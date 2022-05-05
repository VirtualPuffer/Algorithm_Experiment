package experiment01;

import java.util.zip.CheckedInputStream;

public class FastSort {
    public void fast(int low,int height){

    }
    public static void fastSort(int low,int high,int[] get){
       // System.out.println(low + " "+ high);
        if(low >= high){
            return;
        }
        int num = get[low];
        int left = low;
        int right = high;
        while (left < right){
            while (left<right && get[right] >= num){
                right --;
            }
            get[left] = get[right];
            while (left < right && get[left] <= num){
                left++;
            }
            get[right] = get[left];
        }
        get[left] = num;
        fastSort(low,left-1,get);
        fastSort(left+1,high,get);
    }
}
