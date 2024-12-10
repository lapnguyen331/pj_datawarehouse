import pymysql
from datetime import datetime
from sendMail import send_email
import schedule

from log import*
import time
from module2_importDataToStaging import importCSV
from module11_crapingDataFromSourceLazada import crallerLaz
from module12_scrapingDataFromSourceHoangCau import crallerHC

import subprocess


class ETLProcessManager:
    def __init__(self, db_config):
        try:
            # Kết nối với database
            self.connection = pymysql.connect(
                host=db_config['host'],
                user=db_config['user'],
                password=db_config['password'],
                database=db_config['database'],
                charset='utf8mb4',
                cursorclass=pymysql.cursors.DictCursor
            )
        except Exception as e:
            print(f"Failed to connect to the database: {e}") 

    def add_process(self,procces_order, config_id, name,description,start_time ):
        """Thêm một bước xử lý mới vào ProcessControl"""
        try:
            with self.connection.cursor() as cursor:
                sql = """
                INSERT INTO proccesscontrol (procces_order,config_id, name, description,status,action,start_time)
                VALUES (%s, %s, %s,%s, %s, %s,%s)
                """
                cursor.execute(sql, (procces_order,config_id, name,description,"pendding","RE",start_time,))
                procid = cursor.lastrowid  # Lấy ID mới nhất vừa được insert

            self.connection.commit()
            print(f"Process '{name}' added successfully.")
             # Ghi log thành công
            write_log(
                event_name="Add Process",
                event_type="CREATE",
                message=f"Process '{name}' added successfully",
                status="SUCCESS",
                location="add proccess method"
            )
            return procid
        except Exception as e:
            write_log("Add Process","error",message=f"Failed to add process: {str(e)}",
            status="FAILED")
            print(f"Error adding process: {e}")
    def add_file(self, config_id, name ,file_save,dir_save,dir_achive,note,status ):
        """Thêm một bước xử lý mới vào ProcessControl"""
        try:
            with self.connection.cursor() as cursor:
                sql = """
                INSERT INTO files (configs_id,name,column_name,data_format,file_save,dir_save,dir_archive,note,status)
                VALUES (%s, %s, %s,%s,%s,%s,%s,%s,%s)
                """
                cursor.execute(sql, (config_id, name,"","csv",file_save,dir_save,dir_achive,note,status))
            self.connection.commit()
            print(f"add files '{name}' added successfully.")
             # Ghi log thành công
            write_log(
                event_name="Add File",
                event_type="Insert",
                message=f"File '{name}' added successfully",
                status="SUCCESS"
            )
        except Exception as e:
            write_log("Add Files","error",message=f"Failed to add File: {str(e)}",
            status="FAILED")
            print(f"Error adding process: {e}")
    #lấy ra config theo id
    def select_conf(self,config_id ):
        """Lấy ra config theo id"""
        try:
            with self.connection.cursor() as cursor:
                sql = "select * from config where config.id = %s"
                
                cursor.execute(sql, (config_id,))
                result = cursor.fetchone()  # Lấy dữ liệu từ truy vấn

            # self.connection.commit()
            print(f"lấy config'{config_id}' successfully.")
             # Ghi log thành công
            if result:
                write_log(
                    event_name="Lấy cấu hình",
                    event_type="Select",
                    message=f"Lấy cấu hình mặc định từ bảng config theo id:{config_id}",
                    status="SUCCESS"
                )
                return result
            else:
                write_log(
                    event_name="Lấy cấu hình",
                    event_type="Select",
                    message=f"Không tìm thấy config với ID {config_id}",
                    status="WARNING"
                )
        except Exception as e:
            write_log("Lấy cấu hình","error",message=f"Lấy cấu hình mặc định từ bảng config failed: {str(e)}",
            status="FAILED")
            print(f"Error adding process: {e}")
    #lấy ra config theo id
    def get_process_status(self,id ):
        """Lấy ra config theo id"""
        try:
            with self.connection.cursor() as cursor:
                sql = "select * from proccesscontrol where id = %s"
                
                cursor.execute(sql, (id,))
                result = cursor.fetchone()  # Lấy dữ liệu từ truy vấn

            # self.connection.commit()
            print(f"lấy config'{id}' successfully.")
             # Ghi log thành công
            if result:
                write_log(
                    event_name="Lấy proccess status",
                    event_type="Select",
                    message=f"Lấy proccess status từ bảng proccess control",
                    status="SUCCESS",
                    location="get_process_status method "+"id:"+ str(id)
                )
                return result
            else:
                write_log(
                    event_name="Lấy proccess status",
                    event_type="Select",
                    message=f"Không tìm thấy proccess status với ID {id}",
                    status="WARNING",
                    location="get_process_status method "+"id:"+ str(id)

                )
        except Exception as e:
            write_log("Lấy proccess status","error",message=f"Lấy proccess status từ bảng proccess control failed: {str(e)}",
            location="get_process_status method "+"id:"+ str(id),
            status="FAILED")
            print(f"Error proccess status: {e}")


    def update_process_status(self, process_id, status,action, message):
        """Cập nhật trạng thái của một bước xử lý"""
        try:
            time= datetime.now()
            with self.connection.cursor() as cursor:
                sql = """
                UPDATE proccesscontrol
                SET status = %s, action = %s, description =%s
                WHERE id = %s
                """
                cursor.execute(sql, (status,action,message + 'id:'+str(process_id), process_id ))
            self.connection.commit()
            print(f"Process ID {process_id} updated to status '{status}'.")
             # Ghi log thành công
            write_log(
                event_name="update Process",
                event_type="update",
                message=f"Update Process status: '{status}', endtime: '{time}',action:'{action}'  successfully",
                status="SUCCESS",
                location="update_proccess_status method"
            )
            # Gửi email thông báo
            if status == "Failed":
                send_email(
                    to_email="21130419@st.hcmuaf.edu.vn",
                    subject=f"ETL Process Failed: Process ID {process_id}",
                    message_body=f"Process ID {process_id} failed with error"
                )
            elif status == "Success":
                send_email(
                    to_email="21130419@st.hcmuaf.edu.vn",
                    subject=f"ETL Process Completed: Process ID {process_id}",
                    message_body=f"Process ID {process_id} completed successfully."
                )

        except Exception as e:
            print(f"Error updating process status: {e}")
            write_log(
                event_name="update Process",
                event_type="error",
                message=f"Update Process status failed: {e}",
                status="Failed",
                location="update_proccess_status method"
            )

    def check_for_pause(self, process_id):
        """Kiểm tra nếu process cần tạm dừng và đợi resume"""
        try:
            while True:
                with self.connection.cursor() as cursor:
                    sql = "SELECT action FROM proccesscontrol WHERE process_id = %s"
                    cursor.execute(sql, (process_id,))
                    result = cursor.fetchone()
                    if result and result['action'] == 'Pause':
                        print(f"Process ID {process_id} is paused.")
                        time.sleep(5)  # Đợi cho đến khi resume
                        write_log(
                            event_name="check Process action",
                            event_type="check action",
                            message=f"Check Process action: 'Pause', procces will be paused",
                            status="Pending",
                            location="check_for_pause method"
                        )
                    elif result and result['action'] == 'Resume':
                        print(f"Process ID {process_id} resumed.")
                        write_log(
                            event_name="check Process action",
                            event_type="check action",
                            message=f"Check Process action: 'Resume', procces will be resume",
                            status="Resume",
                            location="check_for_pause method"

                        )
                        break
        except Exception as e:
            print(f"Error during pause check: {e}")
            write_log(
                event_name="check Process action",
                event_type="error",
                message=f"Can not Check Process action: 'Resume' {e}",
                status="Failed",
                location="check_for_pause method"

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
                status="Stop",
                location="pause_proccess method"
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
                status="Rerun",
                location="pause_proccess method"
            )
        except Exception as e:
            print(f"Error resuming process: {e}")
            write_log(
                event_name="Rerun stopped proccess",
                event_type="error",
                message=f"can not rerun proccess",
                status="error",
                location="pause_proccess method"
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
            self.update_process_status(process_id, "Running",action="start",message="update proccess status")
            self.check_for_pause(process_id)  # Kiểm tra nếu cần pause
            step_function()  # Thực thi bước cụ thể
            self.update_process_status(process_id, "Success",action="done",message="update proccess status")
        except Exception as e:
            self.update_process_status(process_id, "Failed","stop", str(e))

    def close(self):
        """Đóng kết nối với database"""
        self.connection.close()
        print("Database connection closed.")

# Configuration cho database
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'rooot',  # Đổi password theo cấu hình của bạn
    'database': 'dbcontrol'
}


