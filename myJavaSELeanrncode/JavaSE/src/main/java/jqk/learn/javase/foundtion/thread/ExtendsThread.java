package jqk.learn.javase.foundtion.thread;

/**
 * ClassName:ExtendsThread
 * Package:jqk.learn.javaSE.test.thread
 * Description:
 *
 * @Date:2020/10/16
 * @Author:JQK
 **/

/*1:extebds Thread类，并重写run()方法*/
public class ExtendsThread extends  Thread {

    /**
     * 查看源码方法：
     * 1：先看类上的批注信息，了解类如何使用，按ctrl+F12.或者alt+7对类方法有一个总览
     * 2：根据你调用的方法一步一步的追即可
     *
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
