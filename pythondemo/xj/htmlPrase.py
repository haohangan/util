#-*- coding: UTF-8 -*-
# coding: UTF-8
from bs4 import BeautifulSoup

#查询
def praseModel(html):
    soup  = BeautifulSoup(html, "html.parser")
    all_div = soup.findAll("div",class_="wxh_telbox2")
    model_list = []
    for div in all_div:
        all_img = div.findAll('img')
        img_url = ''
        if len(all_img)==1:
            img_url = all_img[0].get("src")
        else:
            img_url = all_img[1].get("src")
        img_url = img_url[0:img_url.find('?')]
        name = div.find("p").contents[0]
        mid = div.find("a").get("href")
        d = dict()
        d['img']= img_url
        d['name'] = unicode(name)
        d['model_id'] = mid[mid.find('id')+3:len(mid)]
        model_list.append(d)
    return model_list