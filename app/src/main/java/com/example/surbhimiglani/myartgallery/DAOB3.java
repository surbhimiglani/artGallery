package com.example.surbhimiglani.myartgallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surbhi Miglani on 29-04-2017.
 */

public class DAOB3  {
    private SQLiteDatabase database;
    private DBhelperA3 dbHelper;

    public DAOB3(Context context) {
        dbHelper = new DBhelperA3(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close any database object
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * insert a text report item to the location database table
     *
     * @param image
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addImage(BUG image) {
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COLUMN_PATH, image.getPath());
        cv.put(dbHelper.COLUMN_TITLE, image.getTitle());

        return database.insert(dbHelper.TABLE_BUG3, null, cv);
    }


    public List<BUG> getImages() {
        List<BUG> MyImages = new ArrayList<>();
        Cursor cursor =
                database.query(dbHelper.TABLE_BUG3, null, null, null, null,
                        null , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BUG MyImage = cursorToMyImage(cursor);
            MyImages.add(MyImage);
            cursor.moveToNext();
        }
        cursor.close();
        return MyImages;
    }

    private BUG cursorToMyImage(Cursor cursor) {
        BUG image = new BUG();

        image.setPath(
                cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_PATH)));
        image.setTitle(
                cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_TITLE)));
        return image;
    }
}
