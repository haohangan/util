#-*- coding: UTF-8 -*-
import sys
import threading
import mysql.connector,json
# reload(sys)
# sys.setdefaultencoding('utf-8')

template = ("select customer_id from np_customer")

#获取数据库连接
def getConnect():
    cnx = mysql.connector.connect(user='XX', password='XXXX',host='XXXXXX',database='XXX')
    return cnx

#获取用户id
def getIdData():
    try:
        cnx = getConnect()
        cursor = cnx.cursor()
        cursor.execute(template)
        rows = cursor.fetchall()
        return rows
    except EnvironmentError as e:
        print(e)
    finally:
        cursor.close()
        cnx.close()

#调用存储过程
def callProc(ids):
    try:
        cnx = getConnect()
        cursor = cnx.cursor()
        for cusId in ids:
            cursor.callproc("init_single_user_point",[cusId[0]])
        print("线程结束调用")
    except EnvironmentError as e:
        print(e)
    finally:
        cursor.close()
        cnx.close()

def splitCall(bigList):
    list = [bigList[i:i+1000] for i in range(0,len(bigList),1000)]
    for ids in list:
        # thread.start_new_thread (callProc,(ids,))
        t = threading.Thread(target=callProc,args=(ids,))
        t.start()
    
result = getIdData()
splitCall(result)

