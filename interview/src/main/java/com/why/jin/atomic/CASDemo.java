package com.why.jin.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jin
 * dateTime 2021-07-22-17:24
 * 1 CAS是什么？===>compareAndSet
 *   比较并交换
 *
 * cas 缺点：如果cas失败，会一直进行尝试。如果cas长时间一直不成功，可能会给CPU带来很大的开销
 *          当对于一个共享变量执行操作时，我们可以使用循环cas的方式来保证原子性操作，但是 对多个共享变量操作时，循环cas就无法保证操作的原子性，这个时候就可以用锁来保证原子性。
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        atomicInteger.getAndIncrement();

        //main do thing...
        System.out.println(atomicInteger.compareAndSet(5, 2021)+"\t current data: "+atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data: "+atomicInteger.get());
    }
}
