package util;

public class TimeTemplate {
    long startTime;
    long startNano;
    public TimeTemplate() {
        this.startTime = System.currentTimeMillis();
        this.startNano = System.nanoTime();
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
        this.startNano = System.nanoTime();
    }

    public double end(){
        return System.currentTimeMillis() - startTime;
    }

    public double nanoEnd(){
        double ret = (System.nanoTime() - startNano);
        return ret/1000000000;
    }
}
