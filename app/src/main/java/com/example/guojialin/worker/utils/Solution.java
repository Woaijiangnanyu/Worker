package com.example.guojialin.worker.utils;

public class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
       int len = A.length;
       int index = 0;
       int max = A[0] + A[0] ;
       while (index < len){
           for (int i = index; i < len - index; i++){
               for (int j = i;j < len - index ; j++){
                       int temp = A[i] + A[j] + (j - i);
                       if (max < temp){
                           max = temp;
                       }
                       System.out.println(max);
               }
           }
           index ++;
       }
       return max;
    }

    public  void change(int [] arr,int left,int right){
        int v = arr[right];
        int j = left -1;
        for (int i = left;i <= right;i++){
            if (arr[i]<=v){
                swap(arr,j+1,i);
                j++;
            }
        }
        swap(arr,right,j+1);
    }

    public  void swap(int [] arr,int  a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}