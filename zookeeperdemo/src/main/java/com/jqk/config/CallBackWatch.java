package com.jqk.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/7 20:46
 * Description:
 * com.jqk.configCallBackWatch
 */
public class CallBackWatch implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

   ZooKeeper zk;
   ConfData conf;
   CountDownLatch cc=new CountDownLatch(1);

    public ConfData getConf() {
        return conf;
    }

    public void setConf(ConfData conf) {
        this.conf = conf;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    /**
     * 回调方法:数倍被修改,节点发生变化
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.exists("/AppConf", this, this, "ABC");
                break;
            case NodeDeleted:
                //节点被删除,根据容忍性写代码
                conf.setConf("");
                cc=new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.exists("/AppConf", this, this, "ABC");
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }

    /**
     *getData()调这个方法
     */
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
       if(bytes!=null){
           String str = new String(bytes);
           conf.setConf(str);
           cc.countDown();
       }
    }

    public void awiat() throws InterruptedException {
        zk.exists("/AppConf", this, this, "ABC");
        cc.await();
    }

    /**
     * 如果节点存在调用
     */
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        if(stat!=null){
          zk.getData("/AppConf",this,this,"flag");
        }

    }
}
