package jqk.learn.javase.foundtion.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:JQK
 * @Date:2020/11/25 15:30
 * Description:
 * ClassName:ProceduceAndConsumer2
 * Package:jqk.learn.javaSE.test.thread
 *
 *  通过ReentrantLock实现
 **/

public class ProceduceAndConsumer2 {

    /**
     * 定义锁对象
     */
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static Condition proceduceCondition = LOCK.newCondition();
    private static Condition consumerCondition = LOCK.newCondition();
    /**
     * 定义仓库初始数量
     */
    static int responsity = 0;

    public static void main(String[] args) {
        new Thread(new ProceduceAndConsumer.Producer(), "生产者1").start();
        new Thread(new ProceduceAndConsumer.Consumer(), "消费者1").start();
        new Thread(new ProceduceAndConsumer.Producer(), "生产者2").start();
        new Thread(new ProceduceAndConsumer.Consumer(), "消费者2").start();
    }

    /**
     * 生产者类
     */
    static class Producer implements Runnable {
        @Override
        public void run() {
            //死循环，让一直运行
            while (true) {
                LOCK.lock();
                try {
                    //需要判断仓库是否满了,通知消费者消费，并且让自己阻塞
                    if (responsity == 10) {
                        consumerCondition.signal();
                        proceduceCondition.await();
                    } else {
                        System.out.println(Thread.currentThread().getName() + "仓库容量：" + responsity);
                        responsity++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
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
                LOCK.lock();
                try {
                    //需要判断仓库是否为空,通知生产者消费，并且让自己阻塞
                    if (responsity == 0) {
                        proceduceCondition.signal();
                        consumerCondition.await();
                    } else {
                        System.out.println(Thread.currentThread().getName() + "仓库容量：" + responsity);
                        responsity--;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

}
