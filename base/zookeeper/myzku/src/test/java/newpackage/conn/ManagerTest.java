/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.conn;

import com.eva.myzku.ope.conn.ZKConn;
import com.eva.myzku.ope.conn.ZKManager;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author 97617
 */
public class ManagerTest {

    String connString = "192.168.0.108:2181";
    ZooKeeper zk;
    ZKManager manager;

    @Before
    public void before() {
        try {
            zk = ZKConn.conn(connString);
            System.out.println("zk:" + zk.getState());
        } catch (IOException ex) {
            Logger.getLogger(ManagerTest.class.getName()).log(Level.INFO, null, ex);
        }
        try {
            manager = new ZKManager(zk);
        } catch (Exception ex) {
            Logger.getLogger(ManagerTest.class.getName()).log(Level.INFO, null, ex);
        }
    }

    @Test
    public void testEphemeral() throws KeeperException, InterruptedException{
        String path = "/test1";
        String data = "123456";
        manager.createEphemeralNode(path, data.getBytes(StandardCharsets.UTF_8));
    }
    @Test
    public void testPersistent() throws KeeperException, InterruptedException{
        String path = "/test1";
        String data = "123456";
        manager.createPersistentNode(path, data.getBytes(StandardCharsets.UTF_8));
    }
    
    @Test
    public void testDelete() throws KeeperException, InterruptedException{
        String path = "/test1";
        manager.deleteNode(path);
    }
    
     @Test
    public void testUpdate() throws KeeperException, InterruptedException{
        String path = "/test1";
        byte[] data = "abcdef".getBytes(StandardCharsets.UTF_8);
        manager.updateNode(path,data);
    }
    
     @Test
    public void testGet() throws KeeperException, InterruptedException{
        String path = "/test1";
        byte[] data = manager.getData(path);
         System.out.println("数据为："+new String(data,StandardCharsets.UTF_8));
    }
}
