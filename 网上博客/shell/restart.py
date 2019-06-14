#!/usr/bin/python
import os,sys,time,commands

_program = '/opt/cloud/cloud-register-1.0.jar'
_log = '/opt/cloud/eureka.log'
_daemon = 'appStoreServer.py'

def getId():
    result = commands.getoutput("ps aux | grep java| grep cloud-register |grep java|grep -v grep | awk '{print $2}'")
    print("id=",result)
    return result

def kill():
    id = getId()
    if id != '':
        os.system('kill -9 '+id)

def restart():
    kill()
    os.system('rm -f '+_log)
    os.system('nohup java -jar '+_program+' >'+_log+' 2>&1 &')
    print('start success',getId())

restart()
