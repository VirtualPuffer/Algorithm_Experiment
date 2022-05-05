package experiment01;

import util.TimeTemplate;

import static experiment01.BubbleSort.bubble;
import static experiment01.InsertSort.*;
import static util.Number.getRandom;

/**
 * 控制台类，方便调用排序算法
 *
 * */
public class Console {
    public static void main(String[] args) {
        int p = 1;//重复实验次数
        int i = 0;
        double all = 0;//总耗时
        while(p>i++){//多次重复实验
            int[] get = getRandom(100000000,1000000000,false);//获取随机数组，三个参数为数组长度，参数范围，随机数是否允许重复
            //System.out.println(1);
            TimeTemplate time = new TimeTemplate();//封装好的类，方便获取用时
            //InsertSort.insert(get);
            //BubbleSort.bubble(get);
            HeapSort.heapSort(get);
            //SelectSort.select(get);
            //MergeSort.mergeS(0,get.length-1,get);
            //FastSort.fastSort(0,get.length-1,get);
            //double d = time.end();
            double t = time.nanoEnd();
            all += t;
        }
        System.out.println(all/p);//输出结果
    }
}
