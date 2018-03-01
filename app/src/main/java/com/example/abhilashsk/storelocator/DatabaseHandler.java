package com.example.abhilashsk.storelocator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.jar.Attributes;

/**
 * Created by abhilashsk on 28/02/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="StoreLocator";


    /*private static final String TABLE_CREATE = "create table CUSTOMER("+
            "cid integer primary key autoincrement," +
            "name text not null," +
            "username text not null," +
            "password text not null," +
            "phone int(10) not null," +
            "email text not null," +
            "latitude double not null," +
            "longitude double not null" +
            ");";
*/

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*try {
            db.execSQL(TABLE_CREATE);
            Log.d("table created","TRUE");
        }catch (Exception e){
            Log.d("table created","FALSE");
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS CUSTOMER");
        onCreate(db);
    }

    /*public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(TABLE_CREATE);
            Log.d("table created","TRUE");
        }catch (Exception e){
            Log.d("table created","FALSE");
        }
    }*/

    public void insert(String[] details,String table){
        String insert  = "insert into CUSTOMER(name,username,password,phone,email,latitude,longitude) values('"+
                details[0]+"','"+details[1]+"','"+details[2]+"','"+
                Integer.parseInt(details[3])+"','"+details[4]+"','"+details[5]+"','"+details[6]+"');";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insert);
    }

    public int getDetails(){
        String query = "select * from CUSTOMER;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c.getCount();
    }

    public Cursor getData(String table){
        String query = "select * from "+table+";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c;
    }

    public Cursor getLoginData(String table,String user){
        String query = "select * from "+table+" where username='"+user+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query,null);
        return c;
    }

    /*public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "drop table student;";
        db.execSQL(query);
    }*/
}
