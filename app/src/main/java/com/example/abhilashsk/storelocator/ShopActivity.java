package com.example.abhilashsk.storelocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {
    String[] items = {"Dosa", "Idli", "Coffee", "Maggi"} ;
    String[] prices = {"Rs. 40", "Rs. 30", "Rs. 10", "Rs. 35"} ;
    String[] weights = {"1 plate", "1 plate", "1 plate", "1 plate"} ;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final ArrayList<String> items2 = getInfo(items);
        final ArrayList<String> prices2 = getInfo(prices);
        final ArrayList<String> weights2 = getInfo(weights);

        CustomListShopProducts adapter = new CustomListShopProducts(ShopActivity.this, items2,prices2,weights2);
        list=(ListView)findViewById(R.id.list3);
        list.setAdapter(adapter);
    }

    public ArrayList<String> getInfo(String[] arr){
        ArrayList<String> dynarr = new ArrayList<String>();
        for (String x:arr) {
            dynarr.add(x);
        }
        return dynarr;
    }
}
