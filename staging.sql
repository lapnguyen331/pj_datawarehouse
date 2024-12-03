create database if not exists dbStaging;
use dbStaging;

-- đây là cấu trúc của bảng đổ dữ liệu từ csv
CREATE TABLE IF NOT EXISTS staging_temp (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    model_name TEXT,
    type TEXT,
    color TEXT,
    price TEXT,
    brand TEXT,
    version TEXT,
    name TEXT,
    engine_capacity TEXT,
    engine_type TEXT,
    transmission_type TEXT,
    features TEXT,
    image_url TEXT,
    source_url TEXT,
    source_id TEXT,
    source_SkuId TEXT,
    source_name TEXT,
    status TEXT,
    create_at TEXT
);

-- cuấ trúc bảng giống với warehouse và có dữ liệu sạch sau quá trình transfoem và valid 
CREATE TABLE IF NOT EXISTS staging (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    model_name VARCHAR(255),
    type VARCHAR(255),
    color VARCHAR(255),
    price FLOAT,
    price_range varchar(255),
    brand VARCHAR(255),
    version VARCHAR(255),
    name VARCHAR(500),
    engine_capacity VARCHAR(255),
    engine_type VARCHAR(255),
    transmission_type VARCHAR(255),
    features TEXT,
    image_url TEXT,
    source_url text,
    source_id int,
    source_SkuId text,
    source_name text,
    status VARCHAR(255),
    create_at TIMESTAMP
);
SET GLOBAL local_infile=ON;
