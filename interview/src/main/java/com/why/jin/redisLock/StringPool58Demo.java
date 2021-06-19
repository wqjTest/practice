package com.why.jin.redisLock;

/**
 * description:58同城的java字符串常量池
 *
 * 方法区和运行时常量池溢出
 *   由于运行时常量池是方法区的一部分，所以这两个区域的溢出测试可以放到一起进行。前面曾经提到HotSpot从
 * JDK 7开始逐步“去永久代”的计划，并在JDK 8中完全使用元空间来代替永久代的背景故事。
 *   String::intern()是一个本地方法，它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串，
 * 则返回代表池中这个字符串的String对象的引用；否则，会将此String对象包含的字符串添加到常量池中，并且返回此
 * String对象的引用。在JDK 6或更早之前的HotSpot虚拟机中，常量池都是分配在永久代中，我们可以通过-XX：PermSize和
 * -XX：MaxPermSize限制永久代的大小，即可间接限制其中常量池的容量。
 *
 * why:
 * 按照代码结果，java字符串答案为false
 * 必然是两个不同的java，那另外一个java字符串如何加载进来的？
 *
 *  System代码解析：System->initializeSystemClass(sun.misc.Version.init())->Version(private static final String launcher_name = "java";)
 * 来源：类加载器和rt.jar
 *       OpenJdk8源码
 * @author Jin
 * dateTime 2021-06-18-15:16
 */
public class StringPool58Demo {
    public static void main(String[] args) {
        String str1 = new StringBuilder("ali").append("baba").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        System.out.println();

        /**
         * JDK 7 的intern()方法实现就不需要在拷贝字符串的实列到永久代，
         * 既然字符串常量池已经移到Java堆中，那只需要在常量池里记录一下
         * 首次出现的实列引用即可，因此intern()返回的引用和由StringBuilder创建的
         * 那个字符串实列就是同一个。而对str2比较返回false，这是因为“java”这个字符串在执行
         * String-Builder.toString()之前就已经出现过了，字符串常量池中已经有它的引用，不符合
         * intern()方法要求“首次遇到”的原则，“计算机软件”这个字符串则是首次出现的，因此结果返回true
         */
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());
    }

}
