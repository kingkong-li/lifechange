package com.jingang.lifecyclecase.utils;

/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/13-6:03 PM
 * @changeTime:
 */
public interface Sort {

    /**
     * 对数组进行排序-升序
     * @param intArray  排序前的数组
     * @return 排序后的数组
     */
    int[] ascendingSort(int[] intArray);


    /**
     * 对数组进行排序-升序
     * @param intArray  排序前的数组
     * @return 排序后的数组
     */
    int[] descendingSort(int[] intArray);
}
