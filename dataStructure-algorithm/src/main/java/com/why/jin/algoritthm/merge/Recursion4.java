package com.why.jin.algoritthm.merge;

/**
 * @author Jin
 * dateTime 2021-08-20-12:49
 * 递归
 */
public class Recursion4 {
    public static void main(String[] args) {
        System.out.println(f(10));
    }

    static long  f(int n){
        if (n<1){
            return -1;
        }
        if (n==1){
            return 1;
        }
        return n+f(n-1);
    }
}
