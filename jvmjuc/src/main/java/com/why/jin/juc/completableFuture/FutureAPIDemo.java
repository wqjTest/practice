package com.why.jin.juc.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-4 18:49   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 * desc:
 * 1、get容易导致阻塞，一般建议放到程序后面，一旦调用不见不散，非要等到结果才会离开，不管你是否计算完成，容易程序堵塞
 * 2、假如我不愿意等待很长时间，我希望过时不候，可以自动离开
 *
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() ->{

            System.out.println(Thread.currentThread().getName() +"\t ----come in");
            //暂停几秒线程
            TimeUnit.SECONDS.sleep(5);

            return "take over";
        });
        Thread thread = new Thread(futureTask,"t1");
        thread.start();

        // System.out.println(futureTask.get()); //不见不散，非要等到结果才会离开，不管里是否计算完成，容易程序阻塞

        // System.out.println(futureTask.get(3,TimeUnit.SECONDS));

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            }else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中，不要催了，越催越慢，再催熄火");
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t ----忙其他任务了");

    }
}
