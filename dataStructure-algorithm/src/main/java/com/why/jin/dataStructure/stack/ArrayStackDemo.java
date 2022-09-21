package com.why.jin.dataStructure.stack;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-9-7 19:41   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */

import java.util.Scanner;

/**
 * 实现栈的思路分析
 * 1、使用数组来模拟栈
 * 2、定义一个top来表示栈顶，初始化为-1
 * 3、入栈的操作，当有数据加入到栈时，top++;stack[top]=data;
 * 4、出栈的操作，int value = stack[top];top--;return value
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈（入栈）");
            System.out.println("pop: 表示从栈取出数据（出栈）");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    int res = stack.pop();
                    System.out.printf("出栈的数据是%d",res);
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序~！");
    }

}

//定义一个ArrayStack 表示栈
class ArrayStack {
    private int maxSize; //栈的大小

    private int[] stack; //数组，数组模拟栈，数据就放在该数组

    private  int top = -1;//top表示栈顶，初始化为-1；

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 -- push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 -- pop
    public int pop() {
        //判断栈是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~~");
            return;
        }
        //需要从栈开始显示数据
        for (int i = top;i>=0;i--) {
            System.out.printf("stack[%d]=%d \n",i,stack[i]);
        }
    }
}


