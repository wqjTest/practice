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
      6.1 栈管运行，堆管存储
      6.2 栈保存哪些东东？
       8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配
      6.3 Exception in thread "main" java.lang.stackOverflowError
    7 heap
      Eden满了，开启
        GC = YGC = 轻GC
        Eden基本全部清空

       S0 = from
       S1 = to

       交换 ？？？？不用搞懂，2次明白
       from区和to区，他们的位置和名分，不是固定，每次GC后会交换
       GC之后有交换，谁空谁是to

 */
public class JVMNote {

    public void sayHello(){

    }

    public static void air(){

    }

    public int add(int x,int y){
        int result = -1;
        result = x + y;
        return result;
    }

    public static void main(String[] args) {
        JVMNote j1 = new JVMNote();
    }

}
