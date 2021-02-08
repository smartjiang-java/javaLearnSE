package com.jqk.lock;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/7 23:22
 * Description:
 * com.jqk.lockTestLock
 */
public class TestLock {
    ZooKeeper zk;


    @Before
    public void connection() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {

    }

    @Test
    public void lock(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    //每一个线程区抢锁
                    WatchCallback watchCallback = new WatchCallback();
                    watchCallback.setZk(zk);
                    String threadName = Thread.currentThread().getName();
                    watchCallback.setThreadName(threadName);

                    //抢到锁
                    watchCallback.tryLock();

                    //干活
                    System.out.println("干活");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //师傅那个锁
                    watchCallback.unLock();



                }
            }.start();
        }
    }




}
