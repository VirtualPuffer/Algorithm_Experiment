package experiment01;

import java.util.PriorityQueue;

public class HeapSort {
    public static void heapSort(int[] get){
        PriorityQueue<Integer> queue = new PriorityQueue<>(11);//规模为10的优先队列
        for(Integer num : get){
            queue.add(num);
            if(queue.size() > 10){
                queue.poll();
            }
        }
        //while (!queue.isEmpty()){
            //System.out.println(queue.poll());
        //}
    }
}
