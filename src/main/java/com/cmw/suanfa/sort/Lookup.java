package com.cmw.suanfa.sort;


/**
 * @description:  二分查找
 * @author: cmw
 * @data: 2021/8/11
 */

//二分查找
public class Lookup {
    public static void main(String[] args) {
        int[] m={1,2,3,4,5,6,7,8};
        int s=look(m,0,m.length-1,4);
        System.out.println(s);
        int ss= bootserach(m,4);
    }


    //递归实现
    static int look(int[] array,int start,int end,int key){
        int s=(end-start)/2+start;
        if(array[s]==key){
            return s;
        }
        if(start>end){
            return -1;
        }
        else if(key<array[s]){
            return look(array,start,s-1,key);
        }else if(key>array[s]){
            return look(array,s+1,end,key);
        }

        return -1;
    }

    //普通方法实现
    static int bootserach(int[] arr,int key){
        int mid=arr.length/2;
        if(arr[mid]==key){
            return key;
        }
        int start=0;
        int end =arr.length-1;
        while (start<end){
            mid =(end-start)/2+start;
            if(key<arr[mid]){
                end =mid-1;
            }else if(key>arr[mid]){
                start =mid+1;
            }else {
                return mid;
            }
        }

        return -1;
    }
}
