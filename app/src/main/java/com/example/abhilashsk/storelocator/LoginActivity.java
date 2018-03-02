package com.example.abhilashsk.storelocator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.abhilashsk.USERNAME";
    public static final String EXTRA_PASSWORD = "com.example.abhilashsk.PASSWORD";
    final DatabaseHandler db = new DatabaseHandler(this);
    /*public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Username = "usernameKey";
    public static final String Password = "passwordKey";
    SharedPreferences sharedpreferences;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try{
            String user = sharedpreferences.getString("usernameKey","");
            String pass = sharedpreferences.getString("passwordKey","");
            if(user.isEmpty()||pass.isEmpty()){
                Intent intent = new Intent(this, Dashboard2Activity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Log.d("Session Error",e.toString());
        }*/

    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, Dashboard2Activity.class);
        EditText username_text = (EditText) findViewById(R.id.username_login);
        EditText password_text = (EditText) findViewById(R.id.password_login);
        String username = username_text.getText().toString();
        String password = password_text.getText().toString();

        intent.putExtra(EXTRA_USERNAME,username);
        intent.putExtra(EXTRA_PASSWORD,password);

        Cursor c = db.getLoginData("CUSTOMER",username);
        try{
            if(c.getCount()!=0){
                c.moveToNext();
                String pass = c.getString(3);
                if(pass.equals(password)){
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Username, username);
                    editor.putString(Password, password);
                    editor.commit();*/
                    startActivity(intent);
                }else{
                    Message("Wrong Password");
                }
            }else{
                Message("Wrong username and password!");
            }
        }catch (Exception e){
            Message("Login Unsuccessful! Try Again!");
            Log.d("LOGIN ERROR",e.toString()+" "+c.getCount());
        }
    }

    public void Message(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
