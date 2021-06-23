package com.jingang.lifechange.algorithm.struct.array;

/**
 * @Description: 给定一个数组 nums，将 0 移动到数组的最后面，非零元素保持原数组的顺序
 * 要求：1.必须在原数组上操作 2.最小化操作数
 * @Author: jingang.Li
 * @CreateTime: 2021/6/23-11:26 AM
 * @changeTime:
 */
class MoveZero {
    /**
     * 重新排序后，前面0-k个都是非0，后面的都是0
     * 采用两个指针，一个指针 指向前面非0后，第一个数字，一个负责遍历，遇到非零的 就跟第一个指针
     * 指向的值进行交换，然后第一个指针+1  遍历完毕
     * @param array
     * @return
     */
    public void moveZeros(int[] array){
        int zeroP=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==0){
                continue;
            }
            swap(array,zeroP,i);
            i++;
        }
    }

    private void swap(int[] array,int i, int j) {
        if(i==j){
            return;
        }
        int tem=array[i];
        array[i]=array[j];
        array[j]=tem;
    }

}
