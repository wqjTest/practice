package com.why.jin.redisLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 *
 * ReentrantLock的加锁过程，可以分为三个阶段：
 * 1、尝试加锁
 * 2、加锁失败，线程入队列
 * 3、线程入队列后，进入阻塞状态。
 *
 * @author Jin
 * dateTime 2021-06-21-12:51
 */
public class AQSDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        //带入一个银行办理业务的案例来模拟我们的AQS如何进行线程的管理和通知唤醒机制

        //3个线程模拟3个来银行网点，受理窗户办理业务的顾客

    }

}
