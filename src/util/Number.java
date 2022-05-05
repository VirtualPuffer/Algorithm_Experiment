package util;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/**
 * 随机数生成类
 *
 * */
public class Number {
    public static int[] getRandom(int length,int max,boolean duplicate){
        if(duplicate && max < length){
            throw new RuntimeException("?");
        }
        int[] ret = new int[length];
        Set<Integer> set = new HashSet<>();
        Random a = new Random();
        for(int i = 0;i<ret.length;i++){
            int get = a.nextInt(max);
            while (set.contains(get) && duplicate){
                get = a.nextInt(max);
            }
            set.add(get);
            ret[i] = get;
        }
        return ret;
    }
}
