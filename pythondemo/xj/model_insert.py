#-*- coding: UTF-8 -*-
import sys
import mysql.connector,json
# reload(sys)
# sys.setdefaultencoding('utf-8')

template = ("INSERT INTO `kuaixiutest`.`xj_model` (`brand_id`, `model_id`, `model_name`, `img`) VALUES (%s, %s, %s, %s)")

def insertModel(_id,arr):
    cnx = mysql.connector.connect(user='kuaixiutest', password='Kuaixiutest123',host='rds16utan45766t6c4zoo.mysql.rds.aliyuncs.com',database='kuaixiutest')
    cursor = cnx.cursor()
    for d in arr:
        model_id = d['model_id']
        model_name = d['name']
        img = d['img']
        data=(_id,model_id,model_name,img)
        cursor.execute(template,data)
    cnx.commit()
    cursor.close()
    cnx.close()