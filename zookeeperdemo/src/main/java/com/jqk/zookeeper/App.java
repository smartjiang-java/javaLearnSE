package com.jqk.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        //watch的注册只发生在读类型调用,get,exists
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final ZooKeeper zooKeeper = new ZooKeeper("192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181,192.168.150.14:2181",
                3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                event.getPath();
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
                }
                ;
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
                }
                ;
            }
        });

        //到这里阻塞,直至回到完成,即连接成功
        countDownLatch.wait();
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
        //做增
        zooKeeper.create("/xxoo","olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //查
        final Stat stat = new Stat();
        byte[] keeperData = zooKeeper.getData("ooxx", new Watcher() {
            //数据发生变,调用这个watch,一次性的
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData watch"+watchedEvent.toString());
                //调完继续回调
                try {
                    //true的时候,是defaule的watch被注册
                    //不使用default,改成this即可
                    //zooKeeper.getData("/ooxx",true,stat);
                    zooKeeper.getData("/ooxx",this,stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println(new String(keeperData));
        //改:第一次会触发回调
        Stat stat1 = zooKeeper.setData("/ooxx", "newdata".getBytes(), 0);
        zooKeeper.setData("/ooxx","newdata01".getBytes(),stat1.getVersion());

        //异步
        System.out.println(" async start-------");
        zooKeeper.getData("/ooxx",false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println(" async call back-------");
                System.out.println(new String(bytes));
            }
        },"abc");
        System.out.println(" async end-------");

    }
}
