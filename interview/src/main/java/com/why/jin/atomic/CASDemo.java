package com.why.jin.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jin
 * dateTime 2021-07-22-17:24
 * 1 CAS是什么？===>compareAndSet
 *   比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5,0);
    }
}
