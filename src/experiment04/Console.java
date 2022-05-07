package experiment04;

import util.Number;
import util.TimeTemplate;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Console {
    public static void main(String[] args) {
        TimeTemplate template = new TimeTemplate();
        for(int k = 0;k<20;k++){
            int[] mud = {6,1,4,9,8,5};
            //mud = Number.getRandom(2000,1000,false);
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
            boolean tag = true;
            while (step<mud.length){
                for(int i = 0;i+step<mud.length;i++){
                    int  j = i+step;
                    if((mud.length - step )% 2 == 0){
                        if(mud[i] + arr[i+1][j] > mud[j] + arr[i][j-1]){
                            arr[i][j] = mud[i] + arr[i+1][j];
                        }else{
                            arr[i][j] = mud[j] + arr[i][j-1];
                        }
                    }else{
                        if(mud[i] + cjj[i+1][j] -  arr[i+1][j] > mud[j] + cjj[i][j-1] - arr[i][j-1]){
                            arr[i][j] = arr[i+1][j];
                        }else{
                            arr[i][j] = arr[i][j-1];
                        }
                    }
                }
                step++;
            }
            ret = arr[0][mud.length-1];
        }
        System.out.println(template.end()/20000);
        //System.out.println(ret);
        //System.out.println(all - ret);
    }
}
