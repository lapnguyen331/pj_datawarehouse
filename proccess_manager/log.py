import pymysql
from datetime import datetime

# Cấu hình database cho logs
log_db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'password',  # Thay bằng mật khẩu của bạn
    'database': 'dbControl'
}

# Kết nối database cho logging
def get_log_connection():
    return pymysql.connect(
        host=log_db_config['host'],
        user=log_db_config['root'],
        password=log_db_config['rooot'],
        database=log_db_config['dbcontrol'],
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )

# Hàm ghi log
def write_log(event_name, event_type, message, status, location=None):
    try:
        connection = get_log_connection()
        with connection.cursor() as cursor:
            sql = """
                INSERT INTO logs (event_name, event_type, message, status, location, create_at)
                VALUES (%s, %s, %s, %s, %s, %s)
            """
            cursor.execute(sql, (event_name, event_type, message, status, location, datetime.now()))
        connection.commit()
    except Exception as e:
        print(f"Error while logging: {e}")
    finally:
        connection.close()
