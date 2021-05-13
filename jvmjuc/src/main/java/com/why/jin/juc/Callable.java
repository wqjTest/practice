package com.why.jin.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements java.util.concurrent.Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("******come in call method()");
        return 1024;
    }
}

/**
 * @author Jin
 * dateTime 2021-05-13-10:16
 */
public class Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask,"A").start();
        Integer result = futureTask.get();
        System.out.println(result);
    }

}
