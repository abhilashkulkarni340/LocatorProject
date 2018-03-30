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
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    public static final String NAME = "nameKey";
    public static final String EMAIL = "emailKey";
    final Validator valid = new Validator();
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String USERNAME = "usernameKey";
    public static final String PASSWORD = "passwordKey";
    SharedPreferences sharedpreferences;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private ProgressBar progress;
    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkSessionLogin();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    protected void onResume(){
        super.onResume();
        Log.d("LOGIN","LoginActivity resumed");
        checkSessionLogin();

    }

    public void sendMessage(View view){
        final Intent intent = new Intent(this, Dashboard2Activity.class);
        EditText username_text = (EditText) findViewById(R.id.username_login);
        EditText password_text = (EditText) findViewById(R.id.password_login);
        progress=(ProgressBar)findViewById(R.id.progressBar2);
        final String username = username_text.getText().toString();
        final String password = password_text.getText().toString();
        progress.setVisibility(View.VISIBLE);
        db.collection("userdata").whereEqualTo("username",username)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String pass="";
                        for(DocumentSnapshot ds:queryDocumentSnapshots){
                            pass=ds.getString("password");
                            name=ds.getString("name");
                            email=ds.getString("email");
                        }

                        if(pass.equals(password)){
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(USERNAME,username);
                            editor.putString(PASSWORD,password);
                            editor.putString(NAME,name);
                            editor.putString(EMAIL,email);
                            editor.apply();
                            progress.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                        }else{
                            progress.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this,"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                            Log.d("password1",pass);
                            Log.d("password2",password);
                        }
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

    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Log.d("LOGIN","back key pressed");
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void checkSessionLogin(){
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        try{
            String user = sharedpreferences.getString(USERNAME,"");
            String pass = sharedpreferences.getString(PASSWORD,"");
            if(!user.isEmpty()&&!pass.isEmpty()){
                Intent intent = new Intent(this, Dashboard2Activity.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Log.d("Session Error",e.toString());
        }
    }
}
