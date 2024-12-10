import os
import mysql.connector

# Kết nối đến cơ sở dữ liệu MySQL
conn = mysql.connector.connect(
    host='localhost',
    user='root',
    password='rooot',
    database='dbStaging',
    allow_local_infile=True
)

cursor = conn.cursor()

# Hàm gọi LOAD DATA LOCAL INFILE để nạp dữ liệu
def load_data(relative_csv_path):
    # Xác định đường dẫn tuyệt đối từ file khởi chạy
    script_dir = os.path.dirname(os.path.abspath(__file__))
    absolute_csv_path = os.path.join(script_dir, relative_csv_path)

    # Thay thế '\' bằng '/' để MySQL đọc được
    formatted_csv_path = absolute_csv_path.replace("\\", "/")

    try:
        # Câu lệnh SQL LOAD DATA LOCAL INFILE
        load_data_query = f"""
            LOAD DATA LOCAL INFILE '{formatted_csv_path}'
            INTO TABLE staging_temp
            FIELDS TERMINATED BY ',' 
            ENCLOSED BY '"' 
            LINES TERMINATED BY '\\n' 
            IGNORE 1 ROWS;
        """
        
        # Thực thi câu lệnh
        cursor.execute(load_data_query)
        conn.commit()
        print(f"Data loaded successfully from {formatted_csv_path}")
    
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        conn.close()

# Truyền đường dẫn tương đối của file CSV
# relative_csv_path = "ouput/rawdata/hoangcau/laz_motobike_20241205_130314.csv"
# load_data(relative_csv_path)
