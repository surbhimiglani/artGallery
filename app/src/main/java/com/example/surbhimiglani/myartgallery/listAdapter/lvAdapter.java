package com.example.surbhimiglani.myartgallery.listAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by Surbhi Miglani on 17-06-2017.
 */

public class lvAdapter {
    Context c;
    SQLiteDatabase db;
    lvHelper lv;

    public lvAdapter(Context c) {
        this.c = c;
        lv=new lvHelper(c);
    }

    public void openDB(){
        db=lv.getWritableDatabase();
    }

    public void closeDB(){
        lv.close();
    }

    //Select
    public Cursor retrieve(){
        String[] columns={lvDatabase.COLUMN_ID,lvDatabase.COLUMN_NAME};
        Cursor c=db.query(lvDatabase.TABLE_NAME,columns,null,null,null,null,null);
        return c;
    }

    public boolean update(String newName, int id){

        ContentValues cv=new ContentValues();
        cv.put(lvDatabase.COLUMN_NAME,newName);

        int result=db.delete(lvDatabase.TABLE_NAME,lvDatabase.COLUMN_ID+"=?", new String[]{String.valueOf(id)});
        if(result>0)
        {
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        int result=db.delete(lvDatabase.TABLE_NAME,lvDatabase.COLUMN_ID+"=?", new String[]{String.valueOf(id)});
        if(result>0)
        {
            return true;
        }
        return false;
    }

    public boolean add(String name)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(lvDatabase.COLUMN_NAME,name);
            long result=db.insert(lvDatabase.TABLE_NAME,lvDatabase.COLUMN_ID,cv);
            if(result>0)
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
