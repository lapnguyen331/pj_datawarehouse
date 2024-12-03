DROP DATABASE IF EXISTS dbControl;
CREATE DATABASE IF NOT EXISTS dbControl;
USE dbControl;

-- Bảng Config để lưu cấu hình cho các quy trình
CREATE TABLE Config (
    id SERIAL PRIMARY KEY,
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

-- Bảng Controls để quản lý trạng thái tổng quan của các quy trình
CREATE TABLE Controls (
    id SERIAL PRIMARY KEY,
    config_id BIGINT UNSIGNED,
    name VARCHAR(255),
    description TEXT,
    status VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (config_id) REFERENCES Config(id)
);

-- Bảng Logs để lưu các sự kiện xảy ra trong quá trình thực thi
CREATE TABLE Logs (
    id SERIAL PRIMARY KEY,
    event_name VARCHAR(255),
    event_type VARCHAR(255),
    status VARCHAR(255),
    location VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Files để lưu thông tin về các file liên quan đến quá trình ETL
CREATE TABLE Files (
    id SERIAL PRIMARY KEY,
    configs_id BIGINT UNSIGNED,
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
    FOREIGN KEY (configs_id) REFERENCES Config(id)
);

-- Bảng Processes để quản lý từng bước quy trình ETL
CREATE TABLE Processes (
    process_id SERIAL PRIMARY KEY,
    process_name VARCHAR(255) NOT NULL,         -- Tên quy trình (Crawl, Transform, Load)
    process_type VARCHAR(50) NOT NULL,          -- Loại quy trình (Crawl, Transform, Load)
    config_id BIGINT UNSIGNED,                  -- Liên kết với bảng Config
    status VARCHAR(50) NOT NULL,                -- Trạng thái (Running, Completed, Failed)
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian bắt đầu
    end_time TIMESTAMP NULL,                    -- Thời gian kết thúc
    message TEXT,                               -- Thông báo trạng thái hoặc lỗi
    FOREIGN KEY (config_id) REFERENCES Config(id)
);
