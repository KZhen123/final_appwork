package com.example.final_appwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private String TBNAME1;
    private String TBNAME2;
    private String TBNAME3;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME1 = DBHelper.TB_NAME1;
        TBNAME2 = DBHelper.TB_NAME2;
        TBNAME3 = DBHelper.TB_NAME3;
    }

    //插入备忘录表，category=1时为学习，category=2时为生活
    public void add_memo(int category, MemoItem item) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERID", item.getUserId());
        values.put("TITLE", item.getTitle());
        values.put("TIME", item.getTime());
        values.put("BODY", item.getBody());
        if (category == 1) {
            db.insert(TBNAME1, null, values);
        } else {
            db.insert(TBNAME2, null, values);
        }
        db.close();
    }

    //插入用户表
    public void add_users(UserItem item) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("USERID", item.getUserId());
        values.put("NAME", item.getName());
        values.put("PWD", item.getPwd());
        db.insert(TBNAME3, null, values);
        db.close();
    }

    //listview展示备忘录信息
    public List<MemoItem> list_study(String userID) {
        List<MemoItem> memoList = null;
        //ArrayList<HashMap<String, String>>
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME1, null, "USERID=?", new String[]{String.valueOf(userID)}, null, null, null, null);
        if (cursor != null) {
            memoList = new ArrayList<MemoItem>();
            while (cursor.moveToNext()) {
                MemoItem item = new MemoItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setUserId(cursor.getString(cursor.getColumnIndex("USERID")));
                item.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
                item.setBody(cursor.getString(cursor.getColumnIndex("BODY")));
                memoList.add(item);
            }
            cursor.close();
        }
        db.close();
        return memoList;
    }

    //删除
    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME1, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAll_users() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TBNAME3, null, null);
        db.close();
    }


    //检测表中是否有某个userID
    public boolean findById(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME3, null, "USERID=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public boolean isMatch(String id, String pwd) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME3, null, "USERID=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String pwd_query = cursor.getString(cursor.getColumnIndex("PWD"));
            if (pwd_query.equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    //展示用户信息
    public void show_users() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME3, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String userID = cursor.getString(cursor.getColumnIndex("USERID"));
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String pwd = cursor.getString(cursor.getColumnIndex("PWD"));
                Log.i("SHOW", userID);
                Log.i("SHOW", name);
                Log.i("SHOW", pwd);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
    }

    //展示备忘录信息
    public void show_memo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME1, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String userID = cursor.getString(cursor.getColumnIndex("USERID"));
                String title = cursor.getString(cursor.getColumnIndex("TITLE"));
                String time = cursor.getString(cursor.getColumnIndex("TIME"));
                String body = cursor.getString(cursor.getColumnIndex("BODY"));
                Log.i("SHOW", userID);
                Log.i("SHOW", title);
                Log.i("SHOW", time);
                Log.i("SHOW", body);
            }
            while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
    }

    //更新备忘录信息
    public void update_memo(int cate, MemoItem memoItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", memoItem.getTitle());
        values.put("TIME", memoItem.getTime());
        values.put("BODY", memoItem.getBody());
        if (cate == 1) {
            db.update(TBNAME1, values, "ID=?", new String[]{String.valueOf(memoItem.getId())});
        } else {
            db.update(TBNAME2, values, "ID=?", new String[]{String.valueOf(memoItem.getId())});
        }
        db.close();
    }

    //获取某个id对应的memo项
    public MemoItem findById(int cate, int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        if (cate == 1) {
            cursor = db.query(TBNAME1, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);
        } else {
            cursor = db.query(TBNAME2, null, "ID=?", new String[]{String.valueOf(id)}, null, null, null);

        }
        MemoItem item = new MemoItem();
        if (cursor != null && cursor.moveToFirst()) {
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setUserId(cursor.getString(cursor.getColumnIndex("USERID")));
            item.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            item.setBody(cursor.getString(cursor.getColumnIndex("BODY")));
        }
        db.close();
        return item;
    }

}
