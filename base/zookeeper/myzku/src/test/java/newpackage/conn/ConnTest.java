/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.conn;

import com.eva.myzku.ope.conn.ZKConn;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 *
 * @author 97617
 */
public class ConnTest {

    String connString = "192.168.0.108:2181";

    @Test
    public void testConn() {
        try {
            ZooKeeper zk = ZKConn.conn(connString);
            System.out.println("zk:" + zk.getState());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
