package com.why.jin.jvm.classLoader;

/**
 * @author Jin
 * dateTime 2021-05-13-14:00
 * 1 什么是类加载器：
 *    负责加载class文件，class文件在文件开头有特定的文件标示，
 *   将class文件字节码内容加载到内存中，并将这些内容转换成方法区中的
 *   运行时数据结构并且ClassLoader只负责class文件的加载，至于它是否
 *   可以运行，则由Execution Engine决定
 * 2 有几种：
 *   （1）虚拟机自带的加载器
 *    启动类加载器（Bootstrap）C++
 *    扩展类加载器（Extension）Java
 *    应用程序加载器（AppClassLoader）
 *    java也叫系统类加载器，加载当前应用的classpath的所有类
 *   （2）用户自定义的加载器
 *    Java.lang.ClassLoader的子类，用户可以定制类的加载方式
 * 3 双亲委派机制：保证沙箱安全
 * 4 沙箱安全
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
    }

}
