echo $WORKSPACE

version_str="version-2.0-"
build_date=`date '+%Y-%m-%d %H:%M:%S'`
version_str=$version_str$build_date

bossTomcatId=$(ps -ef |grep tomcat |grep -w 'boss'|grep -v 'grep'|awk '{print $2}')
if [[ $bossTomcatId ]];
then
    echo $bossTomcatId
    kill -9 $bossTomcatId
    echo 'kill -9 $bossTomcatId'
else
    echo 'boss not started'
fi
rm -rf /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/*
cp -r $WORKSPACE/kstore_newboss_site/target/kstore_newboss_site-0.0.1-SNAPSHOT/* /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/
echo $version_str > /home/jiuniu-yigou/yigou-kstore-boss/webapps/ROOT/version.txt
BUILD_ID=dontKillMe3 /home/jiuniu-yigou/yigou-kstore-boss/bin/startup.sh
echo 'restart boss over'