package com.example.rahulsk.exampleactivity;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by uttam.tiwari on 05/06/15.
 */
public class LocationIndetifier {
    public static final int MAX_TIME = 5*60*60;
    public static Coordinates curr_location;
    public static List<WIndowEntity> coordinates = new ArrayList<WIndowEntity>();
    public static long START_TIME = new Date().getTime();
    public static double distanceCovered;

    public static String findNearByPlace(Coordinates p){
        long curr_time = new Date().getTime();
        if(curr_time - START_TIME >= MAX_TIME){
            curr_location= coordinates.get(0).getP();
            clearWindow(curr_time);
            START_TIME = coordinates.get(0).getTime();
            return checkMovement(p);
        } else {
            coordinates.add(new WIndowEntity(p,curr_time));
        }
        return "";
    }

    private static void clearWindow(long time){
        Iterator<WIndowEntity> i = coordinates.iterator();
        while(i.hasNext()){
            if(time-i.next().getTime() > MAX_TIME){
                i.remove();
            }
        }
    }

    private static String checkMovement(Coordinates p){
        String str = "" ;
        distanceCovered = distance(p.getLat(), p.getLng(), curr_location.getLat(), curr_location.getLng());
        if(distanceCovered <= 15){
            str= PrecissionFinder.getPrecissionFinder().findNearByPlace(p);
        }
        return str;
    }


    //distance in meters
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344*1000;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


}
