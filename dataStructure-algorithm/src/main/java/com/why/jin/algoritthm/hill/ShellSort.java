package com.why.jin.algoritthm.hill;

/**
 * @author Jin
 * dateTime 2021-08-18-12:35
 * 希尔排序 是插入排序的优化版
 *
 * 1
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {9,6,11,3,5,12,8,7,10,15,14,4,1,13,2};

        sort(arr);

        print(arr);
    }

    public static void sort(int[] arr) {

        /*int gap = 4;

        for (int i = gap;i<arr.length;i++){
            for (int j = i;j>gap-1;j-=gap){
                if (arr[j] < arr[j-gap]){
                    swap(arr,j,j-gap);
                }
            }
        }*/

        /*for (int gap = 4;gap>0;gap/=2){
            for (int i = gap;i<arr.length;i++){
                for (int j = i;j>gap-1;j-=gap){
                    if (arr[j] < arr[j-gap]){
                        swap(arr,j,j-gap);
                    }
                }
            }
        }*/

        /*for (int gap = arr.length/2;gap>0;gap/=2){
            for (int i = gap;i<arr.length;i++){
                for (int j = i;j>gap-1;j-=gap){
                    if (arr[j] < arr[j-gap]){
                        swap(arr,j,j-gap);
                    }
                }
            }
        }*/

        int h = 1;
        while (h <= arr.length/3){
            h = h*3+1;
        }
        for (int gap = h;gap>0;gap =(gap-1)/3){
            for (int i = gap;i<arr.length;i++){
                for (int j = i;j>gap-1;j-=gap){
                    if (arr[j] < arr[j-gap]){
                        swap(arr,j,j-gap);
                    }
                }
            }
        }

    }

    static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void print(int[] arr) {
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }
    }
}
