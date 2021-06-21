package com.why.jin.redisLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:LockSupper
 * 原理：LockSupport是用来创建锁和其他同步类的基本线程阻塞原语
 *      LockSupport是一个线程阻塞工具类，所有的方法都是静态方法，可以让线程在任意位置阻塞，阻塞之后也有对应的唤醒方法。
 *      归根结底，LockSupport调用的unsafe中的native代码
 *      LockSupport提供park()和unpark()方法实现阻塞线程和解除线程阻塞的过程
 *      LockSupport和每个使用它的线程都有一个许可(permit)关联。permit相当于1,0的开关，默认是0
 *      调用一次unpark就加1变成1，
 *      调用一次park就会消费permit，也就是将1变成0，同时park立即返回。
 *      如再次调用park会变成阻塞（因为permit为零了会阻塞在这里，一直到permit变为1），这时调用unpark会把permit置为1。
 *      每个线程都有一个相关的permit，permit最多只有一个，重复调用unpark也不会积累凭证
 * 形象的理解：
 *      线程阻塞需要消耗凭证（permit），这个凭证最多只有1个。
 *      当调用park方法时：
 *      如果有凭证，则会直接消耗掉这个凭证然后正常退出。
 *      如果无凭证，就必须阻塞等待凭证可用。
 *      而unpark则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效。
 * 面试题：
 *       为什么可以先唤醒线程后阻塞线程？
 *       因为unpark获得一个凭证，之后在调用park方法，就可以名正言顺的凭证消费，故不会阻塞。
 *       为什么唤醒两次后阻塞两次，但最终结果还会阻塞线程？
 *       因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证；而调用两次park却需要消费两个凭证，证不够，不能放行。
 *
 * 线程唤醒的方法：
 * 方式一：使用Object中的wait()方法让线程等待，使用Object中的notify()方法唤醒线程
 * 方式二：使用JUC包中Condition的await方法让线程等待，使用signal()方法唤线程
 * 方式三：LockSupport类可以阻塞当前线程以及唤醒指定被阻塞的线程
 *
 * public static void park(){
 *     UNASFE.park(false,0L)
 * }
 * permit默认是0，所以一开始调用park()方法，当前线程就会阻塞，直到别的线程将当前线程的permit设置为1时，park方法会被唤醒
 * 然后会将permit再次设置为0并返回
 * @author whyJin
 * dateTime 2021-06-19-21-04
 */
public class LockSupportDemo {
    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        //synchronizedWaitNotify();
        //ConditionAwaitSignal();
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //sleep方法3秒后醒来，执行park无效，没有阻塞效果，解释如下
        //先执行unpark(t1)导致上面的park方法形同虚设无效，时间一样
       Thread a = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            LockSupport.park();//被阻塞....等待通知等待放行，它要通过需要许可证
            System.out.println(Thread.currentThread().getName()+"\t"+"----被唤醒");
        },"a");
       a.start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       Thread b = new Thread(()->{
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+"\t"+"----通知");
        },"b");
        b.start();
    }

    private static void ConditionAwaitSignal() {
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
