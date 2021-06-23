package com.jingang.lifechange.algorithm.struct.linkedList;

import android.util.Log;

import com.jingang.lifechange.algorithm.struct.linkedList.bean.MyLinkedList;
import com.jingang.lifechange.algorithm.struct.linkedList.bean.SingleListNode;

/**
 * @Description: 基本链表操作
 * @Author: JinGang.Li
 * @CreateTime:2021/5/27-8:20 AM
 */
public class BaseListOperation {
    private static final String TAG=BaseListOperation.class.getSimpleName();
    /**
     * 反转单链表
     * @param head
     * @return
     */
    public static SingleListNode reverseList(SingleListNode head) {
        //pre指针指向已经反转好的链表的最后一个节点，最开始没有反转，所以指向nullptr
        SingleListNode pre = null;
        //cur指针指向待反转链表的第一个节点，最开始第一个节点待反转，所以指向head
        SingleListNode currentNode = head;
        // next 保存未反转列表下一个节点，因为待反转的反转后 会丢失后面的信息，所以next是保存用
        SingleListNode next = null;
        while (currentNode != null) {
            //  保存下一个节点
            next = currentNode.next;
            // 反转当前列表
            currentNode.next = pre;
            // pre 指向已经反转好的列表头
            pre = currentNode;
            // 进行下一轮反转
            currentNode = next;

        }
        return pre;

    }

    /**
     * 反转单链表的一种实现，使用一个虚拟的节点
     * 从头部取，每次取来的节点，指向上次最后的节点，但是上次最后的节点
     * 怎么保存呢，需要的是一个dumy节点来指向它，这样就是每次从原始列表取节点
     * 插入于 dumyNode和已经翻转的列表之间
     * @param head
     * @return
     */
    public static SingleListNode reverseListUseDummyNode(SingleListNode head){
        //构建一个始终指向头的指针-dummyNode
        SingleListNode dummyNode=new SingleListNode(0);

        while (head!=null){
            SingleListNode next=head.next;
            head.next=dummyNode.next;
            dummyNode.next=head;
            head=next;
        }

        return dummyNode.next;

    }

    /**
     * 测试反转列表的方法
     */
    public static void testReverseList(){
        // 构建链表start
        SingleListNode head=new SingleListNode(0);
        SingleListNode currentNode=head;
        for(int i=1;i<10;i++){
            SingleListNode listNode=new SingleListNode(i);
            currentNode.next=listNode;
            currentNode=listNode;
        }
        //构建链表end head就是头指针
        Log.v(TAG,"testReverseList 原始的list="+ BaseListOperation.getListString(head));
        //反转链表
        SingleListNode reversedList=  BaseListOperation.reverseList(head);

        Log.v(TAG,"testReverseList 反转后"+"list="+ BaseListOperation.getListString(reversedList));
        SingleListNode reversedListAgain=BaseListOperation.reverseListUseDummyNode(reversedList);
        Log.v(TAG,"testReverseList 通过虚拟头节点 再次翻转"+"list="+ BaseListOperation.getListString(reversedListAgain));


    }

    /**
     *  返回链表的String对象
     * @param head 链表头指针
     * @return 一个String对象
     */
    public static String getListString(SingleListNode head){
        SingleListNode iCur=head;
        String list="{";
        while(iCur!=null){
            list=list+" "+iCur.val;
            iCur=iCur.next;
        }
        list=list+"}";
        return  list;

    }

    public static void TestDoubleLinkList(){
        MyLinkedList<Integer> linkedList=new MyLinkedList<>();
        for(int i=0;i<100;i++){
            linkedList.addAtHead(i);
        }
        Log.v(TAG,"TestDoubleLinkList linkedList:"+linkedList.toString());
        linkedList.deleteAtIndex(2);
        Log.v(TAG,"TestDoubleLinkList after delete index 2 linkedList="+linkedList.toString());

        Log.v(TAG,"TestDoubleLinkList after delete index 2 linkedList[2]="+linkedList.getNodeValue(2).toString());
        linkedList.addAtIndex(3,10000);
        Log.v(TAG,"TestDoubleLinkList linkedList="+linkedList.toString());
    }

    /**
     * 合并两个已经是升序排序的单链表
     * 链表合并的关键 在于遍历完毕这两个链表
     * 还要构造一个头节点
     * 一般做法还要构造一个尾巴节点 指向合并后新链表的最后一个
     * 头一般构造完毕就不会变化，关键就是根据两个链表值 选择哪一个作为尾部节点
     * @param head1
     * @param head2
     * @return
     */
    private static SingleListNode mergeTwoAscSingleListNode(SingleListNode head1,SingleListNode head2){
        SingleListNode newHead=new SingleListNode(0);
        SingleListNode newTail=newHead;
        while(head1!=null && head2!=null){
            if(head1.val<head2.val){
                newTail.next=head1;
                head1=head1.next;
            }else{
                newTail.next=head2;
                head2=head2.next;
            }
            newTail=newTail.next;
        }
        newTail.next=(head1!=null)?head1:head2;
        return newHead.next;

    }

    private static SingleListNode mergeTwoAscListNode(SingleListNode head1,SingleListNode head2){
       SingleListNode dummyHead=new SingleListNode(0);
       assignTailNext(head1,head2,dummyHead);
       return dummyHead.next;

    }

    /**
     * 通过给当前节点 一直递归赋值next 最终把l1 和l2 都给遍历完毕
     * @param l1
     * @param l2
     * @param tail
     */
    private static void assignTailNext(SingleListNode l1,SingleListNode l2,SingleListNode tail){
        if(l1==null || l2==null){
            tail.next= l1!=null? l1:l2;
            return;
        }
        if(l1.val<l2.val){
            tail.next=l1;
            assignTailNext(l1.next,l2,tail.next);
        }else {
            tail.next=l2;
            assignTailNext(l1,l2.next,tail.next);
        }
    }



    public static void testMerge(){
        // 构建链表1 start
        SingleListNode head1=new SingleListNode(0);
        SingleListNode currentNode=head1;
        for(int i=1;i<10;i++){
            SingleListNode listNode=new SingleListNode(2*i);
            currentNode.next=listNode;
            currentNode=listNode;
        }
        //构建链表end head就是头指针
        Log.v(TAG,"head1="+getListString(head1));
        // 构建链表2 start
        SingleListNode head2=new SingleListNode(0);
        SingleListNode currentNode1=head2;
        for(int i=1;i<10;i++){
            SingleListNode listNode=new SingleListNode(2*i-1);
            currentNode1.next=listNode;
            currentNode1=listNode;
        }
        //构建链表end head就是头指针
        Log.v(TAG,"head2="+getListString(head2));

        SingleListNode newHead=mergeTwoAscSingleListNode(head1,head2);
        Log.v(TAG,"newHead="+getListString(newHead));
        //使用递归很容易产生 递归栈溢出的问题  这个问题下 发现
        // java.lang.StackOverflowError: stack size 8MB 虚拟机给每个线程栈分配8M
        SingleListNode newHead1=mergeTwoAscListNode(head1,head2);
        Log.v(TAG,"使用递归 newHead="+getListString(newHead));

    }
}
