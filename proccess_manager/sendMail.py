import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def send_email(to_email, subject, message_body):
    """
    Gửi email thông qua SMTP.
    """
    # Thông tin SMTP
    smtp_server = "smtp.gmail.com"
    smtp_port = 587
    sender_email = "your_email@gmail.com"  # Email của bạn
    sender_password = "your_password"      # Mật khẩu email (hoặc App Password)

    try:
        # Tạo email
        message = MIMEMultipart()
        message["From"] = sender_email
        message["To"] = to_email
        message["Subject"] = subject
        message.attach(MIMEText(message_body, "plain"))

        # Kết nối tới SMTP server và gửi email
        with smtplib.SMTP(smtp_server, smtp_port) as server:
            server.starttls()  # Bắt đầu kết nối bảo mật
            server.login(sender_email, sender_password)
            server.send_message(message)

        print(f"Email sent successfully to {to_email}")
    except Exception as e:
        print(f"Failed to send email: {e}")
