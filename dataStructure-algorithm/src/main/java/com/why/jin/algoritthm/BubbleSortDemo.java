package com.why.jin.algoritthm;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSortDemo {

    
    public static void main(String[] args) {
       int[] data = new int[]{7,2,6,1,5,8,9};
        System.out.println("排序前："+ Arrays.toString(data));
        System.out.println("排序后："+Arrays.toString(selectSort(data)));
    }

    private static int[] selectSort(int[] data) {
        for (int i=0;i<data.length;i++){
            boolean flag = false;
            for (int j=0;j<data.length-i-1;j++){
                if (data[j]>data[j+1]){
                    int tem;
                    /*tem = data[j+1];
                    data[j+1] = data[j];
                    data[j] = tem;*/
                    tem = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tem;
                    flag = true;
                }
            }
            if (!flag){
                return data;
            }
        }
        return data;
    }

}
