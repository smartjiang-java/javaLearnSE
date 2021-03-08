package jqk.learn.javase.foundtion.thread.threadpool;

import java.util.concurrent.*;

/**
 * @Author:JQK
 * @Date:2020/11/24 17:07
 * Description:
 * ClassNmae:ThreadPool
 * Package:jqk.learn.javase.test.thread.threadpool
 **/

public class ThreadPool {

    /**
     * 创建一个线程池:无法运行，因为Thread类里面的target对象为执行的真正对象，
     * 而一个线程创建之后，调用start()之后除非我们停止或者执行完毕或者抛出异常，才能停止。
     * <p>
     * Executors创建的线程池要么等待队列是LinkedList，要么是最大线程数为Integer.MAX_VALUE，会有太多的线程被创建，造成OOM
     */

    public static void main(String[] args) {

        //RejectedExecutionException异常
        //ExecutorService

        //自定义线程池的七个参数
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
