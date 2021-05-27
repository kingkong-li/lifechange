package com.jingang.lifechange.algorithm.listoperation;

import android.util.Log;

import com.jingang.lifechange.algorithm.listoperation.bean.ListNode;

/**
 * @Description: 基本链表操作
 * @Author:
 * @CreateTime:2021/5/27-8:20 AM
 * @changeTime:
 */
public class BaseListOperation {
    private static final String TAG=BaseListOperation.class.getSimpleName();
    /**
     * 反转列表
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        //pre指针指向已经反转好的链表的最后一个节点，最开始没有反转，所以指向nullptr
        ListNode pre=null;
        //cur指针指向待反转链表的第一个节点，最开始第一个节点待反转，所以指向head
        ListNode currentNode=head;
        // next 保存未反转列表下一个节点，因为待反转的反转后 会丢失后面的信息，所以next是保存用
        ListNode next=null;
        while(currentNode!=null){
//            保存
            next=currentNode.next;
//          反转当前列表
            currentNode.next=pre;
            // pre 指向已经反转好的列表头
            pre=currentNode;
//            进行下一轮反转
            currentNode=next;

        }
        return pre;

    }

    /**
     * 测试反转列表的方法
     */
    public static void testReverseList(){
        // 构建链表start
        ListNode head=new ListNode(0);
        ListNode currentNode=head;
        for(int i=1;i<10;i++){
            ListNode listNode=new ListNode(i);
            currentNode.next=listNode;
            currentNode=listNode;
        }
        //构建链表end head就是头指针
        Log.v(TAG,"testReverseList 原始的list="+ BaseListOperation.getListString(head));
        //反转链表
        ListNode reversedList=  BaseListOperation.reverseList(head);
        Log.v(TAG,"testReverseList 反转后"+"list="+ BaseListOperation.getListString(reversedList));

    }

    public static String getListString(ListNode head){
        ListNode iCur=head;
        String list="{";
        while(iCur!=null){
            list=list+" "+iCur.val;
            iCur=iCur.next;
        }
        list=list+"}";
        return  list;

    }
}
