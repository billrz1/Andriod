package com.oyoung.diary.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DiaryHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "diary.db";
    private static final int DB_VERSION = 2;
    private static DiaryHelper diaryHelper = null;
    private static final String CREATE_DIARY = "create table Diary ("
            + "id integer primary key autoincrement, "
            + "title text, "
            + "description text)";

    public DiaryHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DiaryHelper getInstance(Context context) {
        if (diaryHelper == null) {
            diaryHelper = new DiaryHelper(context);
        }
        return diaryHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Diary");
        onCreate(db);
    }

    public void insert(String title, String description) throws RuntimeException {
        if (diaryHelper == null) {
            throw new RuntimeException("未创建数据库");
        }
        SQLiteDatabase db = diaryHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        db.insert("Diary", null, values);
    }

    public void modify(int id, String title, String description) throws RuntimeException {
        if (diaryHelper == null) {
            throw new RuntimeException("未创建数据库");
        }
        SQLiteDatabase db = diaryHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (!title.isEmpty()) {
            values.put("title", title);
        }
        if (!description.isEmpty()) {
            values.put("description", description);
        }
        Log.e("", "modify: " + id);
        db.update("Diary", values, "id = ?", new String[] {String.valueOf(id)});
    }

    public void delete(int id) throws RuntimeException {
        if (diaryHelper == null) {
            throw new RuntimeException("未创建数据库");
        }
        SQLiteDatabase db = diaryHelper.getWritableDatabase();
        Log.e("", "delete: " + id);
        db.delete("Diary", "id = ?", new String[] {String.valueOf(id)});
    }

    public List<Diary> query() throws RuntimeException {
        if (diaryHelper == null) {
            throw new RuntimeException("未创建数据库");
        }
        List<Diary> diaries = new ArrayList<>();
        SQLiteDatabase db = diaryHelper.getReadableDatabase();
        Cursor cursor = db.query("Diary", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range")
                String description = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Diary diary = new Diary(id, title, description);
                diaries.add(diary);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diaries;
    }
}
