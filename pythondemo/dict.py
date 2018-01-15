import os
import sys
import json
import mysql.connector

host = '123.mysql.rds.aliyuncs.com'
user = '123'
pwd = '123'
cnx = mysql.connector.connect(user=user, password=pwd,host=host,database='kuaixiutest')
cursor = cnx.cursor()
sql = 'select BRANDCODE,BRANDNAME from tb_brand'
cursor.execute(sql)
d = dict()
for (BRANDCODE,BRANDNAME) in cursor:
    d[BRANDCODE] = BRANDNAME
cursor.close()
cnx.close()
# start_response('200 OK', [('Content-Type', 'text/plain')])
res = json.dumps(d)
# return res
print res
