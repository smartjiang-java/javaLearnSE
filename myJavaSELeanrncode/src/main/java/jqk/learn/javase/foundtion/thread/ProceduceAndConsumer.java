package jqk.learn.javase.foundtion.thread;

/**
 * @Author:JQK
 * @Date:2020/11/25 10:08
 * Description:
 * ClassName:ProceducesAndConsumer
 * Package:jqk.learn.javaSE.test.thread
 * <p>
 * 生产者和消费者模型(MQ就这鸟样)
 * 生产者-仓库-消费者（notify,wait）
 * 使用对象锁：这种方式有缺陷，消费者可能唤醒消费者，生产者可能唤醒生产者
 **/

public class ProceduceAndConsumer {

    /**
     * 定义锁对象
     */
    private static final Object OBJECT = new Object();
    /**
     * 定义仓库初始数量
     */
    static int responsity = 0;

    /**
     * 判断仓库是否为空，if empty return true then return false
     *
     * @return boolean
     */
    private static boolean isEmply() {
        return responsity == 0;
    }

    /**
     * 判断仓库是否满了，if full return true then return false
     *
     * @return boolean
     */
    private static boolean isFull() {
        return responsity == 10;
    }

    public static void main(String[] args) {
        new Thread(new Producer(), "生产者1").start();
        new Thread(new Consumer(), "消费者1").start();
        new Thread(new Producer(), "生产者2").start();
        new Thread(new Consumer(), "消费者2").start();
    }

    /**
     * 生产者类
     */
    static class Producer implements Runnable {
        @Override
        public void run() {
            //死循环，让一直运行
            while (true) {
                synchronized (OBJECT) {
                    //需要判断仓库是否满了,通知消费者消费，并且让自己阻塞
                    if (isFull()) {
                        try {
                            //刷屏太快，睡眠一会
                            Thread.sleep(1000);
                           // notify();
                            //wait();
                            //motify（）本来事项唤醒一个消费者，但是有可能会唤醒一个生产者
                            OBJECT.notify();
                            OBJECT.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "仓库容量：" + responsity);
                        responsity++;
                    }
                }
            }
        }
    }

    /**
     * 消费者类
     */
    static class Consumer implements Runnable {
        @Override
        public void run() {
            //死循环，让一直运行
            while (true) {
                synchronized (OBJECT) {
                    //需要判断仓库是否为空,通知生产者消费，并且让自己阻塞
                    if (isEmply()) {
                        try {
                            //刷屏太快，睡眠一会
                            Thread.sleep(1000);
                            // notify();
                            // wait();
                            //为什么这么做？  必须获取锁之后才能释放，得到锁之后才能阻塞
                            OBJECT.notify();
                            OBJECT.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "仓库容量：" + responsity);
                        responsity--;
                    }
                }
            }
        }
    }

}
