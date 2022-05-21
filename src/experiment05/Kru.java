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

    public static void kru() throws FileNotFoundException {
        //InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\page");
        InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\2.txt");
         //InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\largeG.txt");
        //InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\mediumDG.txt");
        Scanner scanner = new Scanner(input);
        int point = scanner.nextInt();
        int line = scanner.nextInt();
        System.out.println(line);

        Set<Integer>[] arr = new HashSet[point];
        int[] keySet = new int[point];
        Set<Integer>[] black = new HashSet[point];
        for(int i = 0;i<point;i++){
            arr[i] = new HashSet<>();
            keySet[i] = -1;
            black[i]=  new HashSet<>();
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
        System.out.println("______");
        System.out.println("line " + line);
        TimeTemplate t = new TimeTemplate();
        //建立最小生成树
        int tag = 1;
        int[] floor = new int[point];
        int[] tagArr = new int[point];
        for(int i = 0;i<point;i++){
            tagArr[i] = -1;
        }
        int c = 0;
        for(int i  = 0;i<point;i++){
            bfs(arr,keySet,tagArr,i);
        }
        System.out.println("____");
        int p = 0;
        int oo =0;
        //System.out.println(set.size());
        for(Point px : set){
            if(keySet[px.a]!=px.b && keySet[px.b]!=px.a){
                findFather(tagArr,keySet,black,px.a,px.b);
            }
        }
        int all = 0;
        for(int i = 0;i<keySet.length;i++){
            int j = keySet[i];
            //if(!black[i].contains(j) && j!=-1){
            //    System.out.println(i+"  "+j);
            //    all++;
            //}
        }
        System.out.println(all);
        System.out.println("time: " + t.end()/1000);
    }
    public static void findFather(int[] tagArr,int[] keySet,Set<Integer>[] black,int index,int target) {
        if(black[index].size()!=0 && black[target].size()!=0){
            //return;
        }
        int pa = tagArr[index];
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        int current = index;
        while (current!=pa){
            int fa = keySet[current];
            map.put(current,fa);
            current = fa;//祖先
        };
        int t =target;
        boolean mode = true;
        int ag = -1;
        while (t!=pa){
            int ta = keySet[t];
            if(map.containsKey(t) && mode){
                ag = t;
                mode = false;
            }
            if(mode){
                map.put(t,ta);//添加
            }else {
                map.remove(t);//删除公共边
            }
            t = ta;
        }
        for(Integer g : map.keySet()){
            int b = map.get(g);
            black[g].add(b);
            black[b].add(g);
        }
        if(map.containsKey(target) && ag!=-1){
            keySet[target]=ag;
            black[target].add(ag);
        }
        if(map.containsKey(index)&&ag!=-1){
            keySet[index]=ag;
            black[index].add(ag);
        }
    }
    public static void bfs(Set<Integer>[] arr,int[] keySet,int[] tagArr,int index){
        if(tagArr[index]!=-1){
            return;
        }else {
            LinkedList<Integer> list = new LinkedList<>();
            list.addLast(index);
            tagArr[index] = index;
            while (!list.isEmpty()){
                int get = list.getFirst();
                list.removeFirst();
                for(Integer next : arr[get]){
                    if(tagArr[next] == -1){
                        keySet[next]=get;
                        list.addLast(next);
                        tagArr[next] = index;
                    }
                }
            }
        }
    }
}
