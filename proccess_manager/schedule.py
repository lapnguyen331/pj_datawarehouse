import schedule
import time

def job():
    """
    Công việc chạy theo lịch.
    """
    print("Running scheduled ETL job...")
    # Ví dụ: gọi các bước ETL hoặc gửi báo cáo
    send_email(
        to_email="recipient_email@gmail.com",
        subject="ETL Job Status",
        message_body="The scheduled ETL job has been executed successfully."
    )

# Định nghĩa lịch
schedule.every().day.at("09:00").do(job)  # Chạy hàng ngày vào 9:00 sáng
schedule.every(1).hour.do(job)           # Chạy mỗi giờ một lần

# Chạy lịch
print("Scheduler started. Waiting for jobs...")
while True:
    schedule.run_pending()
    time.sleep(1)
