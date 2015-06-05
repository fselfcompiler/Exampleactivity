package com.example.rahulsk.exampleactivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by uttam.tiwari on 05/06/15.
 */
public class PrecissionFinder {

    private static PrecissionFinder precissionFinder;
    Map<String , Coordinates> map = new HashMap<String, Coordinates>();
    private PrecissionFinder(){
        Coordinates point1 = new Coordinates(77.6833,12.9328);
        Coordinates point2 = new Coordinates(77.6836,12.9330);
        map.put("NIKE", point1);
        map.put("LEVIS", point2);

    }

    public static PrecissionFinder getPrecissionFinder(){
        if(precissionFinder != null) {
            return precissionFinder;
        }
        return new PrecissionFinder();
    }

    public String findNearByPlace(Coordinates point){
        double min_distance = Double.MAX_VALUE;
        String  nearest = "";
        for(Map.Entry<String, Coordinates> m: map.entrySet()){
            Coordinates p = m.getValue();
            double curr_dist = LocationIndetifier.distance(p.getLat(),p.getLng(),point.getLat(),point.getLng());
            if( curr_dist < min_distance){
                min_distance = curr_dist;
                nearest = m.getKey();
            }
        }
     return nearest;
    }



}

