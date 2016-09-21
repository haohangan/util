/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.bkcopy.zookeper;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Watcher for receiving zookeeper server connection events.
 *
 * @author Administrator
 */
public class ZooKeeperWatcherBase implements Watcher {

    private final int zkSessionTimeoutMS;
    private CountDownLatch latch = new CountDownLatch(1);
    private final CopyOnWriteArraySet<Watcher> childWatcher = new CopyOnWriteArraySet<>();

    public ZooKeeperWatcherBase(int zkSessionTimeoutMS) {
        this.zkSessionTimeoutMS = zkSessionTimeoutMS;
    }

    public ZooKeeperWatcherBase(int zkSessionTimeoutMS, Set<Watcher> childWatchers) {
        this.zkSessionTimeoutMS = zkSessionTimeoutMS;
        this.childWatcher.addAll(childWatchers);
    }

    public ZooKeeperWatcherBase addChildWatcher(Watcher watcher) {
        this.childWatcher.add(watcher);
        return this;
    }

    public ZooKeeperWatcherBase removeChildWatcher(Watcher watcher) {
        this.childWatcher.remove(watcher);
        return this;
    }

    @Override
    public void process(WatchedEvent event) {
// If event type is NONE, this is a connection status change
        if (event.getType() != Event.EventType.None) {
            Logger.getGlobal().log(Level.INFO, "Recieved event: {0}, path: {1} from ZooKeeper server", new Object[]{event.getType(), event.getPath()});
            notifyEvent(event);
            return;
        }

        Logger.getGlobal().log(Level.INFO, "Recieved {0} from ZooKeeper server", event.getState());
        switch (event.getState()) {
            case SyncConnected:
                latch.countDown();
                break;
            case Disconnected:
                latch = new CountDownLatch(1);
                Logger.getGlobal().info("Ignoring Disconnected event from ZooKeeper server");
                break;
            case Expired:
                latch = new CountDownLatch(1);
                Logger.getGlobal().info("ZooKeeper client connection to the ZooKeeper server has expired!");
                break;
            default:
                //do nothing
                break;
        }
        // notify the child watchers
        notifyEvent(event);
    }

    /**
     * Waiting for the SyncConnected event from the ZooKeeper server
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void waitForConnection() throws InterruptedException, KeeperException {
        if (!latch.await(zkSessionTimeoutMS, TimeUnit.MILLISECONDS)) {
            throw KeeperException.create(KeeperException.Code.CONNECTIONLOSS);
        }
    }

    /**
     * Return zookeeper session time out
     *
     * @return
     */
    public int getZkSessionTimeOut() {
        return zkSessionTimeoutMS;
    }

    public void notifyEvent(WatchedEvent event) {
        // notify child watchers
        for (Watcher w : childWatcher) {
            w.process(event);
        }
    }
}
