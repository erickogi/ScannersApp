package apps.kelvin.makau.scannerapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Eric on 1/11/2018.
 */

public class DbOperations {

    private Context context;
    private DbClass dbHandler;

    private boolean success = false;
    private double returnValueDouble = 0.0;
    private int returnValueInt = 0;

    public DbOperations(Context context) {
        dbHandler = new DbClass(context);
        this.context = context;
    }


    public int getCount(String tableName) {
        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = -1;
        try {
            cnt = cursor.getCount();
        } catch (Exception nm) {

        }
        cursor.close();
        return cnt;
    }


    public int getCount(String table, String column, String key) {
        String countQuery = "SELECT  * FROM '" + table + "' WHERE " + column + " = '" + key + "' ";
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = 0;
        try {
            cnt = cursor.getCount();
        } catch (Exception nm) {

        }
        cursor.close();
        return cnt;
    }

    public int getCount(String table, String column, String item, String s1, String s2) {
        String countQuery = "SELECT  * FROM '" + table + "' WHERE " + column + " = '" + item + "' OR " + column + " = '" + s1 + "' OR " + column + " = '" + s2 + "'";
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = 0;
        try {
            cnt = cursor.getCount();
        } catch (Exception nm) {

        }
        cursor.close();
        return cnt;
    }

    public boolean insert(String table, ContentValues values) {

        SQLiteDatabase db = dbHandler.getWritableDatabase();


        return db.insert(table, null, values) >= 1;


    }

    public boolean delete(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful = db.delete(table, column + "= '" + id + "' ", null) > 0;
        db.execSQL("DELETE  FROM " + table + " WHERE KEY_ID = " + id + " ");
        db.close();
        return true;
    }

    public boolean deleteitem(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful = db.delete(table, column + "= '" + id + "' ", null) > 0;
        db.execSQL("DELETE  FROM " + table + " WHERE " + column + " = " + id + " ");
        db.close();
        return true;
    }

    public boolean isThere(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        boolean isThere = false;


        String QUERY = "SELECT * FROM " + table + "  WHERE " + column + " = " + id + " ";


        Log.d("isT", QUERY);
        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            if (cursor.moveToNext()) {
                isThere = true;

            }
        }
        db.close();


        return isThere;
    }

    public boolean update(String table, String column, int id, ContentValues cv) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        boolean successful = db.update(table, cv, column + "=" + id + " ", null) > 0;
        db.close();
        return successful;
    }

    public Cursor select(String table) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = "SELECT * FROM  " + table + " ";

        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }


    }

    public Cursor select(String table, String column, String id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id + "' ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }
    }

    public Cursor select(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id + "'  ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }
    }



    public boolean delete(String table) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful =  db.execSQL("delete from "+ table)> 0;
        db.execSQL("delete from " + table);
        db.close();
        return true;
    }




    public Cursor select(String table, String column, String id1, String id2) {

        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id1 + "' OR " + column + " = '" + id2 + "' ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }

    }

    public Cursor select(String table, String column, String id1, String id2, String id3) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id1 + "' OR " + column + " = '" + id2 + "' OR " + column + " = '" + id3 + "' ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }
    }


}
