package com.why.jin.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class House {

    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    // 第一种匿名内部类
    /*ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/

    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);
    public void saleVolumeMyThreadLocal() {
        saleVolume.set(1+saleVolume.get());
    }
}

/**
 * @author whyJin
 * dateTime 2022-07-17-16-10
 * 需求1： 5个销售卖房子，集团高层只关心销售总量和准确统计数
 * 需求2：5个销售卖完随机数房子，各自独立销售额度，自己业绩按提成走，分灶吃饭，各自销售自己动手，丰衣足食
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        House house = new House();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
//                System.out.println(size);
                try {
                    for (int j = 0; j < size; j++) {
                        house.saleHouse();
                        house.saleVolumeMyThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+"号销售卖出："+house.saleVolume.get());
                }finally {
                    house.saleVolume.remove();
                }
            }, String.valueOf(i)).start();
        }

        //暂停3秒
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\t"+"共计卖出多少套："+house.saleCount);
    }
}
