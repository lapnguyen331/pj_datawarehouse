import numpy as np
from selenium import webdriver
from time import sleep
import random
from selenium.webdriver.common.by import By
import pandas as pd
import re
from datetime import datetime
import os

# Khởi tạo DataFrame
columns = ["id", "model_name","type" ,"color", "price","price_range", "brand", "version", "name", "engine_capacity",
           "engine_type", "transmission_type", "features", "image_url", "source_url", "source_pid",
           "source_SkuId", "source_name", "status", "create_at"]

def initialize_dataframe():
    return pd.DataFrame(columns=columns)

# Hàm lấy tất cả sản phẩm từ trang chính
def getAllItem(link, driver, currentDateTime):
    driver.get(link)
    sleep(random.uniform(2, 8))

    
    elems = driver.find_elements(By.CSS_SELECTOR, ".product-item")
    titleelems = driver.find_elements(By.CSS_SELECTOR,".product-item .product-title")
    title = [title.text for title in titleelems]

    # link_elem = driver.find_elements(By.CSS_SELECTOR, "._95X4G [href]")
    linkElem = driver.find_elements(By.CSS_SELECTOR,".product-title a")
    links = [str(elem.get_attribute('href')) for elem in linkElem]
    print(links)
    elems_price = driver.find_elements(By.CSS_SELECTOR, ".product-price")
    price = [elem.text for elem in elems_price]
    currentDateTime= currentDateTime
    source = "hoangcau.com"
    status = 'active'
    data = list(zip(np.arange(1, len(title) + 1), title, price, links, [source] * len(title),[status]* len(title),[str(currentDateTime)]* len(title)))
    df1 = pd.DataFrame(data, columns=["id", "name", "price", "source_url", "source_name",'status','create_at'])
    return df1

# Lấy chi tiết sản phẩm
def getDetailItem(link, index, driver):
    driver.get(link)
    sleep(random.uniform(2, 4))
    
    try:
        coloroptions = driver.find_elements(By.XPATH, '//input[@name="option2"]')
        # Lấy thông tin từ từng option
        color= coloroptions[0].get_attribute('value') #lấy 1 thông tin về màu sắc là đủ
            
    except Exception as e:
        color = "N/A"

    try:
        image = driver.find_element(By.CSS_SELECTOR, '.product-images img').get_attribute("src")
    except Exception as e:
        image = "N/A"
    try:
        typeElem = driver.find_element(By.CSS_SELECTOR, '.pro-type a')
        type = typeElem.text
    except Exception as e:
        type = "N/A" 

    try:
        brandElem = driver.find_element(By.CSS_SELECTOR, '.pro-brand a')
        brand = brandElem.text
    except Exception as e:
        brand = "N/A" 
    
    product_id = "N/A"
    sku_id = "N/A"
    price_range = "N/A"
    
    return pd.DataFrame([[index, brand,price_range, image,type, color, product_id, sku_id]],
                        columns=["detail_id", "brand", "price_range","image_url","type", "color", "source_id", "source_SkuId"])

# Gọi hàm crawl cho trang và lưu kết quả
def getDataPage(url, driver,currentDateTime):
    df1 = getAllItem(url, driver,currentDateTime)
    df_detail_list = []

    for idx, row in df1.iterrows():
        try:
            df_detail = getDetailItem(row['source_url'], row['id'], driver)
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
def crawlerHC(filePath):
    lazURLList = [
        {"name": 'N/A', "link": "https://hoangcau.com/collections/all?page=1"},
    ]

    currentDateTime = getCurrentDateTime()
    df_main = initialize_dataframe()
    now = datetime.now()
    index =1;
    for i in range(1,6):
        driver = webdriver.Chrome()
        link = subUrl("https://hoangcau.com/collections/all?page=1")+str(i)
        try:
            df_final = getDataPage(link, driver, now)
            df_main = pd.concat([df_main, df_final], ignore_index=True)
        finally:
            driver.quit()

    filename = f"laz_motobike_{currentDateTime}.csv"
    df_main.drop('id',axis=1)
    df_main['id'] = range(1, len(df_main) + 1)
    parent_dir = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))

    target_dir = os.path.join(parent_dir, filePath)
    csv_path = os.path.join(target_dir, filename)


    df_main.to_csv(csv_path, index=False, encoding='utf-8')
    print(f"Crawl hoàn tất và lưu vào {filename}")
    return [target_dir,filename]
def subUrl(link):
    return link[0:-1]

# crawlerHC("/output/rawdata/hoangcau")
