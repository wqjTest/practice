package com.why.jin.juc.completableFuture;

import java.util.concurrent.*;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-4 17:52   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //3个任务，目前开启多个异步任务线程来处理，请问耗时多少？
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "task1 over";
        });
        pool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "task2 over";
        });
        pool.submit(futureTask2);
        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "task3 over";
        });
        pool.submit(futureTask3);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        long endTime = System.currentTimeMillis();
        System.out.println("----costTIme:" + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ----end");
        pool.shutdown();



    }

    private static void m1() {
        //3个任务，目前只有一个main来处理，请问耗时多少？
        long startTime = System.currentTimeMillis();
        //暂停毫秒
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {throw new RuntimeException(e);}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {throw new RuntimeException(e);}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {throw new RuntimeException(e);}

        long endTime = System.currentTimeMillis();
        System.out.println("----costTIme:" + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ----end");
    }
}
