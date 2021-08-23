package com.why.jin.algoritthm.quick;

/**
 * @author Jin
 * dateTime 2021-08-23-13:55
 */
public class QuickSort {
    public static void main(String[] args) {
       int[] arr = {7,3,2,8,1,9,5,4,6};

       sort(arr,0,arr.length-1);

       print(arr);
    }

    public static void sort(int[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound) return;
        int mid =  partition(arr,leftBound,rightBound);
        sort(arr,leftBound,mid-1);
        sort(arr,mid+1,rightBound);
    }

    public static int partition(int[] arr, int leftBound, int rightBound) {
       int pivot = arr[rightBound];
       int left = leftBound;
       int right = rightBound-1;

       while (left <= right){
           while (left <= right && arr[left] <= pivot){
               left++;
           }
           while (left <= right && arr[right] > pivot){
               right--;
           }
           System.out.println("before swap:left->"+left+"right->"+right);
           if (left < right){
               swap(arr,left,right);
           }

           System.out.print("--");
           print(arr);
           System.out.println();
       }
       swap(arr,left,rightBound);

       return left;
    }

    static void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void print(int[] arr) {
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
