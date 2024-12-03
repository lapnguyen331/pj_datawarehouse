DROP DATABASE IF EXISTS dbWarehouse;
CREATE DATABASE IF NOT EXISTS dbWarehouse;
USE dbWarehouse;

-- Tạo bảng dateDim trước
CREATE TABLE IF NOT EXISTS dateDim (
    date_sk INT PRIMARY KEY,
    full_date DATE NOT NULL,
    day_since_2010 INT NOT NULL,
    month_since_2010 INT NOT NULL,
    day_of_week VARCHAR(10) NOT NULL,
    calendar_month VARCHAR(10) NOT NULL,
    calendar_year INT NOT NULL,
    calendar_year_month VARCHAR(10) NOT NULL, -- Đã điều chỉnh để đủ 8 ký tự
    day_of_month INT NOT NULL,
    day_of_year INT NOT NULL,
    week_of_year_sunday INT NOT NULL,
    year_week_sunday VARCHAR(10) NOT NULL,
    week_sunday_start DATE NOT NULL,
    week_of_year_monday INT NOT NULL,
    year_week_monday VARCHAR(10) NOT NULL,
    week_monday_start DATE NOT NULL,
    holiday VARCHAR(15) NOT NULL DEFAULT 'Weekday',
    day_type VARCHAR(10) NOT NULL
);

-- Tạo các bảng khác
CREATE TABLE IF NOT EXISTS typeDim (
    type_id INT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS brandDim (
    brand_id INT PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS priceDim (
    price_id INT PRIMARY KEY,
    price_range VARCHAR(255) NOT NULL
);

-- Quản lý nguồn dữ liệu
CREATE TABLE IF NOT EXISTS sourceDim (
    source_id INT PRIMARY KEY,
    source_pid INT,         -- id của sản phẩm tới ở nguồn qui định
    source_name TEXT,       -- Tên nguồn, ví dụ: Lazada, Tiki
    source_type TEXT,       -- Loại nguồn, ví dụ: Web, API
    source_url TEXT,        -- URL của nguồn
    source_SkuID TEXT,      -- Mã SKU nguồn
    description TEXT        -- Mô tả về nguồn
);

CREATE TABLE IF NOT EXISTS motobikeDim (
    id INT PRIMARY KEY,
    model_name VARCHAR(255),
    color VARCHAR(255),
    price FLOAT,
    brand_id INT,           -- Liên kết với bảng brandDim
    type_id INT,            -- Liên kết với bảng typeDim
    price_id INT,           -- Liên kết với bảng priceDim
    date_sk INT,            -- Liên kết với bảng dateDim
    source_dim_id INT,      -- Khóa ngoại liên kết với bảng sourceDim
    name VARCHAR(500),
    engine_capacity VARCHAR(255),
    engine_type VARCHAR(255),
    transmission_type VARCHAR(255),
    features TEXT,
    image_url TEXT,
    status VARCHAR(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (brand_id) REFERENCES brandDim(brand_id),
    FOREIGN KEY (type_id) REFERENCES typeDim(type_id),
    FOREIGN KEY (price_id) REFERENCES priceDim(price_id),
    FOREIGN KEY (date_sk) REFERENCES dateDim(date_sk),
    FOREIGN KEY (source_dim_id) REFERENCES sourceDim(source_id)  -- Sửa lại khoá ngoại tham chiếu đúng
);

-- Tạo bảng FactPrice sau khi bảng dateDim đã được tạo
CREATE TABLE IF NOT EXISTS FactPrice (
    fact_price_id INT AUTO_INCREMENT PRIMARY KEY,  -- Được chỉnh lại thành INT AUTO_INCREMENT
    motobike_id INT,                               -- Liên kết với bảng motobikeDim (Motobike Dimension)
    price_id INT,                                  -- Liên kết với bảng PriceDim (Price Dimension)
    date_sk INT,                                   -- Liên kết với bảng DateDim (Date Dimension)
    price_value FLOAT,                             -- Giá của sản phẩm tại thời điểm này
    source_id INT,                                 -- Liên kết với bảng SourceDim (Source Dimension)
    FOREIGN KEY (motobike_id) REFERENCES motobikeDim(id),  -- Liên kết với bảng Motobike
    FOREIGN KEY (price_id) REFERENCES priceDim(price_id), -- Liên kết với bảng PriceDim
    FOREIGN KEY (date_sk) REFERENCES dateDim(date_sk),    -- Liên kết với bảng DateDim
    FOREIGN KEY (source_id) REFERENCES sourceDim(source_id) -- Liên kết với bảng SourceDim
);

-- các aggregation
CREATE TABLE IF NOT EXISTS AveragePriceBySource (
    source_id INT PRIMARY KEY,
    average_price FLOAT,
    FOREIGN KEY (source_id) REFERENCES sourceDim(source_id)
);

CREATE TABLE IF NOT EXISTS AveragePriceByBrand (
    brand_id INT PRIMARY KEY,
    average_price FLOAT,
    FOREIGN KEY (brand_id) REFERENCES brandDim(brand_id)
);

CREATE TABLE IF NOT EXISTS PriceTrendAggregation (
    date_sk INT PRIMARY KEY,
    average_price FLOAT,
    min_price FLOAT,
    max_price FLOAT,
    FOREIGN KEY (date_sk) REFERENCES dateDim(date_sk)
);
