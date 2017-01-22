package com.example.vishvraj.remind_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vishvraj on 22-01-2017.
 */

public class DatabaseHelper_MainCategory extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase.db";
    public static final String TABLE_NAME = "category_table";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";

    public DatabaseHelper_MainCategory(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

   public boolean insertData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        long result= db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
   public Cursor getAllData(){
       SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("SELECT rowid _id,* FROM "+TABLE_NAME,null);
        return res;
    }
}

