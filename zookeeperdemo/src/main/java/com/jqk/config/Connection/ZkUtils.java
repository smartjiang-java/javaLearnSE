package com.jqk.config.Connection;

import com.jqk.config.Connection.DefaultWatch;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/7 20:16
 * Description:
 * com.jqk.configZkUtils
 */
public class ZkUtils {
    /**
     * ip地址可以接path,就是这个zookeeper连接客户端工作的父目录.
     */
    private static  final String address="192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181,192.168.150.14:2181/testConf";
    private static ZooKeeper zooKeeper;
    /**
     * 建立Zookeeper时的watch,用的不多
     */
    private static DefaultWatch watch=new DefaultWatch();
    /**
     * 用于保证连接成功
     */
    private static CountDownLatch countDownLatch=new CountDownLatch(1);


    /**
     * 建立Zookeeper连接
     * @return zookeeper连接
     */
    public static ZooKeeper getZookeeper(){
        try {
            zooKeeper=new ZooKeeper(address,1000,watch);
            watch.setCountDownLatch(countDownLatch);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zooKeeper;

    }

}
