package com.why.jin.jvm;

/**
 * @author whyJin
 * dateTime 2021-05-13-21-29
 *
 * 1 JVM系统架构图
 * 2 类加载器
     System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
     System.out.println(myObject.getClass().getClassLoader().getParent());
     System.out.println(myObject.getClass().getClassLoader());
     2.1 有哪几种类加载器
     2.2 双亲委派
     2.3 沙箱安全机制
   3 Native
     3.1 native是一个关键字
     3.2 声明有，实现无，why？
   4 PC寄存器
     4.1记录了方法之间的调用和执行情况，类似排版值日表
        用来存储指向下一条指令的地址，也即将要执行的指令代码
        它是当前线程所执行的字节码的行号指示器
   5 方法区
      5.1 它存储了每一个类的结构信息
      5.2 方法区是规范，在不同虚拟机里头实现是不一样的，最典型的就是永久代（PermGen space）和元空间（Metaspace）

      空调 k1 = new 格力（）
      List list= newArrayList();

     方法区 f = new 永久代
     方法区 f= new 元空间

    6 stack
      栈管运行，堆管存储

 */
public class JVMNote {

    public void sayHello(){

    }

    public static void air(){

    }

    public static void main(String[] args) {
        JVMNote j1 = new JVMNote();
    }

}
