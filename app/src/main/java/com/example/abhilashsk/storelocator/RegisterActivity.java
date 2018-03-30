package com.example.abhilashsk.storelocator;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Validator valid = new Validator();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerSubmit(View view){
        TextView name = (TextView) findViewById(R.id.name_rgstr);
        TextView phone = (TextView) findViewById(R.id.phone_num_rgstr);
        TextView email = (TextView) findViewById(R.id.email_rgstr);
        TextView user = (TextView) findViewById(R.id.username_rgstr);
        TextView pass = (TextView) findViewById(R.id.password_rgstr);
        String lat = "30.323543546";
        String lon = "71.243254534";
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("latitude",lat);
        userdata.put("longitude",lon);
        if(!valid.isValidName(name.getText().toString())){
            Toast.makeText(this,"INVALID NAME",Toast.LENGTH_LONG).show();
        }else if(!valid.isValidPhoneNumber(phone.getText().toString())){
            Toast.makeText(this,"INVALID PHONE NUMBER",Toast.LENGTH_LONG).show();
        }else if(!valid.isValidEmail(email.getText().toString())){
            Toast.makeText(this,"INVALID EMAIL",Toast.LENGTH_LONG).show();
        }else if(!valid.isValidUsername(user.getText().toString())){
            Toast.makeText(this,"INVALID USERNAME",Toast.LENGTH_LONG).show();
        }else if(!valid.isValidPassword(pass.getText().toString())){
            Toast.makeText(this,"INVALID PASSWORD",Toast.LENGTH_LONG).show();
        }else{
            String[] details = {name.getText().toString(), user.getText().toString(), pass.getText().toString(), phone.getText().toString(), email.getText().toString(), lat, lon};
            Log.d("details", name + " " + email);
            userdata.put("name",name.getText().toString());
            userdata.put("phone",phone.getText().toString());
            userdata.put("email",email.getText().toString());
            userdata.put("username",user.getText().toString());
            userdata.put("password",pass.getText().toString());

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        db.collection("userdata").document(phone.getText().toString())
                .set(userdata, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("X", "DocumentSnapshot added Successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("X", "Error adding DocumentSnapshot", e);
                    }
                });
    }

    public void Message(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
