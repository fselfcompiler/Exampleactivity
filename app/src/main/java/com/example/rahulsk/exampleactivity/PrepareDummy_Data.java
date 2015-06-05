package com.example.rahulsk.exampleactivity;
import java.util.*;
import java.io.*;
import java.util.*;

/**
 * Created by rahul.sk on 05/06/15.
 */
public class PrepareDummy_Data {

    Map<String, Points> mapping =new HashMap<>();
    List<Points> datapoints = new ArrayList<>();
    List<String> brands = new ArrayList<>();
    PrepareDummy_Data()
    {
         fill_data_points();
         fill_mapping();
    }
    void fill_data_points()
    {
        datapoints.add(new Points(77.683329,12.932779));
        brands.add("Nike");
        datapoints.add(new Points(77.683526, 12.93309));
        brands.add("Puma");
    }
    void fill_mapping()
    {
        for(int i=0;i<brands.size();i++) {
            mapping.put(brands.get(i),datapoints.get(i));
        }
    }
    Map<String, Points> get_mapping()
    {
        return mapping;
    }

}
