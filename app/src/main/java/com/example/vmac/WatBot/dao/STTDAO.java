package com.example.vmac.WatBot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vmac.WatBot.model.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 26/08/17.
 */
public class STTDAO extends SQLiteOpenHelper {
    private final String table = "STT";

    public STTDAO(Context context) {
        super(context, "VWConversationChat", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + table + " (id INTEGER PRIMARY KEY, name TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, selected INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + table + " IF EXISTS";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<Service> findAll() {
        List<Service> credentials = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + table;

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            Service stt = new Service();

            stt.setId(cursor.getLong(cursor.getColumnIndex("id")));
            stt.setName(cursor.getString(cursor.getColumnIndex("name")));
            stt.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            stt.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            stt.setSelected(cursor.getInt(cursor.getColumnIndex("selected")) != 0);


            credentials.add(stt);
        }

        cursor.close();

        return credentials;
    }

    public Service find(Long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        String args[] = {Long.toString(id)};

        Cursor cursor = db.rawQuery(sql, args);

        Service stt = null;

        if (cursor.moveToFirst()) {
            stt = new Service();

            stt.setId(cursor.getLong(cursor.getColumnIndex("id")));
            stt.setName(cursor.getString(cursor.getColumnIndex("name")));
            stt.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            stt.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            stt.setSelected(cursor.getInt(cursor.getColumnIndex("selected")) != 0);
        }

        cursor.close();

        return stt;
    }

    public void insert(Service stt) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", stt.getName());
        contentValues.put("username", stt.getUsername());
        contentValues.put("password", stt.getPassword());
        contentValues.put("selected", stt.isSelected() ? 1 : 0);

        db.insert(table, null, contentValues);
    }
}