package com.why.jin.algoritthm.choose;

import com.why.jin.algoritthm.bubbling.BubbleSort;
import com.why.jin.algoritthm.hill.ShellSort;
import com.why.jin.algoritthm.insertion.InsertionSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Jin
 * dateTime 2021-08-17-13:32
 * 验证算法-队对数器
 */
public class DataChecker {
    static int[] generateRandomArray(){
        Random r = new Random();

        int[] arr = new int[10000];

        for (int i=0;i<arr.length;i++){
            arr[i] = r.nextInt(10000);
        }
        return arr;
    }

    static void  check(){
        int[] arr = generateRandomArray();
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr,0,arr2,0,arr.length);

        Arrays.sort(arr);
//        SelectionSort.sort(arr2);
//        BubbleSort.sort(arr2);
//        InsertionSort.sort(arr2);
        ShellSort.sort(arr2);

        boolean same = true;
        for (int i=0;i<arr2.length;i++){
            if (arr[i] != arr2[i]){
                same = false;
            }
        }
        System.out.println(same == true?"right":"wrong");
    }
    public static void main(String[] args) {
        check();
    }
}
