#-*- coding: UTF-8 -*-
# coding: UTF-8
import requests,json
from bs4 import BeautifulSoup
# url = 'http://le.ji5u.cn/jiuniu.php/site?code=001nTR770XixAG138n9705cM770nTR74&state=oauthGetaccessToken'
# headers = {'user-agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12A365   MicroMessenger/5.4.1 NetType/WIFI'}
# r = requests.get(url,headers=headers)
# print(r.content)
# 获取品牌
url = 'http://le.ji5u.cn/jiuniu.php/site'
headers={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400','Cookie':'PHPSESSID=9mq2r2j2tef7ektn1j0otlqd33; 0bd3b81924cd73d4a5d3d3e63ce46336openid=on6VgxJvyer3WvK8dgUkxPcFCzbU; Hm_lvt_e836cfe214e5187f9fc4b645d6900657=1515930384,1515932460,1515932500; Hm_lpvt_e836cfe214e5187f9fc4b645d6900657=1515932500'}
r = requests.get(url,headers=headers)
# print(r.content)
txt = r.content
soup  = BeautifulSoup(txt, "html.parser",from_encoding="UTF-8")
all_div = soup.find_all("div", class_="tab1")
# links = [div.findAll('a') for div in all_div] #还是list对象
# for div in all_div:
#     print div mystr.decode('utf-8').encode(type)
l = []
for a in all_div[0].findAll("a"):
    key =  a.get("data-id")
    value =  a.contents[0]
    d = dict()
    d['id'] = key
    d['name']=value
    l.append(d)
    # d[key] = value
    # d[] = a.contents
s = json.dumps(l)
# print ,a.contents
input = open('data.txt', 'w')
input.write(s)
input.close()
# for child in soup.body.children:
#   print child
# print d