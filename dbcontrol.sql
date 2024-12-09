DROP DATABASE IF EXISTS dbControl;
CREATE DATABASE IF NOT EXISTS dbControl;
USE dbControl;

-- Bảng Config để lưu cấu hình cho các quy trình
CREATE TABLE config (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    source_path VARCHAR(500),
    destination VARCHAR(500),
    `column` TEXT,
    `separator` VARCHAR(500),
    location VARCHAR(500),
    format VARCHAR(500),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_by VARCHAR(255),
    update_by VARCHAR(255)
);

-- Bảng ProccessControl để quản lý trạng thái tổng quan của các quy trình
CREATE TABLE proccesscontrol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    procces_order int not null, -- để xác định thứ tự chạy của proccess
    config_id BIGINT,
    name VARCHAR(255),
    description TEXT,
    status VARCHAR(255),
    `action` VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (config_id) REFERENCES config(id) -- Đảm bảo tham chiếu đúng khóa ngoại
);

-- Bảng Logs để lưu các sự kiện xảy ra trong quá trình thực thi
CREATE TABLE logs (
    id SERIAL PRIMARY KEY,
    event_name VARCHAR(255),
    event_type VARCHAR(255),
    message TEXT,
    status VARCHAR(255),
    location VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Files để lưu thông tin về các file liên quan đến quá trình ETL
CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    configs_id BIGINT ,
    name VARCHAR(255),
    column_name VARCHAR(255),
    data_format VARCHAR(255),
    file_save TIMESTAMP,
    dir_save VARCHAR(255),
    dir_archive VARCHAR(255),
    note TEXT,
    status VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_by VARCHAR(255),
    update_by VARCHAR(255),
    FOREIGN KEY (configs_id) REFERENCES config(id) -- Đảm bảo tham chiếu khóa ngoại đúng
);

-- Bảng schedule để lập lịch công việc ETL
CREATE TABLE schedule (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_name VARCHAR(255) NOT NULL,               -- Tên công việc ETL
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    end_time TIMESTAMP, 
    status VARCHAR(50) NOT NULL,                 -- Trạng thái công việc (Running, Completed, Failed)
    author VARCHAR(255) NOT NULL,               -- Tên tác giả của công việc
    message TEXT,                                -- Thông báo trạng thái hoặc lỗi
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
