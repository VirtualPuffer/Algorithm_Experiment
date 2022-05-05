package experiment04;

import util.Number;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Console {
    public static void main(String[] args) {
        //int[] mud = Number.getRandom(1001,100,false);
        int[] mud = {6,1,4,9,8,5};
        int length = mud.length;
        int all = 0;
        for(Integer get : mud){
            all += get;
        }
        int[][] arr = new int[length+3][length+3];
        //金币一定时，A尽可能让数字更大，B尽可能让数字更小
        int used = 0;
        int ret = 0;
        while(used <= length){
            for(int i = 1;i<=used;i++){
                int less = length-used+i-1;
                if(used % 2 == 0){
                    arr[i][less] = max(arr[i-1][less]+mud[i-1],arr[i][less+1]+mud[less]);
                }else{
                    arr[i][less] = min(arr[i-1][less],arr[i][less+1]);
                }
                ret = max(ret,arr[i][less]);
            }
            used++;
        }
        System.out.println(ret);
        System.out.println(all - ret);
    }
}
