#-*- coding: UTF-8 -*-
import sys
import mysql.connector,json
# reload(sys)
# sys.setdefaultencoding('utf-8')

fileObj = open('xianji/data.txt', 'r')
json_txt = json.load(fileObj)

# for d in json_txt:
#     print d['id'],d['name']

template = ("INSERT INTO `kuaixiutest`.`xj_brand` (`brand_id`, `brand_name`) VALUES (%s, %s)")
cnx = mysql.connector.connect(user='kuaixiutest', password='Kuaixiutest123',host='rds16utan45766t6c4zoo.mysql.rds.aliyuncs.com',database='kuaixiutest')
cursor = cnx.cursor()
for d in json_txt:
    data=(d['id'],d['name'])
    cursor.execute(template,data)
cnx.commit()
cursor.close()
cnx.close()