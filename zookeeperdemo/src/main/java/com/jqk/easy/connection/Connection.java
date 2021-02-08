package com.jqk.easy.connection;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:JQK
 * @Date:2021/2/8 16:20
 * @Package:com.jqk.use.connection
 * @ClassName:Connection
 **/

public class Connection {

    public static  ZooKeeper  getConnection() throws Exception {
//watch的注册只发生在读类型调用,get,exists
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        //new ZooKeeper(ip地址，多个   连接时间    Watch    );  这个Watch是session级别的，与path和node没有关系
        final ZooKeeper zooKeeper = new ZooKeeper("192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181,192.168.150.14:2181",
                3000, new Watcher() {
            //回调方法，可以查看连接事件的类型和状态
            @Override
            public void process(WatchedEvent event) {
                System.out.println("这个是注册时候的watch");
                //事件路径
                String path = event.getPath();
                System.out.println(path);
                //事件状态
                Event.KeeperState state = event.getState();
                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("SyncConnected");
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;
                };
                //事件类型
                Event.EventType type = event.getType();
                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
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
                };
            }
        });

        //到这里阻塞,直至回到完成,即连接成功：因为zookeeper快速的返回一个对象，还在creating中，还没有created好，需要等待返回created的对象
        countDownLatch.wait();

        //连接状态
        ZooKeeper.States keeperState = zooKeeper.getState();
        switch (keeperState) {
            case CONNECTING:
                System.out.println("ing ......");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed......");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }
       return  zooKeeper;
    }
}
