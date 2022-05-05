package experiment01;

public class SelectSort {
    public static void select(int[] get){
        int[] temp = new int[get.length];
        System.arraycopy(get,0,temp,0,get.length);//生成临时数组
        for(int i = 0;i<get.length;i++){
            int max = -1;
            int maxIndex = -1;
            for(int j = 0;j<get.length;j++){
                if(temp[j] > max){
                    max = temp[j];
                    maxIndex = j;//记录最大的位置
                }
            }
            get[get.length - i - 1] = temp[maxIndex];
            temp[maxIndex] = Integer.MIN_VALUE;//替换成最小的数字
        }
    }
}
