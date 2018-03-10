package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle bundle_cart = getIntent().getBundleExtra("bundle_cart_key");
        Set<String> bundle_key=bundle_cart.keySet();
        Iterator<String> iterator=bundle_key.iterator();
        ArrayList<String> cart_list=new ArrayList<String>();
        while(iterator.hasNext()){
            String x=iterator.next();
            cart_list.add(x+"\t\t\t\t "+bundle_cart.getString(x));
        }
        Log.d("CART",cart_list.get(0));
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cart_list);
        ListView lv=(ListView)findViewById(R.id.cart_listView);
        lv.setAdapter(adapter);
    }

}
