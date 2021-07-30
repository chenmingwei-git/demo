package com.cmw.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 力扣刷题
 * @author: cmw
 * @data: 2021/7/30
 */
public class LeetCode {

    public static void main(String[] args) {
        int s = lengthOfLongestSubstring("abcdcdaa");
        System.out.println(s);

    }

    /**
     * 两数之和 : 返回数组中两数之和为目标值的下标
     */
    static int[] sum(int[] num, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < num.length; i++) {
            if (map.containsKey(target - num[i])) {
                return new int[]{map.get(target-num[i]),i};
            }
            map.put(num[i],i);
        }

        return new int[]{-1, -1};
    }

    /**
     * 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     * 例如 : 字符串 abcdcdaa 返回的值为4
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

}
