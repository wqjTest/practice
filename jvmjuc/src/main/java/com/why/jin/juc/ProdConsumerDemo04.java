package com.why.jin.juc;

class AirCondition {
    private int number = 0;

    public synchronized void increment() throws Exception {
        //1 判断
        while (number != 0){
            this.wait();
        }
        //2 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3 通知
        this.notifyAll();

    }

    public synchronized void decrement() throws Exception {
        //1 判断
        while (number == 0){
            this.wait();
        }
        //2 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3 通知
        this.notifyAll();
    }
}

/**
 * @author Jin
 * dateTime 2021-05-12-16:19
 * description:
 * 题目：现在两个线程，可以操作初始值为零的一个变量
 * 实现一个线程对变量加一，一个线程对该变量减1
 * 实现交替，来10轮，变量初始值为零
 *
 *
 *
 * 1 高内聚低耦合前提下，线程操作资源类
 * 2 判断/干活/通知
 * 3 防止虚假唤醒 要用while 不能用if
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i=1;i<=10;i++){
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }

}
