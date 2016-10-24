/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eva.myzku.ope.conn;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 *
 * @author 97617
 */
public class ZKManager {

    private ZooKeeper zk;

    public ZKManager(ZooKeeper zk) throws Exception {
        if (zk.getState() != ZooKeeper.States.CONNECTED) {
            throw new Exception("不可用的zookeeper实例！");
        }
        this.zk = zk;
    }

    /**
     * 创建瞬时节点
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException 
     */
    public void createEphemeralNode(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }
    
    /**
     * 创建持久节点
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException 
     */
    public void createPersistentNode(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
    
    public void deleteNode(String path) throws KeeperException, InterruptedException{
        Stat stat = zk.exists(path,null);
        zk.delete(path, stat.getVersion());
    }
    
    public void updateNode(String path,byte[] data) throws KeeperException, InterruptedException{
        Stat stat = zk.exists(path,null);
        zk.setData(path, data, stat.getVersion());
    }
    
    public byte[] getData(String path) throws KeeperException, InterruptedException{
         Stat stat = zk.exists(path,null);
         return zk.getData(path, null, stat);
    }
}
