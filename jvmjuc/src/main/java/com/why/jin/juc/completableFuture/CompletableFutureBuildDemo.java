package com.why.jin.juc.completableFuture;

import java.util.concurrent.*;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-8 12:53   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class CompletableFutureBuildDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() ->{
            System.out.println(Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
        },pool);
        System.out.println(completableFuture.get());*/

        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() ->{
            System.out.println(Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            return "hello supplyAsync";
        });
        System.out.println(completableFuture.get());
        pool.shutdown();
    }

}
