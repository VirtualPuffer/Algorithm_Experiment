

import util.TimeTemplate;

import java.io.*;
import java.util.*;

class Point{
    int index;
    double time = 0;
    int weight = 0;
    Set<Point> set= new HashSet<>();
    public Point(int index){
        this.index = index;
    }
}

public class C {
    static double all = 0;
    static double x = 0;
    public static void main(String[] args) throws IOException {
        int[][] arr = {{8,7},{7,6},{6,5},{5,3},{3,7},{3,16},{3,15},{16,15}
        ,{4,5},{5,2},{4,2},{2,1},{3,1},{1,9},{15,9},{12,9},{12,11},{11,10},{8,16},
                {10,9},{10,13},{9,13},{11,14},{10,14},{13,14}};//各区域的连通图
        Map<Integer,Point> map = new HashMap<>();
        for(int i = 0;i<arr.length;i++){
            add(map,arr[i][0],arr[i][1]);
            add(map,arr[i][1],arr[i][0]);
        }
        for(int j =0;j<100000;j++){//随机进入10000次
            dfs(0,1,map);
        }

        map.get(1).time = 0;
        for(Integer i:map.keySet()){
            System.out.println(i + "   " + ((double)map.get(i).time)/100000);//输出结果
            x+=((double)map.get(i).time)/all;
        }
        System.out.println(1);
        System.out.println(all/100000);
    }
    public static void dfs(int time,int index,Map<Integer,Point> map){//核心函数，模拟随机走动
        if(index == 1 && time>2){
            return;
        }else{
            if(index!=1){
                if(index<9){
                    all++;
                }else {
                    all+=0.5;
                }
            }
        }
        Point get = map.get(index);
        if(index<9){
            get.time++;
        }else {
            get.time+=0.5;
        }

        Random a = new Random();//随机点位函数
        int g = a.nextInt(get.set.size());//随机点
        for(Point p : get.set){
            if(g == 0){
                dfs(time+1,p.index,map);//下一个点
                return;
            }else {
                g--;
            }
        }
    }
    public static void add(Map<Integer,Point> map,int f,int s){
        if(map.get(f) == null){
            map.put(f,new Point(s));
        }
        map.get(f).set.add(new Point(s));
    }
}
