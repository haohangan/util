/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eva.myzku.ope.conn;

import com.eva.myzku.ope.conn.logger.MyLogger;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 *
 * @author 97617
 */
public class ZKConn {
    
    private static final int SESSION_TIMEOUT = 1000*3;
    
    /**
     * 超过一段时间连接不上就会报错
     * @param connString
     * @return 
     * @throws java.io.IOException 
     */
    public static ZooKeeper conn(String connString) throws IOException{
        CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zk = null;
        zk = new ZooKeeper(connString, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                 MyLogger.info(event.getPath()+"\t,"+event.getState()+","+event.getType());
                if(event.getState()==Event.KeeperState.SyncConnected){
                   MyLogger.info("链接成功");
                   latch.countDown();
                }
            }
        });
        try {
            latch.await(SESSION_TIMEOUT*3, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            MyLogger.info("连接中断");
        }
        if(zk.getState() !=ZooKeeper.States.CONNECTED){
            throw new IOException("连接失败");
        }
        return zk;
    }
}
