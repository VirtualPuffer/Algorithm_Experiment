package experiment01;

import util.TimeTemplate;
import static util.Number.getRandom;

public class BubbleSort {
    public static void bubble(int[] get){
        boolean tag = true;
        while (tag) {
            tag = false;
            for(int i = 0;i<get.length-1;i++){
                if(get[i] > get[i+1]){
                    get[i] += get[i+1];           //交换
                    get[i+1] = get[i] - get[i+1];
                    get[i] -= get[i+1];
                    tag = true;//如果有交换就再搜索一遍
                }
            }
        }
    }
}
