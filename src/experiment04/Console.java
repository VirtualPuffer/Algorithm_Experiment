package experiment04;

import util.Number;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Console {
    public static void main(String[] args) {
        int[] mud = {6,1,4,9,8,5};
        mud = Number.getRandom(4000,10,false);
        int length = mud.length;
        int all = 0;
        for(Integer get : mud){
            all += get;
        }
        int[][] arr = new int[length][length];
        int[][] cjj = new int[length][length];
        for(int i = 0;i<mud.length;i++){
            cjj[i][i] = mud[i];
            for(int j = i+1;j<mud.length;j++){
                cjj[i][j] = cjj[i][j-1] + mud[j];
            }
        }
        //金币一定时，A尽可能让数字更大，B尽可能让数字更小
        int ret = 0;
        int step = 1;
        for(int i = 0;i<mud.length;i++){
            if(mud.length % 2 == 0){
                arr[i][i] = mud[i];
            }
        }
        while (step<mud.length){
            //System.out.println(step);
            for(int i = 0;i+step<mud.length;i++){
                int  j = i+step;
                if(mud[i] + arr[i+1][j] > mud[j] + arr[i][j-1]){
                    if((mud.length - step )% 2 == 0){
                        arr[i][j] = mud[i] + arr[i+1][j];
                    }else {
                        //arr[i][j] = arr[i][j-1];
                        if(mud[i] + cjj[i+1][j] -  arr[i+1][j] > mud[j] + cjj[i][j-1] - arr[i][j-1]){
                            arr[i][j] = arr[i+1][j];
                        }else{
                            arr[i][j] = arr[i][j-1];
                        }
                    }
                }else {
                    if((mud.length - step) % 2 == 0){
                        arr[i][j] = mud[j] + arr[i][j-1];
                    }else {
                        //arr[i][j] = arr[i][j-1];
                        if(mud[i] + cjj[i+1][j] -  arr[i+1][j] > mud[j] + cjj[i][j-1] - arr[i][j-1]){
                            arr[i][j] = arr[i+1][j];
                        }else{
                            arr[i][j] = arr[i][j-1];
                        }
                    }
                }
            }
            step++;
        }
        ret = arr[0][mud.length-1];
        System.out.println(ret);
        System.out.println(all - ret);
    }
}
