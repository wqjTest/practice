package com.why.jin.dataStructure.linkedList;

/**
 * @author whyJin
 * dateTime 2022-09-03-15-55
 */

/**
 * Josephu 约瑟夫问题：设编号为1,3,......n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，
 * 数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，知道所有人出列为止，由此产生一个
 * 出队编号的序列
 * n=5，即有5个人
 * k=1，从第一个人开始
 * m=2，数2下
 * <p>
 * 单向环形链表完成，约瑟夫问题
 * <p>
 * 构建一个单向的环形链表思路
 * 1、先创建第一个节点，让first指向该节点，并形成环形
 * 2、后面当我们没创建一个新的节点，就把该节点，加入到已有的环形链表中即可
 * <p>
 * 遍历环形链表
 * 1、先让一个辅助指针（变量）curBoy，指向first节点
 * 2、然后通过一个while循环遍历该环形链表即可curBoy.next = first结束
 */
public class Josepfu {

    public static void main(String[] args) {
        //测试一把看看构建环形链表，和遍历是否OK
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入五个小孩节点
        circleSingleLinkedList.showBoy();

    }
}

class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i=1;i<=nums;i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if ( i==1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~~");
            return;
        }
        //因为first不能动，因此我们任然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if(curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移
         }
    }

    //根据用户的输入，计算小孩出圈的顺序

}

class Boy {
    private int no;//编号
    private Boy next;//下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

/**
 * 根据用户输入，生成一个小孩出圈的顺序
 * n = 5,即有5个人
 * k = 1,从第一个开始报数
 * m = 2,数两下
 *
 * 1、需求创建一个辅助指针（变量）helper,事先应该指向环形链表的最后这个节点
 * 2、当小孩报数时，让first和helper指针同时移动m-1次
 * 3、这时就可以将first指向的小孩节点出圈first = first.next  helper。next = first 原来first指向的节点就没有任何引用，就会被回收
 */