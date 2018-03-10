package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    CustomListCart adapter;
    ListView list;
    Integer total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Bundle bundle_cart = getIntent().getBundleExtra("bundle_cart_key");
        ArrayList<String> items=getIntent().getStringArrayListExtra("items_key");
        ArrayList<String> price=getIntent().getStringArrayListExtra("price_key");
        ArrayList<String> items_parsed=new ArrayList<>();
        ArrayList<String> price_parsed=new ArrayList<>();

        Set<String> bundle_key=bundle_cart.keySet();
        Iterator<String> iterator=bundle_key.iterator();

        ArrayList<String> quantity=new ArrayList<>();
        while(iterator.hasNext()){
            int x=Integer.parseInt(iterator.next());
            total+=Integer.parseInt(bundle_cart.getString(Integer.toString(x)))*Integer.parseInt(price.get(x));;
            quantity.add(bundle_cart.getString(Integer.toString(x)));
            items_parsed.add(items.get(x));
            price_parsed.add(price.get(x));
        }

        adapter = new CustomListCart(CartActivity.this, items_parsed,quantity,price_parsed);
        list=(ListView)findViewById(R.id.cart_listView);
        list.setAdapter(adapter);
        TextView total_cost_cart=(TextView) findViewById(R.id.total_cost_cart);
        total_cost_cart.setText("TOTAL PAYABLE: Rs. "+total);

    }

}
