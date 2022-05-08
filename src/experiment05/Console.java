package experiment05;

import util.TimeTemplate;

import java.io.FileNotFoundException;

public class Console {
    public static void main(String[] args) throws FileNotFoundException {
        TimeTemplate timeTemplate = new TimeTemplate();
        //Kru.kru();
        Standard.stand();
        System.out.println(timeTemplate.end()/1000);
    }
}
