package jqk.learn.javase.foundtion.thread;

import org.junit.Test;

/**
 * 多线程
 * ClassName:ThreadClass
 * Package:jqk.learn.javaSE.test
 * Description:
 *
 * @Date:2020/10/12
 * @Author:JQK
 **/

public class ThreadClass {

    @Test
    public void myTest() {
        /**
         * 线程执行没有顺序
         * 1：线程如何执行？
         *  创建线程：
         *  implements runnable接口，并重写run()方法
         *  extends Thread类，并重写run()方法
         */

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new ExtendsThread().start();
        new Thread(new ImplementsRunnable()).start();

    }
}
