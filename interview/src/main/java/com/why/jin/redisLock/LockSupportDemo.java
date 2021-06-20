package com.why.jin.redisLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:LockSupper
 * 线程唤醒的方法：
 * 方式一：使用Object中的wait()方法让线程等待，使用Object中的notify()方法唤醒线程
 * 方式二：使用JUC包中Condition的await方法让线程等待，使用signal()方法唤线程
 * 方式三：LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程 park unpark
 * @author whyJin
 * dateTime 2021-06-19-21-04
 */
public class LockSupportDemo {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //synchronizedWaitNotify();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"----被唤醒");
            }finally {
                lock.unlock();
            }
        },"A").start();
        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"\t"+"----通知");
            }finally {
                lock.unlock();
            }
        },"B").start();
    }

    private static void synchronizedWaitNotify() {
        new Thread(()->{
            synchronized (objectLock){
                System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
                try {
                    objectLock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"----被唤醒");
            }
        },"A").start();

        new Thread(()->{
            synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"\t"+"----通知");
            }
        },"B").start();
    }

}
