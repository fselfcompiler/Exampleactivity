package com.example.rahulsk.exampleactivity;

/**
 * Created by uttam.tiwari on 05/06/15.
 */
public class Coordinates {

    private double lat;
    private double lng;

    Coordinates(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat(){
        return this.lat;
    }
    public double getLng(){
        return this.lng;
    }
}
