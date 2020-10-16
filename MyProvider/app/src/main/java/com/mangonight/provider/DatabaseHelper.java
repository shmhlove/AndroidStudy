package com.mangonight.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "person";
    public static final String PERSON_ID = "_id";
    public static final String PERSON_NAME = "name";
    public static final String PERSON_AGE = "age";
    public static final String PERSON_MOBILE = "mobile";

    public static final String[] ALL_COLUMNS = {PERSON_ID, PERSON_NAME, PERSON_AGE, PERSON_MOBILE};

    private static final String CREATE_TABLE =
            "create table " + TABLE_NAME + "(" +
                PERSON_ID + " integer primary key autoincrement, " +
                PERSON_NAME + " text, " +
                PERSON_AGE + " integer, " +
                PERSON_MOBILE + " text" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}