package com.why.jin.algoritthm.merge;

/**
 * @author Jin
 * dateTime 2021-08-20-13:04
 * 归并排序
 */
public class MergeSort2 {
    public static void main(String[] args) {
        int[] arr = {1,4,7,8,3,6,9};

        sort(arr,0,arr.length-1);

        print(arr);

    }

    public static void sort(int[] arr,int left,int right) {
        if (left == right){
            return;
        }
        //分成两半
        int mid = left + (right-left)/2;
        //左边排序
        sort(arr,left,mid);
        //右边排序
        sort(arr,mid+1,right);
//        merge(arr,0,4,arr.length-1);
        merge(arr,left,mid+1,right);
    }

    public static void merge(int[] arr,int leftPtr,int rightPtr,int rightBound) {
        int mid = rightPtr-1;
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound){
            if (arr[i] < arr[j]){
//                temp[k] = arr[i];
//                i++;
//                k++;
                temp[k++] = arr[i++];
            }else {
//                temp[k] = arr[j];
//                j++;
//                k++;
                temp[k++] = arr[j++];
            }

//            temp[k++] = arr[i] < arr[j] ? arr[i++]:arr[j++];
        }
        while (i<=mid) temp[k++] = arr[i++];
        while (j<=rightBound) temp[k++] = arr[j++];

        for (int m=0;m<temp.length;m++) arr[leftPtr+m] = temp[m];
    }

    static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr) {
        for (int i =0;i<arr.length;i++){
            System.out.println(arr[i]+" ");
        }
    }


}
