package com.why.jin.algoritthm.leetcode.string;

/**
 * @author Jin
 * dateTime 2021-06-08-10:52
 * description:无重复字符的最长子串
 * 示例1：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class RepeatedString {

    public static int lengthOfLongestSubstring(String s){
        //记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i=0;i<128;i++){
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0;//窗口开始位置
        for (int i=0;i<n;i++){
            int index = s.charAt(i);
            start = Math.max(start,last[index]+1);
            res = Math.max(res,i-start+1);
            last[index]=i;
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");
    }
}
