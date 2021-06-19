package com.why.jin.redisLock;

/**
 * description:LockSupper
 *
 * @author whyJin
 * dateTime 2021-06-19-21-04
 */
public class LockSupportDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
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
    }

}
