package com.why.jin.juc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyData {

    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalField.set(1 + threadLocalField.get());
    }
}

/**
 * @author whyJin
 * dateTime 2022-07-17-15-51
 * desc [强制]必须回收定义的ThreadLocal变量，尤其在线程池场景下，线程经常会被恢复用，如果不清理
 * 自定义的ThreadLocal变量，可能会影响后续业务逻辑和造成内存泄漏等问题。尽量在代理中使用try-finally块进行回收
 *
 * 面试题：thread->threadLocal->threadLocalMap
 *         threadLocalMap实际上就是一个以ThreadLocal实列为key，任意对象为value的entry对象
 */
public class ThreadLocalDemo2 {


    public static void main(String[] args) {

        final MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before = myData.threadLocalField.get();
                        myData.add();
                        Integer after = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + "\t" + "before：" + before + "\t after:" + after);
                    } finally {
                        myData.threadLocalField.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

