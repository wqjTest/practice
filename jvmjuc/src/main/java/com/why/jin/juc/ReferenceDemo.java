package com.why.jin.juc;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MyObject {
    //这个方法一般不用复写，我们只是为了教学给大家演示案例做说明
    @Override
    protected void finalize() throws Throwable{
        //finalize的通常目的是在对象被不可撤销丢弃之前执行清理操作
        System.out.println("------invoke finalize method");
    }
}

/**
 * @author whyJin
 * dateTime 2022-07-23-16-17
 *
 * 强引用：当内存不足，JVM开始垃圾回收，对于强引用的对象，就是出现了OOM也不会对该对象进行回收，死都不收
 * SoftReference：软引用是一种相对强引用弱化了一些的引用，可以让对象豁免一些垃圾收集
 *                对于只有软引用的对象来说
 *                当系统内存充足时它        不会  被回收
 *                当系统内存不足时它        会    被回收
 *                软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收！
 *
 * WeakReference：它比软引用的生存期更短
 *                对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收该对象占用的内存
 *
 *
 */
public class ReferenceDemo {
    public static void main(String[] args) {

        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
//        System.out.println(phantomReference.get());

        List<byte[]> list = new ArrayList<>();
        new Thread(() ->{
            while (true) {
                list.add(new byte[1 * 1204 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(() ->{
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("-----有虚对象回收加入了队列");
                }
            }
        },"t2").start();
    }

    public static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("----gc before 内存够用："+weakReference.get());

        System.gc();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("----gc after 内存够用："+weakReference.get());
    }

    public static void softReference(){
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());

        System.gc();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------gc after内存够用："+softReference.get());
        try {
            byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("----gc after内存不够："+softReference.get());
        }
    }

    public static void strongReference() {
         MyObject myObject = new MyObject();
        System.out.println("gc before:"+myObject);

        myObject = null;

        System.gc(); //人工开启GC，一般不用

        //暂停毫秒
        try{
          TimeUnit.MILLISECONDS.sleep(500);
        }catch(Exception e){
          e.printStackTrace();
        }
        System.out.println("gc after:"+myObject);
    }

}
