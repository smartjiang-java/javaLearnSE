package com.jqk.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:JiangQiKun
 * @Date:2021/2/7 23:28
 * Description:
 * com.jqk.lockWatchCallback
 */
public class WatchCallback implements Watcher, AsyncCallback.StringCallback,
        AsyncCallback.Children2Callback , AsyncCallback.StatCallback {

    ZooKeeper zk;
    String threadName;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/",false,this,"fsf");
                break;
            case NodeDataChanged:
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

    //抢锁
    public void tryLock() {
        try {

            zk.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);


            cc.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //释放锁
    public void unLock() {

    }

    //被creat()方法调用
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        //创建成功
        if(s1!=null){
            //父节点不需要监控
            System.out.println(threadName + "create node "+  s1);
            pathName=s1;
            zk.getChildren("/",false,this,"fsf");
        }
    }

    //getChildren call back:节点创建成功,起恩呢个看到自己前面的所有节点
    @Override
    public void processResult(int i, String s, Object o, List<String> list, Stat stat) {

        System.out.println(threadName + "lock  loks ");
        //对自己前面的节点排序
        Collections.sort(list);
        int index = list.indexOf(pathName.substring(1));
        //是不是第一个,countDown
        if(i==0){
            cc.countDown();
        }else{
            //不是
            zk.exists("/"+list.get(i-1),this,this,"safasf");
        }


    }


    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        //编辑
    }
}
