package com.example.rahulsk.exampleactivity;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import android.app.Activity;
import android.content.Intent;

import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements GooglePlayServicesClient.ConnectionCallbacks,GooglePlayServicesClient.OnConnectionFailedListener,LocationListener {

    LocationClient mLocationClient;
    Location mCurrentLocation;
    LocationRequest mLocationRequest;
    TextView txtLong,txtLat;
    PrepareDummy_Data dummy_data;
    Map<String, Points> mapping=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2. get reference to TextView
        txtLong = (TextView) findViewById(R.id.txtLong);
        txtLat = (TextView) findViewById(R.id.txtLat);

        //Rahul code

        dummy_data=new PrepareDummy_Data();
        mapping=dummy_data.get_mapping();
        // 3. create LocationClient
        mLocationClient = new LocationClient(this, this, this);

        // 4. create & set LocationRequest for Location update
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(1000 * 5);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(1000 * 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 1. connect the client.
        mLocationClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 1. disconnecting the client invalidates it.
        mLocationClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }

    // GooglePlayServicesClient.ConnectionCallbacks
    @Override
    public void onConnected(Bundle arg0) {

        if(mLocationClient != null)
            mLocationClient.requestLocationUpdates(mLocationRequest,  this);

        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();

        if(mLocationClient != null){
            // get location
            mCurrentLocation = mLocationClient.getLastLocation();
            try{

                // set TextView(s)
                txtLat.setText(mCurrentLocation.getLatitude()+"");
                txtLong.setText(mCurrentLocation.getLongitude()+"");


            }catch(NullPointerException npe){

                Toast.makeText(this, "Failed to Connect", Toast.LENGTH_SHORT).show();

                // switch on location service intent
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        }

    }


    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Location changed.", Toast.LENGTH_SHORT).show();
        mCurrentLocation = mLocationClient.getLastLocation();
        txtLat.setText(mCurrentLocation.getLatitude()+"");
        txtLong.setText(mCurrentLocation.getLongitude()+ "");
        check_to_show_notification(new Points(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()));

    }

    void check_to_show_notification(Points current_location)
    {
        for(Map.Entry<String,Points> m: mapping.entrySet())
        {
             if(isinrange(current_location,m.getValue()))
             {
                 Toast.makeText(getApplicationContext(),m.getKey(),Toast.LENGTH_LONG).show();
                 return;
             }
        }
        return;
    }

    boolean isinrange(Points current_location,Points store_location)
    {
        double threshhold_x=0.00001;
        double threshhold_y=0.00001;
        if((current_location.getx()+threshhold_x>=store_location.getx())&&(current_location.getx()-threshhold_x<=store_location.getx())) //X -coordinate
        {
            if((current_location.gety()+threshhold_y>=store_location.gety())&&(current_location.gety()-threshhold_y<=store_location.gety()))
            {
                return true;
            }
        }
        return false;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
