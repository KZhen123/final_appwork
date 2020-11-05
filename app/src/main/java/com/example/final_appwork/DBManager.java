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
    private final String TAG = "DBManager";

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME1 = DBHelper.TB_NAME1;
        TBNAME2 = DBHelper.TB_NAME2;
        TBNAME3 = DBHelper.TB_NAME3;
    }

    //插入备忘录表，cate=1时为学习，cate=2时为生活
    public void add_memo(int category, MemoItem item) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
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

    //插入待办表
    public void add_todo(TodoItem item) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("STATE", item.getState());
        values.put("BODY", item.getBody());
        Log.i(TAG,"state"+item.getState());
        Log.i(TAG,"body"+item.getBody());
        db.insert(TBNAME3, null, values);
        db.close();
    }

    //listview展示备忘录信息,cate=1时为学习，cate=2时为生活
    public List<MemoItem> list_memo(int cate) {
        List<MemoItem> memoList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        if (cate == 1) {
            cursor = db.query(TBNAME1, null, null, null, null, null, "TIME desc");
        } else if (cate == 2) {
            cursor = db.query(TBNAME2, null, null, null, null, null, "TIME desc");
        }
        if (cursor != null) {
            memoList = new ArrayList<MemoItem>();
            while (cursor.moveToNext()) {
                MemoItem item = new MemoItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
                item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
                item.setBody(cursor.getString(cursor.getColumnIndex("BODY")));
                memoList.add(item);
            }
            cursor.close();
        } else {
            Log.i(TAG, "cursor is null");
        }
        db.close();
        return memoList;
    }

    //展示待办项
    public List<TodoItem> list_todo() {
        List<TodoItem> todoList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME3, null, null, null, null, null, "STATE");
        if (cursor != null) {
            todoList = new ArrayList<TodoItem>();
            while (cursor.moveToNext()) {
                TodoItem item = new TodoItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setState(cursor.getString(cursor.getColumnIndex("STATE")));
                //Log.i(TAG,"STATE"+cursor.getString(cursor.getColumnIndex("STATE")));
                item.setBody(cursor.getString(cursor.getColumnIndex("BODY")));
                todoList.add(item);
            }
            cursor.close();
        } else {
            Log.i(TAG, "cursor is null");
        }
        db.close();
        return todoList;
    }

    //删除，cate=1为学习，cate=2为生活，cate=3为待办
    public void delete(int cate, int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (cate == 1)
            db.delete(TBNAME1, "ID=?", new String[]{String.valueOf(id)});
        else if (cate == 2)
            db.delete(TBNAME2, "ID=?", new String[]{String.valueOf(id)});
        else
            db.delete(TBNAME3, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }


    //更新备忘录信息,cate=1为学习，cate=2为生活
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
            item.setTitle(cursor.getString(cursor.getColumnIndex("TITLE")));
            item.setTime(cursor.getString(cursor.getColumnIndex("TIME")));
            item.setBody(cursor.getString(cursor.getColumnIndex("BODY")));
        }
        db.close();
        return item;
    }

    //更新待办的状态
    public void  update_todo(TodoItem todoitem){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        //改变状态
        if(todoitem.getState().equals("0")){
            values.put("STATE","1");
            Log.i(TAG,"state="+0);
        }else{
            values.put("STATE","0");
            Log.i(TAG,"state="+1);
        }
        db.update(TBNAME3, values, "ID=?", new String[]{String.valueOf(todoitem.getId())});
    }

/*
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
*/

}
