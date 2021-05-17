package com.why.jin.juc;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                100L,
                 TimeUnit.SECONDS,
                 new LinkedBlockingDeque<>(3),//等待区
                 Executors.defaultThreadFactory(),
                 new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 1;i<=8;i++){//模拟6个顾客来办理业务，受理窗口max只有5个
                final int tmpl = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"号窗口，"+"服务顾客"+tmpl);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
