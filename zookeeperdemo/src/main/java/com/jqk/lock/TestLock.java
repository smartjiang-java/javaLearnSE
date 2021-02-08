package com.jqk.lock;

import com.jqk.config.ZkUtils;
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
        zk  = ZkUtils.getZookeeper();
    }

    @After
    public void close() {
        try {
            if(zk!=null){
                zk.close();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    //每一个线程区抢锁,都有自己独立的东西
                    WatchCallback watchCallback = new WatchCallback();
                    watchCallback.setZk(zk);
                    String threadName = Thread.currentThread().getName();
                    watchCallback.setThreadName(threadName);

                    //抢到锁
                    watchCallback.tryLock();

                    //干活
                    System.out.println("干活....");
                    try {
                        //如果没有这个休眠，可能会导致其他节点还没排完序，锁就释放了
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //释放锁
                    watchCallback.unLock();



                }
            }.start();
        }

        while(true){

        }


    }

}
