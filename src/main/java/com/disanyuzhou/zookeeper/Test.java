package com.disanyuzhou.zookeeper;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by make on 7/22/16.
 */
public class Test implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {

        Logger.getRootLogger().setLevel(Level.WARN);

        ZooKeeper zooKeeper = new ZooKeeper("localhost:2180", 2000, new Test());
        println(zooKeeper.getState());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        println("session id :  " + sessionId);
        println(zooKeeper.getState());
        println("session password: " + passwd);
        println(zooKeeper.getState());

        ZooKeeper zooKeeper1 = new ZooKeeper("localhost:2181", 2000, new Test(), 1L, "test".getBytes());
        ZooKeeper zooKeeper2 = new ZooKeeper("localhost:2181", 2000, new Test(), sessionId, passwd);

        Thread.sleep(10000);

        println(zooKeeper.getState());
        println(zooKeeper1.getState());
        println(zooKeeper2.getState());

        Thread.sleep(Integer.MAX_VALUE);

    }

    public static void println(Object o) {

        System.out.println(o.toString());
    }


    public void process(WatchedEvent watchedEvent) {
        println("receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}




