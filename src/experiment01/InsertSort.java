package experiment01;

public class InsertSort {
    public static void insert(int[] get){
        for(int i = 0;i<get.length;i++){
            for(int j = i;j>0;j--){
                if(get[j] < get[j-1]){
                    int temp = get[j];
                    get[j] = get[j-1];
                    get[j-1] = temp;
                }else{
                    break;
                }
            }
        }
    }
}
