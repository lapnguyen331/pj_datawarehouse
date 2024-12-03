import pandas as pd
import requests
import time
import random
from tqdm import tqdm

cookies = {
    '_trackity':'607572fe-4fb3-00bf-9297-9ca0fbfd1040',
     '_ga':'GA1.1.165055706.1729066526',
      ' _gcl_au':'1.1.1076244898.1729066530' ,
      '__uidac':'01670f7622c9d62d77f7029240a30d9f',
      '__build_class__ __iid':'749',
    '__iid':'749',
    '__su':'0',
    '__su':'0',
    'dtdz':'c1e5cd8f-692c-502e-a154-0ea970c8dd8b' ,
    '__RC':'5' ,
    '__R':'3',
    '_fbp':'fb.1.1729066530657.308936311293772427',
    '_hjSessionUser_522327':'eyJpZCI6ImQyMWIyZGIxLTNkNTctNWFiMS1hZjY1LWMyNjE3ZWFlZmNjZSIsImNyZWF0ZWQiOjE3MjkwNjY1Mjk3OTUsImV4aXN0aW5nIjp0cnVlfQ==',
    '__cached__ __tb':'0',
    '_gcl_aw':'GCL.1729067901.Cj0KCQjwyL24BhCtARIsALo0fSD_x6Q7nJudDqHVoQZ-tFRc5W49MSxYa8-PpnE73SCUyUVRSBgNIIoaAktMEALw_wcB',
    'GeneratorExit_gcl_gs':'2.1.k1$i1729067899$u96314649',
     'TOKENS':'{%22access_token%22:%220jPTYOM6GxpUEFvBZ2hkelJzg4QrowL5%22}',
     'delivery_zone':'Vk4wMzkwMDYwMDE=' ,
     'tiki_client_id':'165055706.1729066526',
       '_hjSession_522327':'eyJpZCI6IjU1OTI2ZjFiLTRlMDEtNDNhNC04OWIxLThiOGQyMDRhYzAxYSIsImMiOjE3Mjk1Mjc5MzQxNzMsInMiOjAsInIiOjAsInNiIjowLCJzciI6MCwic2UiOjAsImZzIjowLCJzcCI6MX0=',
        '__adm_upl':'eyJ0aW1lIjoxNzI5NTMwMTkwLCJfdXBsIjoiNDYzMy0wMTY3MGY3NjIyYzlkNjJkNzdmNzAyOTI0MGEzMGQ5ZiJ9' ,
       '__uif':'__uid%3A2085414212593863108%7C__ui%3A1%252C5%7C__create%3A1728541422',
       '__IP':'1934495598',
         'cto_bundle':'AnGkZV9NQTdXOTJGRFROaThhWldTV1Vjbmp6ZGNKcE41ViUyQkE0MXU0V3A4NjVnQnR0VVFTczM2UGNJbXIwNjJ4V3pKMkF3MW5WcXZkU0tvdXcwUkpvYnk1MWdUcEZ4QlN3Z2ozRjUwQjBIJTJCb2FDJTJGcVp3aWFVQ3V3TXpzTTJhbjJ1OGJOaiUyQmtHU3BKWEtNN2NUWlduTFh2SFdzQ2xjYTlKRyUyQmROMm9ZQUlad3BEMnZJS1E4QUVEOEwwcWg4NFEwT0ozMHJnNXQyMDNHaGV4eks4b2QxbENQTExCUSUzRCUzRA' ,
         '_ga_S9GLR1RQFJ':'GS1.1.1729527928.4.1.1729529871.32.0.0',
           'amp_99d374':'5och5I3qDeVqdVcaO2CXL7...1ianubk2s.1iao06utp.48.52.9a'
}

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0',
    'Accept': 'application/json, text/plain, */*',
    'Accept-Language': 'en-US,en;q=0.9',
    'Referer': 'https://tiki.vn/xe-may-yamaha-mio-m3-125-p37886798.html?spid=38165081',
    'x-guest-token': '8jWSuIDBb2NGVzr6hsUZXpkP1FRin7lY',
    'Connection': 'keep-alive',
    'TE': 'Trailers',
}

params = (
    ('platform','web'),
    ('spid','38165081')
)
#lấy thông tin product
def parser_product(json):
    d = dict()
    d['id'] = json.get('id')
    d['short_description'] = json.get('short_description')
    d['average_price'] = json.get('price')
    d['list_price'] = json.get('list_price')
    d['price_usd'] = json.get('price_usd')
    d['discount'] = json.get('discount')
    d['discount_rate'] = json.get('discount_rate')
    d['product_name'] = json.get('name')
    d['brand_name'] = json.get('brand').get('name')
    d['author'] = json.get('current_seller').get('name')
    d['type'] = json.get('has_other_fee').get('type')
    d['imageGalery']= json.get('images')
    d['specifications'] = json.get('specifications')
    em = json.get('configurable_products')
    d['option'] =[]
    
    if em is not None:
        for item in em:
            newItem = {'id':item['id'],'name':item['name'],'color:': item['option1'], 'origin_price':item['original_price'],'price':item['price']}
            d['option'].append(newItem)
    d['highlight'] = json.get('highlight')
    return d


df_id = pd.read_csv('product_id_yamaha.csv')
p_ids = df_id.id.to_list()
print(p_ids)
result = []
for pid in tqdm(p_ids, total=len(p_ids)):
    response = requests.get('https://tiki.vn/api/v2/products/{}'.format(pid), headers=headers, params=params, cookies=cookies)
    if response.status_code == 200:
        print('Crawl data {} success !!!'.format(pid))
        result.append(parser_product(response.json()))
    # time.sleep(random.randrange(3, 5))
df_product = pd.DataFrame(result)
df_product.to_csv('product_detail_yamaha.csv', index=False,encoding='utf-8-sig')
