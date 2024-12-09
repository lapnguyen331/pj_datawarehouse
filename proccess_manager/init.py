import pymysql
from datetime import datetime
from sendMail import send_email
from schedule import *
from log import*
import time
from module2_importDataToStaging import importCSV
from module11_crapingDataFromSourceLazada import crallerLaz
from module12_scrapingDataFromSourceHoangCau import crallerHC




class ETLProcessManager:
    def __init__(self, db_config):
        # Kết nối với database
        self.connection = pymysql.connect(
            host=db_config['host'],
            user=db_config['user'],
            password=db_config['password'],
            database=db_config['database'],
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor
        )

    def add_process(self,procces_order, config_id, name,description,start_time ):
        """Thêm một bước xử lý mới vào ProcessControl"""
        try:
            with self.connection.cursor() as cursor:
                sql = """
                INSERT INTO proccesscontrol (procces_order,config_id, name, description,start_time, end_time,status,action)
                VALUES (%s, %s, %s,%s,%s,null, 'Pending', 'None')
                """
                cursor.execute(sql, (procces_order,config_id, name,description,start_time))
            self.connection.commit()
            print(f"Process '{name}' added successfully.")
             # Ghi log thành công
            write_log(
                event_name="Add Process",
                event_type="CREATE",
                message=f"Process '{name}' added successfully",
                status="SUCCESS"
            )
        except Exception as e:
            write_log("Add Process","error",message=f"Failed to add process: {str(e)}",
            status="FAILED")
            print(f"Error adding process: {e}")

    def update_process_status(self, process_id, status,action):
        """Cập nhật trạng thái của một bước xử lý"""
        try:
            datetime= datetime.now()
            with self.connection.cursor() as cursor:
                sql = """
                UPDATE proccesscontrol
                SET status = %s, end_time = %s, action = %s
                WHERE process_id = %s
                """
                cursor.execute(sql, (status, datetime,action, process_id))
            self.connection.commit()
            print(f"Process ID {process_id} updated to status '{status}'.")
             # Ghi log thành công
            write_log(
                event_name="update Process",
                event_type="update",
                message=f"Update Process status: '{status}', endtime: '{datetime}',action:'{action}'  successfully",
                status="SUCCESS"
            )
            # Gửi email thông báo
            if status == "Failed":
                send_email(
                    to_email="admin_email@gmail.com",
                    subject=f"ETL Process Failed: Process ID {process_id}",
                    message_body=f"Process ID {process_id} failed with error: {error_message}"
                )
            elif status == "Success":
                send_email(
                    to_email="admin_email@gmail.com",
                    subject=f"ETL Process Completed: Process ID {process_id}",
                    message_body=f"Process ID {process_id} completed successfully."
                )

        except Exception as e:
            print(f"Error updating process status: {e}")
            write_log(
                event_name="update Process",
                event_type="error",
                message=f"Update Process status: '{status}', endtime: '{datetime}',action:'{action}'  Failed",
                status="Failed"
            )

    def check_for_pause(self, process_id):
        """Kiểm tra nếu process cần tạm dừng và đợi resume"""
        try:
            while True:
                with self.connection.cursor() as cursor:
                    sql = "SELECT action FROM processcontrol WHERE process_id = %s"
                    cursor.execute(sql, (process_id,))
                    result = cursor.fetchone()
                    if result and result['action'] == 'Pause':
                        print(f"Process ID {process_id} is paused.")
                        time.sleep(5)  # Đợi cho đến khi resume
                        write_log(
                            event_name="check Process action",
                            event_type="check action",
                            message=f"Check Process action: 'Pause', procces will be paused",
                            status="Pending"
                        )
                    elif result and result['action'] == 'Resume':
                        print(f"Process ID {process_id} resumed.")
                        write_log(
                            event_name="check Process action",
                            event_type="check action",
                            message=f"Check Process action: 'Resume', procces will be resume",
                            status="Resume"
                        )
                        break
        except Exception as e:
            print(f"Error during pause check: {e}")
            write_log(
                event_name="check Process action",
                event_type="error",
                message=f"Can not Check Process action: 'Resume'",
                status="Failed"
            )

    def pause_process(self, process_id):
        """Tạm dừng một bước xử lý"""
        try:
            with self.connection.cursor() as cursor:
                sql = "UPDATE processcontrol SET action = 'Pause' WHERE process_id = %s"
                cursor.execute(sql, (process_id,))
            self.connection.commit()
            print(f"Process ID {process_id} set to 'Pause'.")
             write_log(
                event_name="Stop Process ",
                event_type="check action",
                message=f"Check Process action: 'Stop', procces will be Stop",
                status="Stop"
            )
        except Exception as e:
            print(f"Error pausing process: {e}")

    def resume_process(self, process_id):
        """Tiếp tục một bước xử lý đã tạm dừng"""
        try:
            with self.connection.cursor() as cursor:
                sql = "UPDATE processcontrol SET action = 'Resume' WHERE process_id = %s"
                cursor.execute(sql, (process_id,))
            self.connection.commit()
            print(f"Process ID {process_id} set to 'Resume'.")
            write_log(
                event_name="Rerun stopped proccess",
                event_type="check action",
                message=f"procces will be rerun",
                status="Rerun"
            )
        except Exception as e:
            print(f"Error resuming process: {e}")
            write_log(
                event_name="Rerun stopped proccess",
                event_type="error",
                message=f"can not rerun proccess",
                status="error"
            )

    def rerun_process(self, process_id):
        """Chạy lại một bước xử lý từ đầu"""
        try:
            with self.connection.cursor() as cursor:
                sql = """
                UPDATE ProcessControl 
                SET status = 'Pending', action = 'Re-run', start_time = NULL, end_time = NULL
                WHERE process_id = %s
                """
                cursor.execute(sql, (process_id,))
            self.connection.commit()
            print(f"Process ID {process_id} set to 'Re-run'.")
        except Exception as e:
            print(f"Error re-running process: {e}")

    def execute_step(self, process_id, step_function):
        """Thực thi một bước xử lý với khả năng pause/resume."""
        try:
            self.update_process_status(process_id, "Running")
            self.check_for_pause(process_id)  # Kiểm tra nếu cần pause
            step_function()  # Thực thi bước cụ thể
            self.update_process_status(process_id, "Success")
        except Exception as e:
            self.update_process_status(process_id, "Failed", str(e))

    def close(self):
        """Đóng kết nối với database"""
        self.connection.close()
        print("Database connection closed.")

# Configuration cho database
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'rooot',  # Đổi password theo cấu hình của bạn
    'database': 'dbController'
}

# Sử dụng ETLProcessManager
manager = ETLProcessManager(db_config)

# Lập lịch
def scheduled_etl_job():
    print("Executing scheduled ETL job...")
    manager = ETLProcessManager(db_config)
    try:
        # Chạy từng bước ETL
        manager.add_process(config_id=1, step_name="module1: web scraping", step_order=1)
        manager.add_process(config_id=2, step_name="module2: save data",step_order =2)
        manager.add_process(config_id=3, step_name="module3: staging to warehouse",step_order =3)
        manager.add_process(config_id=4, step_name="module4: warehouse to mart",step_order =4)

        manager.execute_step(process_id=1, step_function=lambda: print("Crawling data..."))
    except Exception as e:
        manager.update_process_status(process_id=1, status="Failed", error_message=str(e))
    finally:
        manager.close()
#các hàm gọi thực thi module
def runModule1():
    

# Lập lịch hàng ngày vào 08:00
schedule.every().day.at("08:00").do(scheduled_etl_job)

print("Scheduler started. Waiting for ETL jobs...")
while True:
    run_pending()
    time.sleep(1)
