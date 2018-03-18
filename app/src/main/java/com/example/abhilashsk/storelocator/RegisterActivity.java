package com.example.abhilashsk.storelocator;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    Validator valid = new Validator();
    //final DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //db.createTable();
    }

    public void registerSubmit(View view){
        TextView name = (TextView) findViewById(R.id.name_rgstr);
        TextView phone = (TextView) findViewById(R.id.phone_num_rgstr);
        TextView email = (TextView) findViewById(R.id.email_rgstr);
        TextView user = (TextView) findViewById(R.id.username_rgstr);
        TextView pass = (TextView) findViewById(R.id.password_rgstr);
        String lat = "30.323543546";
        String lon = "71.243254534";
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");


        //myRef.setValue("Hello, World!");
        if(!valid.isValidName(name.getText().toString())){
            Toast.makeText(this,"INVALID NAME",Toast.LENGTH_LONG).show();
            //myRef.child("name").setValue(name.getText().toString());
            //name.setText("");
        }else if(!valid.isValidPhoneNumber(phone.getText().toString())){
            Toast.makeText(this,"INVALID PHONE NUMBER",Toast.LENGTH_LONG).show();
            //phone.setText("");
        }else if(!valid.isValidEmail(email.getText().toString())){
            Toast.makeText(this,"INVALID EMAIL",Toast.LENGTH_LONG).show();
            //email.setText("");
        }else if(!valid.isValidUsername(user.getText().toString())){
            Toast.makeText(this,"INVALID USERNAME",Toast.LENGTH_LONG).show();
            //user.setText("");
        }else if(!valid.isValidPassword(pass.getText().toString())){
            Toast.makeText(this,"INVALID PASSWORD",Toast.LENGTH_LONG).show();
            //pass.setText("");
        }else{
            String[] details = {name.getText().toString(), user.getText().toString(), pass.getText().toString(), phone.getText().toString(), email.getText().toString(), lat, lon};
            Log.d("details", name + " " + email);
            /*try {
                db.insert(details, "CUSTOMER");
                Message("Registration Successful!");
            } catch (Exception e) {
                Message("Registration Unsuccessful! ");
                Log.d("Query result:", e.toString());
            }
            Log.d("DB Details", Integer.toString(db.getDetails()));*/

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void Message(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
