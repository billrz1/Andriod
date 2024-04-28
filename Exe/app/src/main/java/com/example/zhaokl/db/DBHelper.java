package com.example.zhaokl.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //数据库名称
    private static final String DB_NAME="users.db";
    //表名
    private static final String TBL_NAME="UsersTbl";
    //声明SQLiteDatabase对象
    private SQLiteDatabase db;
    public DBHelper(Context c) {
        super(c, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //获取SQLiteDatabase对象
        this.db=db;
        //创建表
        String CREATE_TABLE = "create table " + TBL_NAME
                + " (_id integer primary key autoincrement, "
                + "_name text not null, "
                + "_pwd text not null);";
        db.execSQL(CREATE_TABLE);
    }

    //插入
    public long insert(User entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_name", entity.getName());
        values.put("_pwd", entity.getPwd());
        long id = db.insert(TBL_NAME, null, values);
        db.close();
        return id;
    }


    //查询
    public Cursor query(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.query(TBL_NAME,null,null,null,null,null,null);
        return  c;
    }
    //验证用户名密码
    public boolean check(String name,String pwd)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.query(TBL_NAME,null,"_name = ? and _pwd = ?",new String[]{name,pwd},null,null,null);
        return c.moveToNext();
    }

    //删除
    public int delete(User entity) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = { entity.getId() };

        int rows = db.delete(TBL_NAME, whereClause, whereArgs);
        db.close();
        return rows;
    }

    //关闭数据库
    public void close(){
        if(db !=null)
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
