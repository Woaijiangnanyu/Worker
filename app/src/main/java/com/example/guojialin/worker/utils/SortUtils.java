package com.example.guojialin.worker.utils;

public class SortUtils {

    //{2,6,3,5,4,1,8,45,2};
    public static int[] quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
        return list;
    }

    public static int[] quickSort(int[] list, int first, int last) {
        if (first < last) {//递归地对主元（pivot）前后的数组进行快排
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
        return list;
    }


    public static int partition(int[] list, int first, int last) {//操作过程见下图
        int pivot = list[first], low = first + 1, high = last;
        //寻找前半数组中大于主元的元素下标和后半数组中小于或等于主元的元素下标
        while (high > low) {
            while (pivot >= list[low] && low <= high)
                low++;
            while (pivot < list[high] && low <= high)
                high--;
            //交换两个元素
            if (low < high) {
                int tmp = list[low];
                list[low] = list[high];
                list[high] = tmp;
            }
        }
        //插入主元进适当位置
        while (list[high] >= pivot && high > first)
            high--;
        if (list[high] < pivot) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
        }
    }

}
