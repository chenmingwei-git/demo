package com.cmw.suanfa.sort;


/**
 * @description: 归并排序 两种思路 sortArray 和 sortArray2
 * @author: cmw
 * @data: 2021/8/30
 */
public class Merge {

    public static void main(String[] args) {
        /**
         * 归并排序中的单组排序
         */
        int[] arr = new int[]{14, 12, 15, 13, 11, 16, 10};
        int[] newArr = sort(arr, new int[7], 0, arr.length - 1);

        /**
         * 将两个有序数组合并为一个有序数组
         */

        int[] arr1 = new int[]{2, 3, 6, 8};
        int[] arr2 = new int[]{1, 4, 5, 7};
        int[] newArrs = sort(arr1, arr2);

    }

    /**
     * 归并排序之单个数组排序
     *
     * @param arr
     * @param result
     * @param start
     * @param end
     * @return
     */
    public static int[] sort(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return null;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        sort(arr, result, start1, end1);
        sort(arr, result, start2, end2);
        int k = start;
        //进行比较。注意这里++是后执行的，先取出来数组中的值然后++
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        //将每个分组剩余的进行复制
        while (start1 <= end1)
            result[k++] = arr[start1++];
        //将每个分组剩余的进行复制
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
        return result;
    }


    /**
     * 归并排序之两个有序数组合并为一个
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] sort(int[] arr1, int[] arr2) {
        int[] newArr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {

                newArr[k] = arr1[i];
                i++;
                k++;
            } else {

                newArr[k] = arr2[j];
                j++;
                k++;
            }
        }

        while (i < arr1.length)
            newArr[k++] = arr1[i++];
        while (j < arr2.length)
            newArr[k++] = arr2[j++];
        return newArr;
    }

}
