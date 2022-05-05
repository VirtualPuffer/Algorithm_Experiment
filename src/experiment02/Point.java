package experiment02;

import java.util.*;

public class Point{
    int x;
    int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public boolean compare(Point others){
        if(this.x == others.x){
            return this.y > others.y;
        }else {
            return this.x > others.x;
        }
    }
    public boolean comparey(Point others){
        if(this.y == others.y){
            return this.x > others.x;
        }else {
            return this.y > others.y;
        }
    }

    public double distance(Point others){
        double get = (others.x- this.x)*(others.x- this.x) + (others.y- this.y)*(others.y- this.y);
        return Math.sqrt(get);
    }

    public static Point randomPoint(int max,Random a){
        return new Point(a.nextInt(max),a.nextInt(max));
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        if (this == point) return true;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static Point[] getRandom(int length, int max){
        Point[] ret = new Point[length];
        Set<Point> set = new HashSet<>();
        Random a = new Random();
        for(int i = 0;i<ret.length;i++){
            Point get = randomPoint(max,a);
            while (set.contains(get)){
                get = randomPoint(max,a);
            }
            //System.out.println(get.x + " "+ get.y);
            set.add(get);
            ret[i] = get;
        }
        return ret;
    }
}
