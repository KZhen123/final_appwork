package com.example.final_appwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "memo.db";
    public static final String TB_NAME1 = "tb_study";
    public static final String TB_NAME2 = "tb_life";
    public static final String TB_NAME3 = "tb_users";

    private static final int VERSION = 1;
    //建三个表，用户、学习、生活
    public static final String create1 = "CREATE TABLE " + TB_NAME1 + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "USERID text," + "TITLE text," + "TIME text," + "BODY text)";
    public static final String create2 = "CREATE TABLE " + TB_NAME2 + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "USERID text," + "TITLE text," + "TIME text," + "BODY text)";
    public static final String create3 = "CREATE TABLE " + TB_NAME3 + "(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "USERID text," + "NAME text," + "PWD text)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create1);
        db.execSQL(create2);
        db.execSQL(create3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
