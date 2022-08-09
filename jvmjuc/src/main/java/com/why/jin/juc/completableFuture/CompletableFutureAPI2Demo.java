package com.why.jin.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * (
 * thenApply:计算结果存在依赖关系，这两个线程串行化
 *            由于存在依赖关系（当前步错，不走下一步），当前步骤有异常的话就叫停。
 *  handle:有异常也可以往下一步走，根据带的异常参数可以进一步
 * )
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-9 20:07   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class CompletableFutureAPI2Demo {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() ->{
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("111");
            return 1;
        },pool).handle((f,e) ->{
//            int i = 10/0;
            System.out.println("222");
            return f+2;
        }).thenApply(f ->{
            System.out.println("333");
            return f+3;
        }).whenComplete((v,e) ->{
            if (e == null) {
                System.out.println("----计算结果："+v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName()+"----主线程忙其他任务");
        pool.shutdown();
    }
}
