package com.why.jin.jvm;


/**
 * jps -l
 * jinfo -flag PrintGCDetails xxx GC收集细节
 * jinfo -flag MetaspaceSize 34836 元空间
 * jinfo -flag MaxTenuringThreshold xxx 多大的极限年龄升养老区
 *
 * 第一种，查看参数盘点家底
 * jinfo -flag 具体参数 java进程编号
 * jinfo -flags        java进程编号
 * 第二种，查看参数盘点家底
 *java -XX:+PrintFlagsInitial
 *
 * 主要查看修改更新
 * java -XX:+PrintFlagsFinal -version =(初始值) :=（更改值）
 * java -XX:+PrintFlagsFinal -XX:MetaspaceSize=512m T（运行的java类名字）
 *
 * 第三种，查看参数盘点家底
 * java -XX:+PrintCommandLineFlags -version
 *
 * 常用参数：
 *   -Xms 初始大小内存，默认为物理内存 1/64
 *        等价于-XX：InitialHeapSize
 *
 *   -Xmx 最大分配内存，默认为物理内存 1/4
 *        等价于-XX：MaxHeapSize
 *
 *   -Xss 设置单个线程栈的大小，一般默认为512k~1024k
 *        等价于-XX:ThreadStackSize
 *        备注：初始值和平台有关（linux/x64:1024KB,OS x64:1024KB,windows:The default value depends on virtual memory）
 *
 *   -Xmn 设置年轻代大小
 *
 *   -XX:MetaspaceSize
 *         备注：元空间的本质和永久代类似，都是对jvm规范中方法区的实现。
 *              不过元空间于永久代之间最大的区别在于：
 *              元空间并不在虚拟机中，而是使用本地内存。
 *              因此，默认情况下，元空间的大小仅受本地内存限制。
 *         -Xms10m -Xmx10m -XX:MetaspaceSize=1024m -XX:+PrintFlagsFinal
 *
 *   典型设置案例：
 *         -Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *         -XX:+UseSerialGC   串行垃圾回收器
 *         -XX:+UseParallelGC 并行垃圾回收器
 *
 *   -XX:+PrintGCDetails
 *        输出详细GC收集日志信息
 *
 *   -XX:SurvivorRatio
 *       设置新生代中Eden和S0/S1空间比例
 *       默认：-XX:SurvivorRatio=8,Eden:S0:S1=8:1:1
 *       假如：-XX:SurvivorRatio=4,Eden:S0:S1=4:1:1
 *       SurvivorRatio值就是设置eden区的比列占多少，S0/S1相同
 *
 *   -XX:NewRatio
 *       配置年轻代和老年代在堆结构的占比
 *       默认
 *       -XX:NewRatio=2新生代占1，老年代2，年轻代占整个堆的1/3
 *       假如
 *       -XX:NewRatio=4新生代占1，老年代4，年轻代占整个堆的1/5
 *       NewRatio值就是设置老年代的占比，剩下的1给新生代
 *
 *   -XX:MaxTenuringThreshold
 *       默认15
 *       设置垃圾最大年龄
 *
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中内存总量
        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        System.out.println("TOTAL_MEMORY(-Xms)="+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx)="+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");

        //cpu
        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println("*****HelloGC");
        Thread.sleep(Integer.MAX_VALUE);

    }
}
