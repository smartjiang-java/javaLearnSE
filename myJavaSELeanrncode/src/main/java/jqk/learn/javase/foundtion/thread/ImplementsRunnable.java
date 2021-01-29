package jqk.learn.javase.foundtion.thread;

/**
 * ClassName:ImplementsRunnable
 * Package:jqk.learn.javaSE.test.thread
 * Description:
 *
 * @Date:2020/10/16
 * @Author:JQK
 **/

/*1:implements runnable接口，并重写run()方法*/
public class ImplementsRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
