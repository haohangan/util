#!/bin/sh
tomcatId=$(ps -ef |grep tomcat |grep -w 'boss'|grep -v 'grep'|awk '{print $2}')
#echo $tomcatId
if [[ $tomcatId ]];then
        echo $tomcatId
        kill -9 $tomcatId
        echo 'kill -9 $tomcatId'
fi
rm -rf /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/*
cp -r /home/jiuniu-yigou/.jenkins/workspace/yigou/kstore_newboss_site/target/kstore_newboss_site-0.0.1-SNAPSHOT/* /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/
version_str="version-2.0-"
version_str+=$(date +%Y-%m-%d)
echo $version_str > /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/version.txt
/home/jiuniu-yigou/yigou-kstore-boss/bin/startup.sh
echo 'restart over'