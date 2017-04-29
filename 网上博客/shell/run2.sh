#!/bin/sh

nohup java -jar ./server.jar 8080 app1 localhost:2181 >./logs/s.log &
