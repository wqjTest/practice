package com.why.jin.algoritthm.choose;

/**
 * @author Jin
 * dateTime 2021-08-16-15:57
 * 选择排序
 * 时间复杂度是：n平方
 * 空间复杂度：1
 * 稳定性：不稳
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5,3,6,8,1,7,9,4,2};

        for (int i=0;i<arr.length-1;i++){
            int minPos = i;

            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[minPos]){
                    minPos = j;
                }
//                minPos = arr[j]<arr[minPos]?j:minPos;
            }
            System.out.println("minPos:"+minPos);

            //值交换
            swap(arr,i,minPos);

            System.out.println("经过第"+i+"次循环之后，数组的内容");
            print(arr);
        }
    }

    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void print(int[] arr){
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
    }

    public static void sort(int[] arr) {
        for (int i=0;i<arr.length-1;i++){
            int minPos = i;

            for (int j=i+1;j<arr.length;j++){
                /*if (arr[j]<arr[minPos]){
                    minPos = j;
                }*/
                minPos = arr[j]<arr[minPos]?j:minPos;
            }
            System.out.println("minPos:"+minPos);

            //值交换
            swap(arr,i,minPos);

            System.out.println("经过第"+i+"次循环之后，数组的内容");
            print(arr);

        }
    }
}
