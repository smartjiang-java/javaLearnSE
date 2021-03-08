package jqk.learn.javase.foundtion.iostream;

import java.io.FileOutputStream;

/**
 * @Author:JQK
 * @Date:2020/12/11 15:04
 * Description:
 * ClassName:FileOutputStream_Demo
 * Package:jqk.learn.javase.test.io
 **/

public class FileOutputStream_Demo {

    public static void main(String[] args) throws Exception {
        //FileOutputStream 从内存对磁盘  参数：接受数据的位置
        FileOutputStream fileOutputStream = new FileOutputStream("");
        //写入一个数组数据，从起点到尾端,写入一部分
        fileOutputStream.write("public class Demo{xxx} ".getBytes());
        //一定要关流，否则可能会导致部分数据存留在缓冲区里面
        //如果没关，在JVM关闭的时候，会将缓冲区数据刷出
        fileOutputStream.close();
    }
}