manager = ETLProcessManager(db_config)

# Lập lịch
def scheduled_etl_job():
    print("Executing scheduled ETL job...")
    # thêm bước ETL
    id1 = manager.add_process(config_id=1, name="module1: web scraping", description="",procces_order=1,start_time=datetime.now())
    print("Process added successfully")
    write_log(
        event_name="add proccess",
        event_type="add proccess",
        message="thêm proccess vào bảng",
        status="Success",
        location= "proccesscontrol"
    )
    #chạy các bước etl
    try:
        manager.execute_step(process_id=id1, step_function=lambda: runModule1('hc'))
    except Exception as e:
        write_log(
            event_name="run proccess",
            event_type="run proccess",
            message=str(e),
            status="Failed",
            location= "module 1, hc" +"processId:"+str(id1)
        )
        manager.update_process_status(process_id=id1, status="Failed",action="stop" ,message="Lỗi"+str(e))

    if manager.get_process_status(id1) == 'Success':  # Kiểm tra trạng thái của process_id 1
        id2 = manager.add_process(config_id=3, name="module2: save data", description="",procces_order =2,start_time=datetime.now())
        try:
            manager.execute_step(process_id=id2, step_function=lambda: runModule2())
        except Exception as e:
            write_log(
                event_name="run proccess",
                event_type="run proccess",
                message=str(e),
                status="Failed",
                location= "module 2, hc" +"processId:"+str(id2)
            )
            manager.update_process_status(process_id=id2, status="Failed",action="stop", message=str(e))
    if manager.get_process_status(id2) == 'Success':
        id3 = manager.add_process(config_id=4, name="module3: staging to warehouse", description="",procces_order =3,start_time=datetime.now())
        try:
            manager.execute_step(process_id=id3, step_function=lambda: runModule3())
        except Exception as e:
            write_log(
                event_name="run proccess",
                event_type="run proccess",
                message=str(e),
                status="Failed",
                location= "module 3, hc" +"processId:"+str(id3)
            )
            manager.update_process_status(process_id=id3, status="Failed", message=str(e))
    if manager.get_process_status(id3) == 'Success':
        try:
            id4 = manager.add_process(config_id=5, name="module4: warehouse to mart", description="",procces_order =4,start_time=datetime.now())
            manager.execute_step(process_id=id4, step_function=lambda: runModule4())
        except Exception as e:
            write_log(
                event_name="run proccess",
                event_type="run proccess",
                message=str(e),
                status="Failed",
                location= "module 4, hc" +"processId:"+str(id4)
            )
            manager.update_process_status(process_id=id4, status="Failed",action="stop", message=str(e))

