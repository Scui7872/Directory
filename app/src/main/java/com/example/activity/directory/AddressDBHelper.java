package com.example.activity.directory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AddressDBHelper extends SQLiteOpenHelper {

    public  AddressDBHelper(@Nullable Context context) {
        super(context,"address.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("CREATE TABLE address(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20),  phone VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists address");
        onCreate(db);
    }
}
