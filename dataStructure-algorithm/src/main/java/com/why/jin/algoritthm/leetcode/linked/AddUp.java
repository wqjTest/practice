package com.why.jin.algoritthm.leetcode.linked;


import java.util.ArrayList;
import java.util.List;

//Definition for singly-linked list
class ListNode {
    public int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * @author Jin
 * dateTime 2021-06-08-13:32
 * description:两数相加
 * 示例1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
public class AddUp {

    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
       ListNode root = new ListNode(0);
       ListNode cursor = root;
       int carry = 0;
       while (l1 != null || l2 !=null || carry !=0){
           int l1Val = l1 != null ? l1.val :0;
           int l2Val = l2 != null ? l2.val :0;
           int sumVal = l1Val + l2Val + carry;
           carry = sumVal / 10;

           ListNode sumNode = new ListNode(sumVal % 10);
           cursor.next = sumNode;
           cursor = sumNode;

           if (l1 != null){
               l1 = l1.next;
           }
           if (l2 != null){
               l2 = l2.next;
           }
       }
       return root.next;
    }


    public static void main(String[] args) {
        ListNode demo1 = new ListNode();
        demo1.setVal(1);
        demo1.setNext(null);
        ListNode demo2 = new ListNode();
        demo2.setVal(2);
        demo2.setNext(null);
        addTwoNumbers(demo1,new ListNode(2,null));
    }
}







