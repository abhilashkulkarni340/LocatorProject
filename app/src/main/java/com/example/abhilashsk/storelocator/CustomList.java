package com.example.abhilashsk.storelocator;

/**
 * Created by abhilashsk on 02/03/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> shopName;
    private final ArrayList<String> location;
    public CustomList(Activity context,
                      ArrayList<String> shop,ArrayList<String> location) {
        super(context, R.layout.list_single, shop);
        this.context = context;
        this.shopName = shop;
        this.location = location;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.from_name);
        TextView address = (TextView) rowView.findViewById(R.id.address_text);
        txtTitle.setText(shopName.get(position));
        address.setText(location.get(position));
        RelativeLayout rel = (RelativeLayout)rowView.findViewById(R.id.rel_shop_list);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ShopActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        return rowView;
    }
}
