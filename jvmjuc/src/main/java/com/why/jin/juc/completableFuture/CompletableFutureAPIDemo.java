package com.why.jin.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-9 16:52   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() ->{

            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}

            return "abc";
        });

//        System.out.println(completableFuture.get());

//        System.out.println(completableFuture.get(2, TimeUnit.SECONDS));

//        System.out.println(completableFuture.join());

//        System.out.println(completableFuture.getNow("xxx"));

        TimeUnit.SECONDS.sleep(2);

        System.out.println(completableFuture.complete("completable") + "\t" + completableFuture.join());
    }
}
