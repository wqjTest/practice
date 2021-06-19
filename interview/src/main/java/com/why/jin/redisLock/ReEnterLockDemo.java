package com.why.jin.redisLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁
 * <p>
 * 在一个Synchronize修饰的方法或代码块的内部
 * 调用本类的其他Synchronize修饰的方法或代码块时，是永远可以得到锁的
 *
 * @author whyJin
 * dateTime 2021-06-19-13-50
 */
public class ReEnterLockDemo {
    /*static Object objectLockA = new Object();
    public static void m1(){
        new Thread(()->{
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName()+"\t"+"----外层调用");
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName()+"\t"+"----中层调用");
                    synchronized (objectLockA){
                        System.out.println(Thread.currentThread().getName()+"\t"+"---内层调用");
                    }
                }
            }
        },"t1").start();
    }

      public static void main(String[] args) {
          m1();
      }*/


    /*public synchronized void m1() {
        System.out.println("====外");
        m2();
    }

    public synchronized void m2() {
        System.out.println("====中");
        m3();
    }

    public synchronized void m3() {
        System.out.println("====内");
    }

    public static void main(String[] args) {
        new ReEnterLockDemo().m1();
    }*/

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try{
                System.out.println("====外层");
                lock.lock();
                try{
                    System.out.println("====内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t1").start();
    }
}
