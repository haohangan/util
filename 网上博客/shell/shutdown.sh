#!/bin/sh

getPid(){
    a=1999
    readonly a
    export a
# a=100
    echo _${a}_
    exit 43
    echo _$a _
}

#getPid
pid=0
serverid(){
    javaps=`jcmd|grep server.jar`
   # echo javaps:$javaps
    if [ -n "$javaps" ];
    then
    #    echo '进入第一个分支' 
        pid=`echo $javaps | awk '{print $1}'`  
    else
    #    echo "进入第二个分支" 
        pid=0
    fi
}
#serverid
#echo pid=$pid

stop(){
    serverid
    echo 'stoping server.jar'
    if [ $pid -ne 0 ]; then
        echo -n "stroping server(pid=$pid)..."
        kill -9 $pid
        if [ $? -eq 0 ]; then
            echo 'success'
        else
            echo 'failed'
        fi
        serverid
        if [ $pid -ne 0 ];then
            stop
        fi
    else
        echo 'server is not running'
    fi
}
stop
