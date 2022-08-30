package com.why.jin.dataStructure.linkedList;

import java.util.Stack;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-8-30 19:28   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */

//演示栈Stack的基本使用
public class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
