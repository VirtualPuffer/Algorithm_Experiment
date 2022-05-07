package experiment05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

class Point{
    int a;
    int b;
    public Point(int a ,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return a == point.a && b == point.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
public class Standard {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\mediumDG.txt");
        Scanner scanner = new Scanner(input);
        int point = scanner.nextInt();
        int line = scanner.nextInt();

        /*InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\largeG.txt");
        //InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\page");
        Scanner scanner = new Scanner(input);
        int line = scanner.nextInt();
        int point = scanner.nextInt();*/
        System.out.println(line);

        Set<Integer>[] arr = new HashSet[point];
        for(int i = 0;i<point;i++){
            arr[i] = new HashSet<>();
        }
        Set<Point> set = new HashSet<>();
        try {
            while (true){
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                set.add(new Point(a,b));
                arr[a].add(b);
                arr[b].add(a);
                line--;
            }
        }catch (Exception p){
        }
        int p = 0;
        for(Point px : set){
            if(bfs(arr,px.a,px.b)){
                System.out.println(px.a + "  " + px.b + "  " + p++);
            }
        }
    }
    public static boolean bfs(Set<Integer>[] arr,int a,int b){
        //System.out.println(a+"        "+b);
        int[] access = new int[arr.length];
        LinkedList<Integer> list = new LinkedList();
        //list.add(b);
        for(Integer c : arr[a]){
            if(access[c] == 0 ){
                if(c != b){
                    list.addLast(c);
                }
                access[c] = 1;
            }
        }
        access[a] =1;
        while(!list.isEmpty()){
            int get = list.getFirst();
            list.removeFirst();
            for(Integer c : arr[get]){
                if(access[c] == 0 ){
                    list.addLast(c);
                    access[c] = 1;
                }
                if(c.equals(b)){
                    return false;
                }
            }
        }
        return true;
    }
}
