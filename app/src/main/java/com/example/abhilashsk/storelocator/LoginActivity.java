package com.example.abhilashsk.storelocator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.abhilashsk.USERNAME";
    public static final String EXTRA_PASSWORD = "com.example.abhilashsk.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, Dashboard2Activity.class);
        EditText username_text = (EditText) findViewById(R.id.username_login);
        EditText password_text = (EditText) findViewById(R.id.password_login);
        String username = username_text.getText().toString();
        String password = password_text.getText().toString();

        intent.putExtra(EXTRA_USERNAME,username);
        intent.putExtra(EXTRA_PASSWORD,password);

        startActivity(intent);

    }
}
