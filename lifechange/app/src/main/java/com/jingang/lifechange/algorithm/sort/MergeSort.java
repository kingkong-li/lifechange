package com.jingang.lifechange.algorithm.sort;

import java.util.Arrays;

/**
 * 合并排序
 */
public class MergeSort implements Sort {

    @Override
    public int[] ascendingSort(int[] intArray) {
        mergeSort(intArray);
        return intArray;
    }

    /**
     * 归并排序 时间复杂度分析
     * 首先递归-采用二分的来说 总共有logn层
     * 每一层，比如最底层 是两个数 合并 需要每个都遍历一遍
     * 最上层 是两个数组 合并 也是每个数组遍历一遍
     * 所以 合并数组是O(n)时间复杂度，
     * 故总的时间复杂度 O(nlogn)
     * @param array
     */
    public  void mergeSort(int[] array){

        int length=array.length;

        int middle=length/2;

     //   最后就是长度为2的数组排序好
        if(length>1){
            //拷贝数组array的左半部分
            int[]left=Arrays.copyOfRange(array,0,middle);
            //拷贝数组array的右半部分
            int[]right= Arrays.copyOfRange(array,middle,length);
            //递归array的左半部分
            mergeSort(left);
            //递归array的右半部分
            mergeSort(right);
            //数组左半部分、右半部分合并到Array
            merge(array,left,right);
        }
    }

    private  void merge(int[] result,int[] left,int[] right){

        int i=0; // 指向当前结果数组的第一个
        int l=0; // 指向左数组第一个
        int r=0;

//        当左边数组 和右边数组都有值的时候 ，结果数组 每次需要从对应的位置 选择最小的 选择完毕相应的数组指针+1
//         直到一个数组 遍历结束 ，证明另一个数组的值 都是比现在copy到新数组的值 是大的
//         那么直接复制过来就好了
        while(l<left.length&&r<right.length){
            if(left[l]<right[r]){
                result[i]=left[l];
                i++;
                l++;
            }else{
                result[i]=right[r];
                i++;
                r++;
            }
        }

        //如果右边剩下合并右边的
        while(r<right.length){
            result[i]=right[r];
            r++;
            i++;
        }

        while(l<left.length){
            result[i]=left[l];
            l++;
            i++;
        }
    }

    @Override
    public int[] descendingSort(int[] intArray) {
        return new int[0];
    }
}
