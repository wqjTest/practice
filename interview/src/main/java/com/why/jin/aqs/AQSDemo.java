package com.why.jin.aqs;

import java.util.concurrent.TimeUnit;
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
        ReentrantLock lock = new ReentrantLock(true);
        //带入一个银行办理业务的案例来模拟我们的AQS如何进行线程的管理和通知唤醒机制

        //3个线程模拟3个来银行网点，受理窗户办理业务的顾客

        //A 顾客就是第一个顾客，此时受理窗口没有任何人，A可以直接去办理。
        new Thread(()->{
            lock.lock();
            try{
                System.out.println("----A thread come in");
                //暂停几秒钟线程
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        },"A").start();

        //第二个顾客，第二个线程---->，由于受理业务的窗口只有一个（只能一个线程持有锁），此时B只能等待
        //进入候客区
        new Thread(()->{
            lock.lock();
            try{
                System.out.println("----B thread come in");
            }finally {
                lock.unlock();
            }
        },"B").start();

        //第三个顾客，第三个线程---->，由于受理业务的窗口只有一个（只能一个线程持有锁），此时C只能等待
        //进入候客区
        new Thread(()->{
            lock.lock();
            try{
                System.out.println("----C thread come in");
            }finally {
                lock.unlock();
            }
        },"C").start();

    }

}
