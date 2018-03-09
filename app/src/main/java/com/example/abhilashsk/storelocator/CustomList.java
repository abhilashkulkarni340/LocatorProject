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
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.from_name);
        final TextView address = (TextView) rowView.findViewById(R.id.address_text);
        txtTitle.setText(shopName.get(position));
        address.setText(location.get(position));

        ImageView nav = (ImageView) rowView.findViewById(R.id.navigation_image);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MapsActivity.class);
                intent.putExtra("destination_address_key",location.get(position));
                intent.putExtra("destination_name_key",shopName.get(position));
                view.getContext().startActivity(intent);
            }
        });

        ImageView shop = (ImageView)rowView.findViewById(R.id.shop_icon);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ShopActivity.class);
                intent.putExtra("Address",location.get(position));
                intent.putExtra("ShopName",shopName.get(position));
                view.getContext().startActivity(intent);
            }
        });

        return rowView;
    }
}