#các hàm gọi thực thi module
def runModule1(sourceName):
    print('excute module 1')
    write_log(
        event_name="run proccess",
        event_type="run proccess",
        message="bắt đầu chạy",
        status="running",
        location= "proccess 1, hc"
    )
    if sourceName == 'hc':
        configdata = manager.select_conf(2)
        filename = crallerHC.crawlerHC(str(configdata['destination']))
        manager.add_file(config_id=configdata['id'],name=configdata['name'],file_save=datetime.now(),dir_achive="hehe",dir_save=configdata['destination'] +"/"+ str(filename[1]),note="lưu file raw data",status="success")

        print('conf:'+str(configdata))
        print('filename :'+str(filename))
        try:
            manager.add_file(config_id=configdata['id'],name=configdata['name'],file_save=datetime.now(),dir_achive="hehe",dir_save=configdata['destination'] +"/"+ str(filename[1]),note="lưu file raw data",status="success")
        
        except Exception as e:
            write_log(
                event_name="save file csv",
                event_type="save file csv",
                message="Lỗi lưu đường dẫn file vào bảng files str({e})",
                status="Error",
                location= "proccess 1, hc"
            )
        print('chạy craler')

    else:
        configdata = manager.select_conf(1)
        filename = crallerLaz.crawlerLaz(str(configdata['destination']))
        try:
            manager.add_file(config_id=configdata['id'],name=configdata['name'],file_save=datetime.now(),dir_achive="hehe",dir_save=configdata['destination'] +"/"+ str(filename[1]),note="lưu file raw data",status="success")
        except Exception as e:
            write_log(
                event_name="save file csv",
                event_type="save file csv",
                message="Lỗi lưu đường dẫn file vào bảng files str({e})",
                status="Error",
                location= "proccess 1, hc"
            )

        print('chạy craler')

