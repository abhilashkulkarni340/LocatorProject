package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextpage(View view) {
        Intent i=new Intent(MainActivity.this,MapsActivity.class);
        i.putExtra("dest_key","Meghana's food,jayanagar");
        startActivity(i);
    }

    public void nearby(View view) {
        Intent j=new Intent(MainActivity.this,MapsActivity.class);
        j.putExtra("near_key","bmsce");
        startActivity(j);
    }
}
