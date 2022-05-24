package experiment05;

import util.TimeTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Console {
    public static void main(String[] args) throws FileNotFoundException {
        Kru.kru(new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\page"));
        Kru.kru(new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\mediumDG.txt"));
        Kru.kru(new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\largeG.txt"));
        Kru.kru(new FileInputStream("D:\\Java\\IdeaProjects\\algorithm\\report\\实验5\\2.txt"));
        //Standard.stand();
        //System.out.println(timeTemplate.end()/1000);
    }
}
