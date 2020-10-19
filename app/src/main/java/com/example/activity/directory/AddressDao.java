package com.example.activity.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    private AddressDBHelper helper;

    public AddressDao(Context context) {
        this.helper = new AddressDBHelper(context);
    }

    public boolean insert(Address address){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",address.getName());
        values.put("phone",address.getPhone());
        return db.insert("address",null,values) > 0;
    }

    public List<Address> queryAll() {
        ArrayList<Address> addresses = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("address",null,null,null,null,null,null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Address address = new Address();
                address.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                address.setName(cursor.getString(cursor.getColumnIndex("name")));
                address.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                addresses.add(address);
            }
            cursor.close();
            db.close();
        }
        return addresses;
    }

    public boolean update(Address address) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",address.getName());
        values.put("phone",address.getPhone());
        return db.update("address",values,"_id=?",new String[]{String.valueOf(address.get_id())})>0;
    }
    public boolean delete(int _id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete("address","_id=?",new String[]{String.valueOf(_id)}) > 0;
    }

}
