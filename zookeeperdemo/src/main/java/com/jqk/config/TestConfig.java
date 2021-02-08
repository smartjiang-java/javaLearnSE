package com.jqk.config;

import com.jqk.config.Connection.ZkUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @Author:JiangQiKun
 * @Date:2021/2/7 20:17
 * Description:
 * com.jqk.configTestConfig
 */
public class TestConfig {

    ZooKeeper zk;


    @Before
    public void connection() {
       zk= ZkUtils.getZookeeper();
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myTest() throws InterruptedException {
        //既是Watch,又是Callback
        CallBackWatch callBackWatch = new CallBackWatch();
        callBackWatch.setZk(zk);
        ConfData confData = new ConfData();
        callBackWatch.setConf(confData);
        callBackWatch.awiat();
        while(true){
            if(confData.getConf().equals("")){
                System.out.println("数据被清空了");
                //数据没了,就在这阻塞
                callBackWatch.awiat();
            }else{
                System.out.println(confData.getConf());
                Thread.sleep(2000);
            }
        }
    }

}
