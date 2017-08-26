package com.example.vmac.WatBot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vmac.WatBot.model.ConversationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipemacedo on 24/08/17.
 */

public class ConversationDAO extends SQLiteOpenHelper {
    private final String table = "Conversation";

    public ConversationDAO(Context context) {
        super(context, "VWConversationChat", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + table + " (id INTEGER PRIMARY KEY, name TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, workspaceId TEXT NOT NULL, selected INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + table + " IF EXISTS";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public List<ConversationService> findAll() {
        List<ConversationService> credentials = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + table;

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()) {
            ConversationService conversation = new ConversationService();

            conversation.setId(cursor.getLong(cursor.getColumnIndex("id")));
            conversation.setName(cursor.getString(cursor.getColumnIndex("name")));
            conversation.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            conversation.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            conversation.setWorkspaceId(cursor.getString(cursor.getColumnIndex("workspaceId")));
            conversation.setSelected(cursor.getInt(cursor.getColumnIndex("selected")) != 0);

            credentials.add(conversation);
        }

        cursor.close();

        return credentials;
    }

    public ConversationService find(Long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + table + " WHERE id = ?";
        String args[] = {Long.toString(id)};

        Cursor cursor = db.rawQuery(sql, args);

        ConversationService conversation = null;

        if (cursor.moveToFirst()) {
            conversation = new ConversationService();

            conversation.setId(cursor.getLong(cursor.getColumnIndex("id")));
            conversation.setName(cursor.getString(cursor.getColumnIndex("name")));
            conversation.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            conversation.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            conversation.setWorkspaceId(cursor.getString(cursor.getColumnIndex("workspaceId")));
            conversation.setSelected(cursor.getInt(cursor.getColumnIndex("selected")) != 0);
        }

        cursor.close();

        return conversation;
    }

    public void insert(ConversationService conversationService) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", conversationService.getName());
        contentValues.put("username", conversationService.getUsername());
        contentValues.put("password", conversationService.getPassword());
        contentValues.put("workspaceId", conversationService.getWorkspaceId());
        contentValues.put("selected", conversationService.isSelected() ? 1 : 0);

        db.insert(table, null, contentValues);
    }
}
