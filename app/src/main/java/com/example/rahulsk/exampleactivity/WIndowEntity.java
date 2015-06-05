package com.example.rahulsk.exampleactivity;

/**
 * Created by uttam.tiwari on 06/06/15.
 */
public class WIndowEntity {
    private Coordinates p;
    private long time;
    public  WIndowEntity(Coordinates p, long time){
        this.p = p;
        this.time= time;
    }

    public Coordinates getP() {
        return p;
    }

    public long getTime() {
        return time;
    }
}
