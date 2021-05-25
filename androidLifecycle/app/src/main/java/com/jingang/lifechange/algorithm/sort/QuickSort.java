package com.jingang.lifechange.algorithm.sort;

/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/13-6:10 PM
 * @changeTime:
 */
public class QuickSort implements Sort {
    @Override
    public int[] ascendingSort(int[] intArray) {


        quickSortKernel(intArray, 0, intArray.length - 1);

        return intArray;
    }

    private void quickSortKernel(int[] s, int left, int right) {
        if (left < right) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = left, j = right, x = s[left];
            // 一次循环就是把大于基准点的 放基准点右边 小于基准点的放基准点左边 start
//             while循环 可以把大于基准点的和小于基准点的 进行交换
            while (i < j) {
//                从右向左找第一个比基准点小的数
                while (i < j && s[j] > x) {
                    j--;
                }

                while (i < j && s[i] <= x) {
                    i++;
                }

                if (i < j) {
                    int swap = s[j];
                    s[j] = s[i];
                    s[i] = swap;
                }

            }

//          这里是把现在基准点交换到中间位置，因为当前基准点 是最左边那个点，所以应该是s[left] 和s[i]交换值
            int swap1 = s[i];
            s[i] = s[left];
            s[left] = swap1;
            quickSortKernel(s, left, i - 1);
            quickSortKernel(s, i + 1, right);
        }

    }

    @Override
    public int[] descendingSort(int[] intArray) {
        return new int[0];
    }
}
