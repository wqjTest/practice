package com.why.jin.dataStructure.stack;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:                                 <br>
 * 修改日期           修改人员       版本       修改内容<br>
 * -------------------------------------------------<br>
 * 2022-9-9 16:53   qianjin.wang     1.0       初始化创建<br>
 * </p>
 *
 * @author qianjin.wang
 * @version 1.0
 * @since JDK1.8
 */

/**
 * 使用栈完成表达式的计算思路
 * 1、通过一个index值（索引），来遍历我们的表达式
 * 2、如果我们发现是一个数字，就直接入数栈
 * 3、如果发现扫描到是一个符号，就分如下情况
 * 3.1、如果发现当前的符号为空，就直接入栈
 * 3.2、如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，在从符号栈中pop出一个符号，进行运算，
 * 将得到结果，入数栈，然后将当前的操作符入符号栈。如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
 * 4、当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行。
 * 5、最后在数栈只有一个数字，就是表达式的结果
 * <p>
 * 验证：3+2*6-2=？
 */
public class Calculator {

    public static void main(String[] args) {
        //根据前面思路，完成表达式的运算
        String expression = "3+2*6-2";
        //创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch
        //开始循环的扫描expression
        while (true) {
            //依次得到expression 的每一个字符
            /*ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operatorStack.isOperator(ch)) { //如果是运算符
               //判断当前的符号栈是否为空
                if (operatorStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数，
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                         num1 = numStack.pop();
                         num2 = numStack.peek();
                         operator = operatorStack.pop();
                         res = numStack.cal(num1,num2,operator);
                         //把运算结果入数栈
                         numStack.push(res);
                         //然后当前的操作符入符号栈
                         operatorStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operatorStack.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operatorStack.push(ch); // 1 + 3
                }
            }else {
                //如果是数，则直接入数栈
                numStack.push(ch - 48); //? "1+3" "1" =>1
            }
            //让index + 1,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }*/
        }

    }
}

class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就放在该数组
    private int top = -1;//top表示栈顶，初始化-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //可以返回当前栈顶的值，但是不是真正的pop
    public int peek() {
        return stack[top];
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
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d \n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    /*public int priority(char operator) {
        if (operator == "*" || operator == "/") {
            return 1;
        } else if (operator == "+" || operator == "-") {
            return 0;
        } else {
            return -1; //假定目前的表达式只有+,-,*,/
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char var) {
        return var == "+" || var == "-" || var == "*" || var == "/";
    }

    //计算方法
    public int cal(int num1, int num2, int operator) {
        int res = 0;//res 用于存放计算结果
        switch (operator) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num2 / num1;
                break;
        }
        return res;
    }*/
}