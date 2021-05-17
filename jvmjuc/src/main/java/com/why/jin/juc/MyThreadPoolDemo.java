package com.why.jin.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第四种获得/使用java多线程的方式，线程
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

    }

    private static void threadPoolInit() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个处理线程
        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i=1;i<=10;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
           threadPool.shutdown();
        }
    }
}
