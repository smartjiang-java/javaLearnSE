package com.jqk.easy.addnode;

import com.jqk.easy.connection.Connection;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @Author:JQK
 * @Date:2021/2/8 16:57
 * @Package:com.jqk.use.addnode
 * @ClassName:AsynAdd 异步：非阻塞取数据
 **/

public class AsynAdd {
    public static void addNode() throws Exception {
        //获取Zookeeper连接
        final ZooKeeper zk = Connection.getConnection();

        //异步增加节点
        //zooKeeper.create(节点目录   数据   访问控制    四种节点类型(持久，临时))  返回值是节点目录
        String pathName = zk.create("/ooxx", "oldDate".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        //查节点数据
        //zooKeeper.getData(节点目录   是否watch   非阻塞，回调方法   可以传参上下文   )  返回值是节点目录
        System.out.println("1111111111");
        zk.getData("/ooxx", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("333333333333");
                System.out.println(new String(bytes));
                System.out.println("传进来的参数："+o.toString());
            }
        }, "AAAA");
        System.out.println("2222222222");

    }

    @Test
    public void myTest() throws Exception {
        SyncAdd.addNode();
    }

}
