package com.example.sajib.sqlloginpage_demo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_name = "myuserdetails.db";
    private static final String Table_Name = "alluserdetails";
    private static final String Id = "id";
    private static final String Name = "name";
    private static final String Email = "email";
    private static final String Password = "password";
    private static final String Username = "username";
    private static final int Version_Number = 8;
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE " + Table_Name + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " VARCHAR(255)," + Email + " VARCHAR(255)," + Username + " VARCHAR(15)," + Password + " VARCHAR(15));";
    private static final String Drop_Table = "DROP TABLE IF EXISTS " + Table_Name;

    public DatabaseHelper(Context context) {
        super(context, Database_name, null, Version_Number);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Oncrete is called", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {

            Toast.makeText(context, "Exception", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
            db.execSQL(Drop_Table);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception", Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(UserDetails userDetails) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name, userDetails.getName());
        contentValues.put(Email, userDetails.getEmail());
        contentValues.put(Username, userDetails.getUsername());
        contentValues.put(Password, userDetails.getPassword());

        long rowId = sqLiteDatabase.insert(Table_Name, null, contentValues);
        return rowId;


    }

    public boolean finpassword(String uname, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name, null);
        boolean result = false;
        if(cursor.getCount()==0){
            Toast.makeText(context, "No dat found", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                String username=cursor.getString(3);
                String password=cursor.getString(4);
                if(username.equals(uname)&&password.equals(pass)){
                    result=true;
                    break;
                }

            }
        }
    return result;
    }
}
