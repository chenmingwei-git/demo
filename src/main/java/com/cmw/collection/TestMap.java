package com.cmw.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: HashMap原理研究
 * @author: cmw
 * @data: 2020/7/27
 */
public class TestMap {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        Map str = new HashMap(12);

        int a = tableSizeFor(20);
    }

    /** hashMap初始化原理 :得到大于等于当前传入值的最小2的整次幂
     * 在一开始进行减一的操作是为了防止出现二的整数幂时，没有把自身包含进范围！
     * 例如:传入的cap为8时得到的结果为16 而减一之后得到的结果为8
     * 多次无符号右移位的原理 再进行 (|) 运算的原理是拼出低位全为1的二进制数据
     * 最后再加1得到2的整次幂后返回
     *
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
