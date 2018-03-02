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

public class CustomListShopProducts extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> items;
    private final ArrayList<String> price;
    private final ArrayList<String> weight;
    public CustomListShopProducts(Activity context,
                      ArrayList<String> items,ArrayList<String> prices,ArrayList<String> weights) {
        super(context, R.layout.list_single_products, items);
        this.context = context;
        this.items = items;
        this.price = prices;
        this.weight =weights;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single_products, null, true);
        TextView plist_name = (TextView) rowView.findViewById(R.id.plist_name);
        TextView plist_price = (TextView) rowView.findViewById(R.id.plist_price);
        TextView plist_weight = (TextView) rowView.findViewById(R.id.plist_weight);
        plist_name.setText(items.get(position));
        plist_price.setText(price.get(position));
        plist_weight.setText(weight.get(position));
        final int pos= position;
        final TextView plist_quantity = (TextView) rowView.findViewById(R.id.plist_quantity);
        ImageView plus = (ImageView) rowView.findViewById(R.id.cart_plus_img);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),items.get(pos)+" has been added to your cart!" , Toast.LENGTH_SHORT).show();
            }
        });
        ImageView minus = (ImageView) rowView.findViewById(R.id.cart_minus_img);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),items.get(pos)+" has been removed to your cart!" , Toast.LENGTH_SHORT).show();
            }
        });
        /*RelativeLayout rel = (RelativeLayout)rowView.findViewById(R.id.rel_shop_list);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ShopActivity.class);
                view.getContext().startActivity(intent);
            }
        });*/
        return rowView;
    }
}
