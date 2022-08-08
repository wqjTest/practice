package com.why.jin.juc.completableFuture;

import java.util.concurrent.*;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-8 20:12   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() ->{
            System.out.println(Thread.currentThread().getName() + "----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("----1秒钟后出结果：" + result);
            return result;
        }).whenComplete((v,e) ->{
            if (e == null) {
                System.out.println("----计算完成，更新系统update:"+v);
            }
        }).exceptionally(e ->{
            e.printStackTrace();
            System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭：暂停3秒钟线程
        TimeUnit.SECONDS.sleep(3);

    }

    private static void future1() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("----1秒钟后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "线程先忙其它任务了");

        System.out.println(completableFuture.get());
    }
}
