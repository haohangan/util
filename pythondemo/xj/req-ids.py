#-*- coding: UTF-8 -*-
# coding: UTF-8
import requests,json
import htmlPrase,config,model_insert
# url = 'http://le.ji5u.cn/jiuniu.php/site?code=001nTR770XixAG138n9705cM770nTR74&state=oauthGetaccessToken'
# headers = {'user-agent': 'Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12A365   MicroMessenger/5.4.1 NetType/WIFI'}
# r = requests.get(url,headers=headers)
# print(r.content)
# 获取机型   #page 参数
url = 'http://le.ji5u.cn/jiuniu.php?m=jiuniu&c=Site&a=ajax_GetList'
cookie = config.getCookie()
uaerAgent = config.getUserAgent()
headers={'User-Agent':uaerAgent,'Cookie':cookie}

def reqByPage(_id,_page):
    data = {'id':_id,'page':_page}
    r = requests.post(url,headers=headers,data=data)
    result = json.loads(r.content)
    # print '状态：',result['status']
    # print '结果数目：',result['data']['count']
    html = result['data']['html']
    arr = htmlPrase.praseModel(html)
    return arr

# arr = reqByPage('1012017','1')


# 获取所有的列表
def getAllById(_id):
    num = 1
    l = []
    while 1:
        arr = reqByPage(_id,num)
        if len(arr) == 0:
            break
        num = num +1
        for a in arr:
            l.append(a)
    return l

# allList = getAllById('1012017')
# print allList
# print len(allList)

fileObj = open('xianji/ids.txt', 'r')
json_txt = json.load(fileObj)

for id in json_txt:
    print id
    arr = getAllById(id)
    model_insert.insertModel(id,arr)