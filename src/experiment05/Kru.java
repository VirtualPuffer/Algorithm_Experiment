package experiment05;

import util.TimeTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

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

        //建立最小生成树
        int tag = 1;
        int[] tagArr = new int[point];
        int c = 0;
        for(int i  = 0;i<point;i++){c++;
            if(dfs(arr,keySet,tagArr,i,tag)){
                tag++;
            }
        }
        System.out.println("____");
        System.out.println(tag-1);
        int p = 0;
        for(Point px : set){
            if(!keySet[px.a].contains(px.b)){
                findFather(keySet,black,px.a,px.b,-1);
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
    public static boolean findFather(Set<Integer>[] keySet,Set<Integer>[] black,int index,int target,int fa){
        if(target == index){
            black[index].add(target);
            black[target].add(index);
            return true;
        }else {
            for(Integer next : keySet[index]){
                if(next == fa){
                    continue;
                }
                if(findFather(keySet,black,next,target,index)){
                    black[index].add(next);
                    black[next].add(index);
                    return true;
                }
            }
            return false;
        }
    }
    public static boolean dfs(Set<Integer>[] arr,Set<Integer>[] keySet,int[] tagArr,int index,int tag){
        if(tagArr[index]!=0){
            return false;
        }else {
            tagArr[index] = tag;
            for(Integer next : arr[index]){
                if(tagArr[next]==0){
                    keySet[next].add(index);
                    keySet[index].add(next);
                    dfs(arr,keySet,tagArr,next,tag);
                }
            }
            if(arr[index].size()>0){
                return true;
            }
        }
        return false;
    }
    public static boolean bfs(Set<Integer>[] arr,int a,int b){
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
