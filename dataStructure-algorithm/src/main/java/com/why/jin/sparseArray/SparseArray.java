package com.why.jin.sparseArray;

/**
 * @author whyJin
 * dateTime 2021-04-27-23-00
 * description:稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
       //创建一个原始的二维数组11*11
       //0: 表示没有棋子，1表示：黑子2表示：篮子
       int chessArr1[][] = new int[11][11];
       chessArr1[1][2] = 1;
       chessArr1[2][3] = 2;
       //输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组 转 稀疏数组
        //1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0;i <chessArr1.length;i++){
            for (int j =0;j<chessArr1.length;j++){
                if (chessArr1[i][j] !=0){
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组

    }
}
