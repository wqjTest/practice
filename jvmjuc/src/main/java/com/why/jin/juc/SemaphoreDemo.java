package com.why.jin.juc;

import java.util.concurrent.Semaphore;

/**
 * 信号量主用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 * 争车位
 */
public class SemaphoreDemo {

    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i=1;i<=6;i++){
           new Thread(()->{
               try {
                   semaphore.acquire();
                   System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                   Thread.sleep(3000);
                   System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   semaphore.release();
               }
           },String.valueOf(i)).start();
        }
    }
}
