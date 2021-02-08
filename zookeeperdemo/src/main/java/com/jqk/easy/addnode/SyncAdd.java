package com.jqk.easy.addnode;

import com.jqk.easy.connection.Connection;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @Author:JQK
 * @Date:2021/2/8 16:26
 * @Package:com.jqk.use.addnode
 * @ClassName:SyncAdd
 * 同步：阻塞阻到数据，完成后断开连接

 **/

public class SyncAdd {

    public static void addNode() throws Exception {
        //获取Zookeeper连接
        final ZooKeeper  zk = Connection.getConnection();

        //同步增加节点
        //zooKeeper.create(节点目录   数据   访问控制    四种节点类型(持久，临时))  返回值是节点目录
        String pathName = zk.create("/ooxx", "oldDate".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        //查节点数据
        //zooKeeper.getData( 节点目录   Watch   Stat)
        final Stat stat = new Stat();
        byte[] keeperData = zk.getData("ooxx", new Watcher() {
            //此后这个节点数据发生变化,才会调用这个watch,一次性的
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("这个是检测节点数据变化的watch");
                System.out.println("getData watch" + watchedEvent.toString());
                //如果想监控完成后继续检测，则只需要继续监控
                try {
                    zk.getData("ooxx", this,stat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println(new String(keeperData));

        //修改数据:会调用上面的那个关注节点数据的Watch
        Stat stat1 = zk.setData("/ooxx", "newDate".getBytes(), 0);

        //修改数据:因为Watch是一次性的，所以第二次修改不会触发
        zk.setData("/ooxx","newDate2".getBytes(), stat1.getVersion());

        //根据打印日志验证一系列东西：模拟一个zookeeper挂掉
        Thread.sleep(300000);

    }

    @Test
    public void myTest() throws Exception {
        SyncAdd.addNode();
    }


}




