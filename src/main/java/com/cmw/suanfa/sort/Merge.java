package com.cmw.suanfa.sort;

import java.util.Arrays;

/**
 * @description: 归并排序 两种思路 sortArray 和 sortArray2
 * @author: cmw
 * @data: 2021/8/30
 */
public class Merge {

    public static void main(String[] args) {
        int[] s = new int[]{6,3,2,7,1,3,5,4};
        sortArray(s);
    }


    public static int[] sortArray(int[] nums) {
        sort(0, nums.length - 1, nums);
        return nums;
    }

    // 分：递归二分
    private static void sort(int l, int r, int[] nums) {
        if (l >= r) return;

        int mid = (l + r) / 2;
        sort(l, mid, nums);
        sort(mid + 1, r, nums);
        merge(l, mid, r, nums);
    }


    public int[] sortArray2(int[] nums) {
        int n = nums.length;
        // sz= 1,2,4,8 ... 排序
        for (int sz = 1; sz < n; sz *= 2) {
            // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
            for (int i = 0; i < n - sz; i += 2*sz ) {
                merge(i, i + sz - 1, Math.min(i+sz+sz-1, n-1), nums);
            }
        }
        return nums;
    }


    // 治：将nums[l...mid]和nums[mid+1...r]两部分进行归并
    private static void merge(int l, int mid, int r, int[] nums) {
        int[] aux = Arrays.copyOfRange(nums, l, r + 1);

        int lp =l, rp = mid + 1;

        for (int i = lp; i <= r; i ++) {
            if (lp > mid) {                // 如果左半部分元素已经全部处理完毕
                nums[i] = aux[rp - l];
                rp ++;
            }  else if (rp > r) {          // 如果右半部分元素已经全部处理完毕
                nums[i] = aux[lp - l];
                lp ++;
            } else if (aux[lp-l] > aux[rp - l]) {     // 左半部分所指元素 > 右半部分所指元素
                nums[i] = aux[rp - l];
                rp ++;
            } else {                                  // 左半部分所指元素 <= 右半部分所指元素
                nums[i] = aux[lp - l];
                lp ++;
            }
        }
    }
}
