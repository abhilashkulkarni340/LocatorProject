package com.example.abhilashsk.storelocator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.example.abhilashsk.storelocator.LoginActivity.MyPREFERENCES;

public class CartActivity extends AppCompatActivity {

    CustomListCart adapter;
    ListView list;
    Integer total=0;
    Button checkout;
    String shopid,userid,address;
    Double currorderid;
    Integer orderid;
    FirebaseFirestore mDBRef=FirebaseFirestore.getInstance();
    ProgressBar progress;
    Iterator<String> iterator;
    Set<String> bundle_key;
    Bundle bundle_cart;
    ArrayList<String> items_parsed,price_parsed,quantity;
    ArrayList<String> items,price;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //checkSessionCart();
        bundle_cart = getIntent().getBundleExtra("bundle_cart_key");
        items=getIntent().getStringArrayListExtra("items_key");
        price=getIntent().getStringArrayListExtra("price_key");
        shopid=getIntent().getStringExtra("shopid");
        items_parsed=new ArrayList<>();
        price_parsed=new ArrayList<>();

        bundle_key=bundle_cart.keySet();
        iterator=bundle_key.iterator();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userid=sharedPreferences.getString("phoneNumber","");
        //address=sharedPreferences.getString("address","");

        progress=(ProgressBar)findViewById(R.id.progressBarCart);
        progress.setVisibility(View.VISIBLE);

        mDBRef.collection("storedata").whereEqualTo("id",shopid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot ds:queryDocumentSnapshots){
                            if(ds.exists()) {
                                currorderid=ds.getDouble("currorderid");
                            }
                        }
                        progress.setVisibility(View.INVISIBLE);
                        Log.d("ORDERID",Double.toString(currorderid));
                        quantity=new ArrayList<>();
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

                        checkout=(Button)findViewById(R.id.checkout_button);
                        checkout.setOnClickListener(new View.OnClickListener() {
                            @Override public void onClick(View v) {
                                progress.setVisibility(View.VISIBLE);
                                setData();
                            } });
                    }
                });
    }

    public void setData(){
        Double x=currorderid+1;
        orderid=Integer.valueOf(x.intValue());
        Map<String,Object> order=new HashMap<>();
        order.put("id",orderid);
        order.put("payable",total);
        order.put("phonenumber",userid);
        //order.put("address",address);
        mDBRef.collection("storedata").document(shopid).collection("orderdata").document(Integer.toString(orderid))
                .set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                putData();
            }
        });
    }

    public void putData(){
        Map<String,Object> orderitems=new HashMap<>();
        for(int i=0;i<items_parsed.size();i++){
            orderitems.put(items_parsed.get(i),quantity.get(i));
        }
        mDBRef.collection("storedata").document(shopid).collection("orderdata").document(Integer.toString(orderid)).collection("orderitems").document("ITEMS")
                .set(orderitems).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                modifyData();
            }
        });
    }

    public void modifyData(){
        Map<String,Object> modify=new HashMap<>();
        modify.put("currorderid",orderid);
        mDBRef.collection("storedata").document(shopid)
                .set(modify, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progress.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(CartActivity.this, Dashboard2Activity.class);
                PendingIntent pending = PendingIntent.getActivity(CartActivity.this, 0, intent, 0);
                Notification noti = new Notification.Builder(CartActivity.this).setContentTitle("Store Locator").setContentText("Your order has been placed! YAY!").setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pending).build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                noti.flags |= Notification.FLAG_AUTO_CANCEL; manager.notify(0, noti);
                startActivity(intent);
            }
        });
    }

    public void checkSessionCart(){
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try{
            String user = sharedPreferences.getString("usernameKey","");
            String pass = sharedPreferences.getString("passwordKey","");
            if(user.isEmpty()&&pass.isEmpty()){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, Dashboard2Activity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Log.d("Session Error",e.toString());
        }
    }

}
