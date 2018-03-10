package com.example.abhilashsk.storelocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhilashsk on 10/03/18.
 */

public class CustomListCart extends ArrayAdapter<String>{
    private final Activity context;
    private final ArrayList<String> items;
    private final ArrayList<String> quantity;
    private final ArrayList<String> price;
    public Integer total_cost;
    public CustomListCart(Activity context,
                          ArrayList<String> items, ArrayList<String> quantity, ArrayList<String> price) {
        super(context,R.layout.list_single_cart,items);
        this.context = context;
        this.items = items;
        this.quantity = quantity;
        this.price=price;
        this.total_cost=0;

    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView= inflater.inflate(R.layout.list_single_cart, null, true);
        TextView item_name=(TextView) rowView.findViewById(R.id.plist_name_cart);
        TextView item_quantity = (TextView) rowView.findViewById(R.id.plist_quantity_cart);
        TextView item_total=(TextView) rowView.findViewById(R.id.plist_total_cost);
        TextView total_cost_cart=(TextView)rowView.findViewById(R.id.total_cost_cart);

        item_name.setText(items.get(position));
        item_quantity.setText(quantity.get(position));
        String total_cost_this_item=Integer.toString(Integer.parseInt(quantity.get(position))*Integer.parseInt(price.get(position)));
        item_total.setText("Rs. "+total_cost_this_item);


        total_cost+=Integer.parseInt(total_cost_this_item);
        //total_cost_cart.setText("TOTAL PAYABLE: "+total_cost);
        Log.d("total",""+total_cost);

        return rowView;
    }
}
