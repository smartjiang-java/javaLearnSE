package com.jqk.easy;

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

        //new ZooKeeper(ip地址，多个   连接时间    Watch    );  这个Watch是session级别的，与path和node没有关系
        final ZooKeeper zooKeeper = new ZooKeeper("192.168.150.11:2181,192.168.150.12:2181,192.168.150.13:2181,192.168.150.14:2181",
                3000, new Watcher() {
            //回调方法，可以查看连接事件的类型和状态
            @Override
            public void process(WatchedEvent event) {
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



        //拿到Zookeeper连接后
        //增加节点
        //zooKeeper.getData(节点目录   数据   访问控制    四种节点类型(持久，临时))
        zooKeeper.create("/xxoo","olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        //查
        final Stat stat = new Stat();
        //zooKeeper.getData( 节点目录   Watch   Stat)
        byte[] keeperData = zooKeeper.getData("ooxx", new Watcher() {
            //数据发生变,调用这个watch,一次性的
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData watch"+watchedEvent.toString());
                //调完继续回调
                try {
                    //true的时候,是defaule的watct, 被重新注册
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
        // 异步：zooKeeper.getData(节点目录   Watch    CallBack    CTX(随意))
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
