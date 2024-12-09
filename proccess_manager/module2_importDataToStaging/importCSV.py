import mysql.connector

# Kết nối đến cơ sở dữ liệu MySQL
conn = mysql.connector.connect(
    host='localhost',        
    user='root',     # username
    password='rooot', #  password 
    database='dbStaging' ,     
    allow_local_infile=True   # Bật tính năng LOCAL INFILE
)

cursor = conn.cursor()

# Hàm gọi LOAD DATA LOCAL INFILE để nạp dữ liệu
def load_data(csv_file_path):
    try:
        # Câu lệnh SQL LOAD DATA LOCAL INFILE
        load_data_query = f"""
            LOAD DATA LOCAL INFILE '{csv_file_path}' 
            INTO TABLE staging_temp
            FIELDS TERMINATED BY ',' 
            ENCLOSED BY '"' 
            LINES TERMINATED BY '\\n' 
            IGNORE 1 ROWS;
        """
        
        # Thực thi câu lệnh
        cursor.execute(load_data_query)
        conn.commit()
        print(f"Data loaded successfully from {csv_file_path}")
    
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        conn.close()

# Truyền đường dẫn của file CSV
csv_file_path = 'D:/learning_code/datatwarehouse/module1.1_crapingDataFromSourceLazada/laz_motobike_20241204_193647.csv'  # Thay thế với đường dẫn thực tế của bạn
load_data(csv_file_path)
