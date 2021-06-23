package com.jingang.lifechange.algorithm.struct.linkedList.bean;

/**
 * @Description: 尝试自己实现LinkedList
 * @Author: jingang.Li
 * @CreateTime: 2021/5/31-9:35 AM
 * @changeTime:
 */
public class MyLinkedList<E> {
    public int size=0;
    public Node<E> head, tail;
    /**
     *定义linkedList节点
     *
     * @param <E>
     */
    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;
        Node(E value) {
            this.value = value;
        }
    }

    public MyLinkedList() {

    }

    /**
     * 在头部插入一个值 相当于栈的push
     * @param val 值
     */
    public void addAtHead(E val) {
        Node<E> node = new Node<>(val);
        // 修改指针，将节点插入对应位置
        if(head==null){
            addFirstNode(node);
        }else{
            Node<E> oldHead=head;
            head=node;
            head.next=oldHead;
            oldHead.prev=head;
            size++;
        }

    }
    public void addAtTail(E val) {
        Node<E> node = new Node<>(val);
        // 修改指针，将节点插入对应位置
        if(tail==null){
            addFirstNode(node);
        }else{
            Node<E> oldTail=tail;
            tail=node;
            tail.prev=oldTail;
            oldTail.next=tail;
            size++;
        }

    }
    private Node<E> getNode(int index){
        if(index<0 || index>size){
            return null;
        }
        Node<E> node=head;
        for(int i=0;i<index;i++){
            node=node.next;
        }
        return node;
    }

    /**
     * @param index  索引
     * @return 节点value
     */
    public E getNodeValue(int index){
        Node<E> node=getNode(index);
        if(node!=null){
            return node.value;
        }else {
            return null;
        }

    }

    /**
     * 在第index个节点前添加一个节点
     * @param index 索引
     * @param val 值
     * @return 返回
     */
    public boolean addAtIndex(int index, E val) {
        if(index<0 || index>size){
            return false;
        }
        if(index==0){
            addAtHead(val);
            return true;
        }else if(index==size){
            addAtTail(val);
            return true;
        }else {
            Node<E> oldNodeAtIndex=getNode(index);
            Node<E> pre= null;
            if (oldNodeAtIndex != null) {
                pre = oldNodeAtIndex.prev;
            }
            Node<E> curNode=new Node<>(val);
            curNode.prev=pre;
            curNode.next=oldNodeAtIndex;
            if (pre != null) {
                pre.next=curNode;
            }
            if (oldNodeAtIndex != null) {
                oldNodeAtIndex.prev=curNode;
            }
            return true;
        }

    }

    /**
     * 删除第index个节点，从0开始
     * @param index 序列号
     * @return false 操作失败 true操作成功
     */
    public boolean deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        // 处理头部
        if(index==0){
            head=head.next;
        }
        // 处理尾部删除
        if(index==size-1){
            tail=tail.prev;
        }
        // 处理中间
        if(index>0 && index<size-1){
            Node<E> waitDeleteNode = getNode(index);
            assert waitDeleteNode != null;
            Node<E> prev= waitDeleteNode.prev;
            Node<E> next= waitDeleteNode.next;
            prev.next = next;
            next.prev = prev;
        }
        size--;
        return true;
    }


    private void addFirstNode(Node<E> node){
        head=node;
        tail=node;
        size++;
    }

    @Override
    public String toString() {
        Node<E> iCur=head;
        StringBuilder builder = new StringBuilder();
        builder.append("size=").append(size).append(",");
        builder.append("list=");
        while(iCur!=null){
            builder.append(" ");
            builder.append(iCur.value);
            iCur=iCur.next;
        }
        return builder.toString();
    }
}
