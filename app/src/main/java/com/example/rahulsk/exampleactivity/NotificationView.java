package com.example.rahulsk.exampleactivity;

/**
 * Created by rahul.sk on 05/06/15.
 */
import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotificationView extends  Activity {
    TextView text_view;
   // LinearLayout layout;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        text_view=(TextView)findViewById(R.id.text_id);
      // layout=(LinearLayout)findViewById(R.id.layout_id)
        text_view.setText("Hi this is notification");
    }


}
