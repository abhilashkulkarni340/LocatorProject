package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShopDisplayActivity extends AppCompatActivity {
    String shopname;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_display);

        Intent intent=getIntent();
        shopname =intent.getStringExtra("ShopName");
        address=intent.getStringExtra("Address");

        TextView shop_name=(TextView)findViewById(R.id.shop_name_disp);
        shop_name.setText(shopname);

        TextView shop_address=(TextView)findViewById(R.id.shop_address_disp);
        shop_address.setText(address);
    }

    public void callFunction(View view) {
        dialContactPhone("9087654321");
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public void open_navigation(View view) {
        Intent intent = new Intent(view.getContext(),MapsActivity.class);
        intent.putExtra("destination_address_key",address);
        intent.putExtra("destination_name_key",shopname);
        view.getContext().startActivity(intent);
    }
}
