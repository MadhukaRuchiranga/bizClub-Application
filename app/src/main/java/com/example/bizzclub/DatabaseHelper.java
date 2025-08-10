package com.example.bizzclub;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="appuserdata.db";
    public static final String TABLE_NAME="normalusers";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    //onCreate method
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+ TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,email TEXT, password TEXT)"
        );
    }

    //upGrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //Register a user
    public boolean insertUser(String username,String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("email",email);
        values.put("password",password);
        long result=db.insert(TABLE_NAME,null,values);
        return result!=-1;
    }
    // Check login
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE username = ? AND password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}
