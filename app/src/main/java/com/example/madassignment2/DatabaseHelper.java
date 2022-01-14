package com.example.madassignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "empsalary.db";
    public static final String TABLE_NAME = "employee";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "DAYS_WORKED";
    public static final String COL_3 = "BONUS";
    public static final String COL_4 = "NET_SALARY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(NAME TEXT PRIMARY KEY, DAYS_WORKED TEXT, BONUS TEXT, NET_SALARY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String daysWorked, String bonus, String netSalary) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, daysWorked);
        contentValues.put(COL_3, bonus);
        contentValues.put(COL_4, netSalary);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Integer deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME=?", new String[]{name});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +TABLE_NAME, null);
        return cursor;
    }

    public boolean updateData(String name, String daysWorked, String bonus, String netSalary) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, daysWorked);
        contentValues.put(COL_3, bonus);
        contentValues.put(COL_4, netSalary);

        int num = db.update(TABLE_NAME,contentValues,"NAME=?",new String[]{(name)});
        //return num;
        return true;

    }
}




