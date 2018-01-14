#-*- coding: UTF-8 -*-
import sys
import mysql.connector,json
# reload(sys)
# sys.setdefaultencoding('utf-8')

fileObj = open('data.txt', 'r')
json_txt = json.load(fileObj)

# for d in json_txt:
#     print d['id'],d['name']

template = ("INSERT INTO `test`.`brand` (`id`, `name`) VALUES (%s, %s)")
cnx = mysql.connector.connect(user='root', password='tiger',host='192.168.1.12',database='test')
cursor = cnx.cursor()
for d in json_txt:
    data=(d['id'],d['name'])
    cursor.execute(template,data)
cnx.commit()
cursor.close()
cnx.close()