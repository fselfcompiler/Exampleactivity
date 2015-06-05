package com.example.rahulsk.exampleactivity;

import android.util.Log;

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
        Coordinates point1 = new Coordinates(12.932779,77.6833214);
        Coordinates point2 = new Coordinates(12.93309,77.683526);
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
        float min_distance = 15.000F;//Double.MAX_VALUE;
        String  nearest = "";
        for(Map.Entry<String, Coordinates> m: map.entrySet()){
            Coordinates p = m.getValue();

            float curr_dist = calculateDistance(p,point);
            //Log.v("Distance",);
            Log.v("Current Coordinates ","X cord = " + point.getLat()+" Y cord = " + point.getLng() +"Ponint 2 x= "+p.getLat()+" Y="+p.getLng()+"distance " + curr_dist);
            if( curr_dist <= min_distance){
                //min_distance = curr_dist;
                nearest = m.getKey();
            }
        }
     return nearest;
    }

    private float calculateDistance(Coordinates p1, Coordinates p2){
       // return Math.sqrt(Math.pow((p2.getLat()-p1.getLat()),2.0)+Math.pow((p2.getLng()-p1.getLng()),2.0));
        float[] results=new float[4];
        android.location.Location.distanceBetween(p1.getLat(), p1.getLng(), p2.getLat(), p2.getLng(),results);
       // android.location.Location.distanceBetween(77.6833214, 12.932779, 77.683526, 12.93309,results);
        return results[0];

    }


}

