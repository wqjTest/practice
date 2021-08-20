package com.why.jin.algoritthm.merge;

/**
 * @author Jin
 * dateTime 2021-08-20-13:04
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1,4,7,8,3,6,9};

        sort(arr);

    }

    public static void sort(int[] arr) {
        merge(arr);
    }

    public static void merge(int[] arr) {
        int mid = arr.length / 2;
        int[] temp = new int[arr.length];

        int i = 0;
        int j = mid+1;
        int k = 0;

        while (i <= mid && j < arr.length){
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
        while (j<arr.length) temp[k++] = arr[j++];
        print(temp);
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
