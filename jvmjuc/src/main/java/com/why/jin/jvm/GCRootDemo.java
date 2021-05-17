package com.why.jin.jvm;

/**
 * GC垃圾回收算法4种：
 *                 引用计数
 *                 复制
 *                 标记清除
 *                 标记整理
 *
 * 枚举根节点做可达性分析：
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象；
 * 2.方法区中的类静态属性引用的对象
 * 3.方法区中常量引用对象
 * 4.本地方法栈中JNI（即一般说的Native方法）中引用的对象
 *
 * 落地实现
 * 四种主要垃圾回收的方式：
 * Serial（串行）：它为单线程环境设计且只使用一个线程进行垃圾回收，会暂停所有的用户线程。所以不适合服务器环境
 * parallel（并行）：多个垃圾收集线程并行工作，此时用户线程是暂停的，适用于科学计算/大数据处理首台处理等弱交互场景
 * CMS(并发)：用户线程和垃圾线程同时执行（不一定是并行，可能交替执行），不需要停顿用户线程
 *           互联网公司多用它，适用对响应时间有要求的场景
 * G1：
 *
 * java的gc回收的类型主要几种：
 * UseSerialGC
 * UseParallelGC
 * UseConcMarkSweepGC
 * UseParNewGC
 * UseParallelOldGC
 * UseG1Gc
 *
 * Yong Gen:Serial Copying  parallel Scavenge  ParNew
 * ----------------------------------------------------G1
 * Old Gen:Serial MSC（Serial Old） Parallel Compacting(Parallel Old) CMS
 *
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100*1024*1024];
//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
