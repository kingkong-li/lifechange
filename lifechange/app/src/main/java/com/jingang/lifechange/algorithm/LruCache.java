package com.jingang.lifechange.algorithm;

import java.util.HashMap;

/**
 * @Description:Lru 缓存实现
 * @Author: jingang.Li
 * @CreateTime:2020/8/31-9:34 AM
 * @changeTime:
 */
public class LruCache {
    private HashMap<Integer,DLinkedNode> map=new HashMap<>();
    private int mCapacity;
    private int size;
    private DLinkedNode head;
    private DLinkedNode tail;


    public LruCache(int capacity){
        this.size = 0;
        this.mCapacity = capacity;

    }
    public int get(int key){
        DLinkedNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;

    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    public void put(int key,int value){
        DLinkedNode node = map.get(key);
        if(node==null){

            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            map.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            size++;
            if(size>mCapacity){
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                map.remove(tail.key);
                --size;
            }


        }else{
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }


    }

    private DLinkedNode removeTail() {
        DLinkedNode trueTail = tail.pre;
        removeNode(trueTail);
        return trueTail;
    }

    private void removeNode(DLinkedNode dLinkedNode) {
        dLinkedNode.pre.next=dLinkedNode.next;
        dLinkedNode.next.pre=dLinkedNode.pre;
    }

    private void addToHead(DLinkedNode newNode) {
        newNode.pre = head;
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;

    }

    class DLinkedNode{
        DLinkedNode pre;
        DLinkedNode next;
        int key;
        int value;
        public DLinkedNode(int key,int value){
            this.key=key;
            this.value=value;
        }
        public DLinkedNode(){

        }
    }
}
