package com.example.surbhimiglani.myartgallery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Surbhi Miglani on 29-04-2017.
 */

public class DBhelperA2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BF2.db";
    public static final String TABLE_BUG2 = "BUGFINDER";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_PATH="path";


    public DBhelperA2(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = ("CREATE TABLE " + TABLE_BUG2 + "(" + COLUMN_PATH+" VARCHAR,"+COLUMN_TITLE + " VARCHAR," + COLUMN_IMAGE + " BLOG)");
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUG2);
        onCreate(db);

    }
}
