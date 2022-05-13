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
}
public class Kru {

    public static void kru() throws FileNotFoundException {
        TimeTemplate template = new TimeTemplate();
        /*InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\mediumDG.txt");
        Scanner scanner = new Scanner(input);
        int point = scanner.nextInt();
        int line = scanner.nextInt();*/

        InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\largeG.txt");
        //InputStream input = new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\page");
        Scanner scanner = new Scanner(input);
        int line = scanner.nextInt();
        int point = scanner.nextInt();
        System.out.println(line);

        Set<Integer>[] arr = new HashSet[point];
        Set<Integer>[] keySet = new HashSet[point];
        Set<Integer>[] black = new HashSet[point];
        for(int i = 0;i<point;i++){
            arr[i] = new HashSet<>();
            keySet[i] = new HashSet<>();
            black[i]=  new HashSet<>();
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
        TimeTemplate t = new TimeTemplate();
        //建立最小生成树
        int tag = 1;
        int[] floor = new int[point];
        int[] tagArr = new int[point];
        int c = 0;
        for(int i  = 0;i<point;i++){c++;
            if(dfs(arr,keySet,tagArr,i,tag,floor,0)){
                tag++;
            }
        }
        System.out.println("____");
        System.out.println(tag-1);
        int p = 0;
        for(Point px : set){
            if(!keySet[px.a].contains(px.b)){
                findFather(keySet,black,px.a,px.b,-1,floor);
            }
        }
        int all = 0;
        for(int i = 0;i<keySet.length;i++){
            for(Integer j : keySet[i]){
                if(!black[i].contains(j)){
                    keySet[j].remove(i);
                    all++;
                    //System.out.println(i+"  "+j);
                }
            }
        }
        System.out.println(all);
    }
    public static boolean findFather(Set<Integer>[] keySet,Set<Integer>[] black,int index,int target,int fa,int[] floor){
        if(target == index){
            black[index].add(target);
            black[target].add(index);
            return true;
        }else {
            for(Integer next : keySet[index]){
                if(next == fa){
                    continue;
                }
                if(floor[next]>floor[target]){
                    //continue;
                }
                if(findFather(keySet,black,next,target,index,floor)){
                    black[index].add(next);
                    black[next].add(index);
                    return true;
                }
            }
            return false;
        }
    }
    public static boolean dfs(Set<Integer>[] arr,Set<Integer>[] keySet,int[] tagArr,int index,int tag,int[] floor,int f){
        if(tagArr[index]!=0){
            return false;
        }else {
            floor[index] = f;
            tagArr[index] = tag;
            for(Integer next : arr[index]){
                if(tagArr[next]==0){
                    keySet[next].add(index);
                    keySet[index].add(next);
                    dfs(arr,keySet,tagArr,next,tag,floor,f+1);
                }
            }
            if(arr[index].size()>0){
                return true;
            }
        }
        return false;
    }
}
