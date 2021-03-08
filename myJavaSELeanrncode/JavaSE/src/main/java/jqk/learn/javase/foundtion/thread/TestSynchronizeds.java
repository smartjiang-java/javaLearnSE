package jqk.learn.javase.foundtion.thread;

/**
 * @Author:JQK
 * @Date:2020/11/18 10:31
 * Description:
 * ClassName:TestSynchronizeds
 * Package:jqk.learn.javaSE.test.thread
 **/

public class TestSynchronizeds {

    /**
     * 在代码块上加，字节码体现为一个monitorenter和两个monitorexit
     */
    public void run() {
        synchronized (this) {
        }
    }

    /**
     * 再方法上加，字节码体现为ACC_SYNCHRONIZED
     */
    public synchronized void run2() {

    }

    public static void main(String[] args) {

    }

}
