package experiment03;

import util.TimeTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Point{
    int numb;
    static int colorNumber = 5;
    Set<Point> set = new HashSet<>();
    static Set<Integer> x = new HashSet<>();
    static Point[] points = new Point[451];
    int color = 0;
    int[] colorN = new int[colorNumber+1];
    int ret = colorNumber;
    public Point(int numb){
        this.numb = numb;
    }

    public void add(Point point){
        set.add(point);
    }

    public void paint(int i){
        if(i == color){
            return;
        }else {
            for (Point get : set) {
                get.colorN[color]--;
                get.colorN[i]++;
            }
            color = i;
        }
    }
    public int getNumber(){
        int rets = 0;
        for(int i = 0;i<colorN.length;i++){
            if(colorN[i]==0){
                rets++;
            }
        }
        return rets;
    }
    public static void addPoint(String get){
        int ret1 = 0;
        int index = 0;
        while (index<get.length()&&get.charAt(index) == ' '){
            index++;
        }
        while(index<get.length()&&get.charAt(index)<='9' && get.charAt(index)>='0'){
            ret1*=10;
            ret1 += get.charAt(index)-'0';
            index++;
        }

        int ret2 = 0;
        while (index<get.length()&&get.charAt(index) == ' '){
            index++;
        }
        while(index<get.length()&&get.charAt(index)<='9' && get.charAt(index)>='0'){
            ret2*=10;
            ret2 += get.charAt(index)-'0';
            index++;
        }
        ret1--;
        ret2--;
        x.add(ret1);
        x.add(ret2);
        points[ret1].add(points[ret2]);
        points[ret2].add(points[ret1]);
    }
}

public class Console {
    static Set<Point> px = new HashSet<>();
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        for(int i = 0;i<Point.points.length;i++){
            Point.points[i] = new Point(i);
        }
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\vitual_puffer\\Desktop\\资料\\算法设计与分析\\algorithm\\resources\\mapData\\1.col"));
        while (true){
            int g = reader.read();
            if(g == -1){
                break;
            }
            if((char)g == 'e'){
                Point.addPoint(reader.readLine());
            }else {
                reader.readLine();
            }
        }
        TimeTemplate t = new TimeTemplate();
        Point point = MRV();
        point.paint(1);
        d(point,1 );
        System.out.println(t.end()/1000);
        System.out.println(answer);
    }
    public static void d(Point point,int number){
        if(point == null){
            return;
        }
        if(number == Point.points.length-1){
            answer++;
            return;
        }
        for(int i = 1;i<=Point.colorNumber;i++){
            if(canPaint(point,i)){
                Point next = null;
                try {
                    point.paint(i);
                    next = MRV();
                    d(next,number+1);
                } catch (Exception e) {
                }
            }
        }
        point.paint(0);
    }
    public static Point MRV(){
        int min = Point.colorNumber+1;
        Point minP = null;
        for(int i = 0;i<Point.points.length;i++){
            if(min == 0){
                throw new RuntimeException();
            }
            Point get = Point.points[i];
            if(get.color != 0){
                continue;
            }else {
                int n = get.getNumber();
                if (min > n) {
                    min = get.getNumber();
                    minP = get;
                }else if(min == n){
                    if(minP.set.size() < get.set.size()){
                        minP = get;
                    }
                }
            }
        }
        return minP;
    }
    public static void dfs(Point get,int number){
        System.out.println(number);
        System.out.println(get.numb);
        Set<Point> available = new HashSet<>();
        if(number == Point.points.length-1){
            answer++;
        }

        while(true){
            available = new HashSet<>();
            for(Point point:get.set){
                if(point.color == 0){
                    available.add(point);
                }
            }
            if(available.isEmpty()){
                break;
            }
            int minColor = Point.colorNumber+1;
            Point point = null;
            for(Point next : available){
                if(next.color != 0){
                    continue;
                }
                int num = next.getNumber();
                if(num == 1){
                    point = next;
                    break;
                }else if(num == 0){
                    return;
                }else if(num < minColor){
                    minColor = num;
                    point = next;
                }
            }
            for (int i = 1;i<=Point.colorNumber;i++){
                if(canPaint(point,i)){
                    point.color = i;
                    available.remove(point);
                    dfs(point,number+1);
                    available.add(point);
                    point.color = 0;
                    //
                }
            }
        }

    }
    public static boolean canPaint(int index,int color){
        Point get = Point.points[index];
        for(Point others:get.set){
            if(others.color == color){
                return false;
            }
        }
        return true;
    }
    public static boolean canPaint(Point get,int color){
        for(Point others:get.set){
            if(others.color == color){
                return false;
            }
        }
        return true;
    }
}
