package com.example.surbhimiglani.myartgallery.listAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Surbhi Miglani on 17-06-2017.
 */

public class lvHelper extends SQLiteOpenHelper {

    public lvHelper(Context context){
        super(context, lvDatabase.DATABASE_NAME, null, lvDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_TB="CREATE TABLE "+lvDatabase.TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name VARCHAR)";
        db.execSQL(create_TB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + lvDatabase.TABLE_NAME);
        onCreate(db);
    }
}
