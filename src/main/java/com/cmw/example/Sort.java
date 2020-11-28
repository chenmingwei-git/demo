package com.cmw.example;

public class Sort {
    public static void main(String[] args) {

        //冒泡排序
        int[] ss={2,1,34,45,56};
        for(int i=0;i<ss.length-1;i++){
            for(int j=0;j<ss.length-1-i;j++){

                if(ss[j]>ss[j+1]){
                    int temp = ss[j];
                    ss[j]=ss[j+1];
                    ss[j+1]=temp;
                }
            }
        }
        System.out.println();
        for(int s:ss){
            System.out.println(s+" ");
        }

        //简单的选择排序
        int[] mm={1,5,4,45,67,35};
        for(int i=0;i<mm.length-1;i++){
            int k=i;
            for(int j=k+1;j<mm.length;j++){
                if(mm[j]<mm[k]){
                    k=j;
                }
            }
            if(i!=k){
                int temp =mm[i];
                mm[i]=mm[k];
                mm[k]=temp;

            }
        }

    }

}
