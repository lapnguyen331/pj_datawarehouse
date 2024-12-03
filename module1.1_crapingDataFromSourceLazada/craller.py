import numpy as np
from selenium import webdriver
from time import sleep
import random
from selenium.webdriver.common.by import By
import pandas as pd
import re
from datetime import datetime

# Khởi tạo DataFrame
columns = ["id", "model_name","type" ,"color", "price", "brand", "version", "name", "engine_capacity",
           "engine_type", "transmission_type", "features", "image_url", "source_url", "source_id",
           "source_SkuId", "source_name", "status", "create_at"]

def initialize_dataframe():
    return pd.DataFrame(columns=columns)

# Hàm lấy tất cả sản phẩm từ trang chính
def getAllItem(link, driver, brand, currentDateTime):
    driver.get(link)
    sleep(random.uniform(2, 8))

    
    elems = driver.find_elements(By.CSS_SELECTOR, ".RfADt [href]")
    title = [elem.text for elem in elems]

    link_elem = driver.find_elements(By.CSS_SELECTOR, "._95X4G [href]")
    links = [elem.get_attribute('href') for elem in link_elem]

    elems_price = driver.find_elements(By.CSS_SELECTOR, ".aBrP0")
    price = [elem.text for elem in elems_price]
    currentDateTime= currentDateTime
    source = "lazada"
    status = 'active'
    data = list(zip(np.arange(1, len(title) + 1), title, price, links, [source] * len(title),[status]* len(title),[str(currentDateTime)]* len(title)))
    df1 = pd.DataFrame(data, columns=["id", "name", "price", "source_url", "source_name",'status','create_at'])
    return df1

# Lấy chi tiết sản phẩm
def getDetailItem(link, index, driver, brand):
    driver.get(link)
    sleep(random.uniform(2, 4))
    
    try:
        color_elems = driver.find_elements(By.CSS_SELECTOR, ".sku-prop-content-header .sku-name")
        color = ", ".join([elem.text for elem in color_elems]) if color_elems else "N/A"
    except Exception as e:
        color = "N/A"

    try:
        image = driver.find_element(By.CSS_SELECTOR, '.pdp-mod-common-image').get_attribute("src")
    except Exception as e:
        image = "N/A"
        
    product_info = extract_product_info(link)
    product_id = product_info.get("product_id", "N/A")
    sku_id = product_info.get("sku_id", "N/A")
    
    return pd.DataFrame([[index, brand, image, color, product_id, sku_id]],
                        columns=["detail_id", "brand", "image_url", "color", "source_id", "source_SkuId"])

# Trích xuất product_id và sku_id từ URL
def extract_product_info(url):
    product_id_match = re.search(r"-i(\d+)", url)
    sku_id_match = re.search(r"-s(\d+)\.html", url)
    return {
        "product_id": product_id_match.group(1) if product_id_match else "N/A",
        "sku_id": sku_id_match.group(1) if sku_id_match else "N/A"
    }

# Gọi hàm crawl cho trang và lưu kết quả
def getDataPage(url, driver, brand,currentDateTime):
    df1 = getAllItem(url, driver, brand,currentDateTime)
    df_detail_list = []

    for idx, row in df1.iterrows():
        try:
            df_detail = getDetailItem(row['source_url'], row['id'], driver, brand,currentDateTime)
            df_detail_list.append(df_detail)
        except Exception as e:
            print(f"Lỗi xử lý sản phẩm {row['source_url']}: {e}")
    # Kiểm tra nếu không có chi tiết sản phẩm
    if not df_detail_list:
        print(f"Không lấy được chi tiết sản phẩm cho URL: {url}")
        return df1
    df_details = pd.concat(df_detail_list, ignore_index=True)
    df_final = df1.merge(df_details, how='left', left_on='id', right_on='detail_id').drop(columns=['detail_id'])
    return df_final

# Lấy thời gian hiện tại
def getCurrentDateTime():
    now = datetime.now()
    return now.strftime("%Y%m%d_%H%M%S")

# Hàm crawl chính
def crawler():
    lazURLList = [
        {"name": 'yamaha', "link": "https://www.lazada.vn/xe-standard/yamaha/"},
        {"name": 'honda', "link": "https://www.lazada.vn/xe-standard/honda/"},
        {"name": 'osakar', "link": "https://www.lazada.vn/xe-standard/osakar/"}
    ]

    currentDateTime = getCurrentDateTime()
    df_main = initialize_dataframe()
    now = datetime.now()

    for l in lazURLList:
        driver = webdriver.Chrome()
        try:
            df_final = getDataPage(l["link"], driver, l["name"], now)
            df_main = pd.concat([df_main, df_final], ignore_index=True)
        finally:
            driver.quit()

    filename = f"laz_motobike_{currentDateTime}.csv"
    df_main.to_csv(filename, index=False, encoding='utf-8')
    print(f"Crawl hoàn tất và lưu vào {filename}")

crawler()