def runModule2():
    print("chạy module 2")
    write_log(
        event_name="run proccess",
        event_type="run proccess",
        message="bắt đầu chạy",
        status="running",
        location= "proccess 2"
    )
    configdata = manager.select_conf(5) 
    try:
        importCSV.load_data(configdata) 
    except Exception as e:
        write_log(
            event_name="import data from csv to staging_temp",
            event_type="run proccess",
            message="Lỗi import csv {e}",
            status="Error",
            location= "proccess 2, import data"
        )
def runModule3():
    print("chạy module 2")
    write_log(
        event_name="run proccess",
        event_type="run proccess",
        message="bắt đầu chạy",
        status="running",
        location= "proccess 3"
    )
    try:
        run_java_jar_module3("module3\module3.jar")
    except Exception as e:
        write_log(
            event_name="module 3",
            event_type="run proccess",
            message="Lỗi chạy jar module 3 {e}",
            status="Error",
            location= "proccess 3"
        )
    manager.update_process_status(process_id=3, status="Success",action="done",message="xong")
def runModule4():
    manager.update_process_status(process_id=4, status="Success",action="done",message="xong")
#hàm chạy jar module 3
def run_java_jar_module3(jarPath):
    try:
        result = subprocess.run(
            ["java", "-jar", jarPath],  # Lệnh Java để chạy file HelloWorld.jar
            capture_output=True,
            text=True,
            check=True
        )
        write_log(
            event_name="run proccess module3",
            event_type="run proccess",
            message="bắt đầu chạy file jar của module3",
            status="Success",
            location= "proccess 3"
        )
        write_log(
            event_name="run proccess module3",
            event_type="run proccess",
            message="bắt đầu chạy proccess 3",
            status="Running",
            location= "proccess 3"
        )
         # Ghi log thành công
        write_log(
            event_name="run process module3",
            event_type="run process",
            message=f"Process 3 hoàn thành. Output: {result.stdout.strip()}",
            status="Success",
            location="process 3"
        )
        print("Java Output:", result.stdout)
    except subprocess.CalledProcessError as e:
        # Ghi log khi gặp lỗi
        write_log(
            event_name="run process module3",
            event_type="run process",
            message=f"Process 3 thất bại. Error: {e.stderr.strip()}",
            status="Failed",
            location="process 3"
        )
        print("Error running Java process:", e.stderr)
#hàm chạy jar module 4
def run_java_jar_module4(jarPath):
    try:
        result = subprocess.run(
            ["java", "-jar", jarPath],  # Lệnh Java để chạy file HelloWorld.jar
            capture_output=True,
            text=True,
            check=True
        )
        write_log(
            event_name="run proccess module3",
            event_type="run proccess",
            message="bắt đầu chạy file jar của module3",
            status="Success",
            location= "proccess 4"
        )
        write_log(
            event_name="run proccess module4",
            event_type="run proccess",
            message="bắt đầu chạy proccess 4",
            status="Running",
            location= "proccess 4"
        )
         # Ghi log thành công
        write_log(
            event_name="run process module3",
            event_type="run process",
            message=f"Process 4 hoàn thành. Output: {result.stdout.strip()}",
            status="Success",
            location="process 4"
        )
        print("Java Output:", result.stdout)
    except subprocess.CalledProcessError as e:
        # Ghi log khi gặp lỗi
        write_log(
            event_name="run process module3",
            event_type="run process",
            message=f"Process 3 thất bại. Error: {e.stderr.strip()}",
            status="Failed",
            location="process 3"
        )
        print("Error running Java process:", e.stderr)

# configdata = manager.select_conf(2)
# manager.add_file(config_id=configdata['id'],name=configdata['name'],file_save=datetime.now(),dir_achive="hehe",dir_save=configdata['destination'],note="lưu file raw data",status="success")

# Lập lịch hàng ngày vào 08:00
# schedule.every().day.at("20:53").do(scheduled_etl_job)
dt = "10:03" #set thời gian mặc định chạy hằng ngày ở đây
schedule.every().day.at(dt).do(scheduled_etl_job)
print("Scheduled job at "+dt)

print("Scheduler started. Waiting for ETL jobs...")
while True:
    schedule.run_pending()
    time.sleep(1)
