package com.why.jin.algoritthm.insertion;

/**
 * @author Jin
 * dateTime 2021-08-17-15:53
 * 时间复杂度是：n平方
 * 空间复杂度：1
 * 稳定性：稳
 */
public class InsertionSort {
    public static void main(String[] args) {

        int[] a = {9,3,1,4,6,8,7,5,2};
        sort(a);
        print(a);
    }

    public static void sort(int[] a){
       for (int i = 1;i<a.length;i++){
           for (int j = i;j>0;j--){
               if (a[j] < a[j-1]){
                   swap(a,j,j-1);
               }
           }
       }
    }

    static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static void print(int[] a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]+" ");
        }
    }
}
