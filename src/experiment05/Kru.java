package experiment05;

import util.TimeTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


class x{
    int index;
    int size;
    public x(int index,int size){
        this.index = index;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        x x = (x) o;
        return index == x.index && size == x.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, size);
    }
}
public class Kru {

    public static void kru(InputStream input) throws FileNotFoundException {
        System.out.println("____ 数据加载中 ____");
        Scanner scanner = new Scanner(input);
        int point = scanner.nextInt();
        int line = scanner.nextInt();
        System.out.println(line);

        Set<Integer>[] arr = new HashSet[point];
        int[] keySet = new int[point];
        int[] fSet = new int[point];
        boolean[] tp = new boolean[point];
        for(int i = 0;i<point;i++){
            tp[i] = true;
            keySet[i] = -1;
            arr[i] = new HashSet<>();
        }
        Set<Point> set = new HashSet<>();
        while (line>0){
            line--;
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            set.add(new Point(a,b));
            arr[a].add(b);
            arr[b].add(a);
        }
        TimeTemplate t = new TimeTemplate();
        //建立最小生成树
        for(int i  = 0;i<point;i++){
            bfs(arr,keySet,fSet,i);
        }
        System.out.println("____ 数据加载完成 ____");
        for(Point px : set){
            if(fSet[px.a]!=px.b && fSet[px.b]!=px.a){
                findFather(keySet,px.a,px.b,tp);
            }
        }
        int all = 0;
        for(int i = 0;i<keySet.length;i++){
            int f = fSet[i];
            if(tp[i] && f!=0){
                //System.out.println(i+"   "+f);
                all++;
            }
        }
        System.out.println("all  "+all);
        System.out.println("time: " + t.nanoEnd());
        System.out.println("___________________________________");
        System.out.println();
    }
    public static void findFather(int[] keySet,int index,int target,boolean[] tp) {
        Set<Integer> set = new HashSet<>();
        int current = index;
        while (keySet[current]!=-2){
            int fa = keySet[current];
            set.add(current);
            current = fa;//祖先
        };
        int t =target;
        boolean mode = true;
        int ag = -1;
        while (keySet[t]!=-2){
            int ta = keySet[t];
            if(set.contains(t) && mode){
                ag = t;
                mode = false;
            }
            if(mode){
                set.add(t);
            }else {
                set.remove(t);
            }
            t = ta;
        }
        for(Integer g : set){
            tp[g] = false;
            if(ag!= -1){
                keySet[g] = ag;
            }
        }
    }
    public static void bfs(Set<Integer>[] arr,int[] keySet,int[] fSet,int index){
        if(keySet[index]!=-1){
            return;
        }else {
            LinkedList<Integer> list = new LinkedList<>();
            list.addLast(index);
            keySet[index] = -2;
            while (!list.isEmpty()){
                int get = list.getFirst();
                list.removeFirst();
                for(Integer next : arr[get]){
                    if(keySet[next] == -1){
                        keySet[next]=get;
                        //arr[next].remove(get);
                        fSet[next] = get;
                        list.addLast(next);
                    }
                }
            }
        }
    }
}
