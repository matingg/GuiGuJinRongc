package com.mashaoting.guigujinrong.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 麻少亭 on 2017/3/12.
 * 四种线程池
 * newCachedThreadPool
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * <p>
 * newFixedThreadPool
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
 * <p>
 * newScheduledThreadPool
 * 创建一个定长线程池，支持定时及周期性任务执行
 * <p>
 * newSingleThreadExecutor
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行
 */

public class ThreadPool {

    private ThreadPool() {
    }

    ;


    private static ThreadPool threadPool = new ThreadPool();

    public static ThreadPool getThreadPool() {
        return threadPool;
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread() {
        return executorService;
    }
}
