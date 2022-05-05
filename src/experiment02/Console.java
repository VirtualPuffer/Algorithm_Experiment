package experiment02;

import util.TimeTemplate;

import java.util.*;

public class Console {
    public static void main(String[] args) {
        double a = 0;
        for(int i = 0;i<10;i++){
            Point[] get = Point.getRandom(60000,20000);
            PointMerge.mergeS(0,get.length-1,get);
            TimeTemplate template = new TimeTemplate();
            //System.out.println(fz(get, 0, get.length - 1));
            //fz(get, 0, get.length - 1);
            force(get);
            //System.out.println(template.nanoEnd());
            a += template.nanoEnd();
        }
        System.out.println(a/10);
    }


    public static void sortY(Point[] points,int left,int right){
        boolean tag = true;
        while(tag){
            tag = false;
            for(int i = left;i<right-1;i++){
                if(points[i].comparey(points[i+1])){
                    Point temp = points[i];
                    points[i] = points[i+1];
                    points[i+1] = temp;
                    tag = true;
                }
            }
        }
    }

    public static double fz(Point[] points,int left,int right){
        //System.out.println(right - left);
        double min = Double.MAX_VALUE;
        if(left >= right){
            return min;
        }
        if(left == right-1){
            return points[left].distance(points[right]);
        }
        if(left < right){
            int mid = (left+right)/2;
            Point midPoint = points[mid];
            double a = fz(points,left,mid);
            double b = fz(points,mid+1,right);
            min = Math.min(a,b);
            double size = Math.min(a,b);

            double start = midPoint.x - size;
            double end = midPoint.x+size;

            ArrayList<Point> arr1 = new ArrayList<>();
            ArrayList<Point> arr = new ArrayList<>();
            for(int i = mid-1;i>=left && Math.abs(midPoint.x - points[i].x) < min;i--){
                arr.add(points[i]);
            }
            for(int i = mid+1;i<=right && Math.abs(midPoint.x - points[i].x) < min;i++){
                arr1.add(points[i]);
            }
            arr.sort(new Comparator<Point>() {
                public int compare(Point f, Point s) {
                    return (f.y - s.y) + (f.x-s.x);
                }
            });
            arr1.sort(new Comparator<Point>() {
                public int compare(Point f, Point s) {
                    return (f.y - s.y) + (f.x-s.x);
                }
            });
            for(int i = 0;i<arr.size();i++){
                Point fp = arr.get(i);
                for(int j = 0;j<arr1.size();j++){
                    double op = fp.distance(arr1.get(j));
                    if(op != Double.NaN){
                        min = Math.min(min,op);
                    }
                }
            }
        }
        return min;
    }

    public static double force(Point[] points){
        double min = Double.MAX_VALUE;
        for(int i = 0;i<points.length;i++){
            Point f = points[i];
            for(int j = i+1;j<points.length;j++){
                Point s = points[j];
                if(s.equals(f)){
                    continue;
                }
                min = Math.min(min,f.distance(s));
            }
        }
        return min;
    }
}
