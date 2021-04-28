package com.why.jin.sparseArray;

import java.io.*;

/**
 * @author whyJin
 * dateTime 2021-04-27-23-00
 * description:
 * 二维数组 转 稀疏数组的思路
 * 1. 遍历  原始的二维数组，得到有效数据的个数 sum
 * 2. 根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
 * 3. 将二维数组的有效数据数据存入到 稀疏数组
 *
 * 稀疏数组转原始的二维数组的思路
 *
 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
 * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
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
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;//count 用于记录是第几个非0数据
        for (int i = 0;i <chessArr1.length;i++){
            for (int j =0;j<chessArr1.length;j++){
                if (chessArr1[i][j] !=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~");
        for (int i =0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
        try{
            System.out.println("创建/打开文档");
            File f = new File("D:\\sparse.txt");
            FileOutputStream f1 = new FileOutputStream(f);
            OutputStreamWriter outW = new OutputStreamWriter(f1,"UTF-8");
            System.out.println("写入中-------");
            for (int i=0;i<sparseArr.length;i++){
                outW.write(String.valueOf(sparseArr[i][0]));
                outW.write("，");
                outW.write(String.valueOf(sparseArr[i][1]));
                outW.write("，");
                outW.write(String.valueOf(sparseArr[i][2]));
                outW.write("，");
            }
            outW.close();
            f1.close();
            System.out.println("写入成功");
            System.out.println("读取中------");
            FileInputStream f2 = new FileInputStream(f);
            InputStreamReader inR = new InputStreamReader(f2,"UTF-8");
            StringBuffer sb = new StringBuffer();
            while (inR.ready()){
                sb.append((char)inR.read());
            }
            inR.close();
            f2.close();
            System.out.println("读取成功");
            String ss = sb.toString();
            String[] sb1 = sb.toString().split(",");
            System.out.println("读取数据字符串为:");
            System.out.println(ss);
        }catch (Exception e){
            e.printStackTrace();
        }

        //3.将稀疏数组--》恢复成原始的二维数组
        /**
         *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
         *  2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
         */

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2. 在读取稀疏数组后几行的数据（从第二行开始），并赋给 原始的二维数组 即可.
         for (int i=1;i<sparseArr.length;i++){
             chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
         }
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row: chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
