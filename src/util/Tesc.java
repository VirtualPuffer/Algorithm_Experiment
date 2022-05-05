package util;

public class Tesc {
    public static void main(String[] args) {
        double p = 3D/5120;
        double l = 1.96;
        double end = ((1-p)/(l*Math.sqrt(p*(1-p))));
        System.out.println(end*end);
    }
}
