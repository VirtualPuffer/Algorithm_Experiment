package experiment02;

public class PointMerge {
    public static void merge(int low,int mid,int high,Point[] get,Point[] temp){
        for(int i = low;i<=high;i++){
            temp[i] = get[i];
        }
        int s1 = low;
        int s2 = mid+1;
        int index = low;
        while(s1 <= mid && s2 <=high){
            if(temp[s1].compare(temp[s2])){
                get[index++] = temp[s2++];
            }else{
                get[index++] = temp[s1++];
            }
        }
        while(s2 <=high){
            get[index++] = temp[s2++];
        }
        while(s1 <= mid) {
            get[index++] = temp[s1++];
        }
    }
    public static void mergeS(int low,int high,Point[] get){
        mergeSort(low,high,get,new Point[get.length]);
    }
    public static void mergeSort(int low,int high,Point[] get,Point[] temp){
        // System.out.println(low + " "+high);
        if(low < high){
            int mid = (low + high)/2;
            mergeSort(low,mid,get,temp);
            mergeSort(mid+1,high,get,temp);
            merge(low,mid,high,get,temp);
        }
    }
}
