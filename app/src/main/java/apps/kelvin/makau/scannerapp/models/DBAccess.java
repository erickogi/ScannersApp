package apps.kelvin.makau.scannerapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import java.util.ArrayList;


public class DBAccess {
    private  SQLiteDatabase db;
    Context context;


    public DBAccess(Context context) {
        db = context.openOrCreateDatabase("MotivationDB", Context.MODE_PRIVATE, null);
        this.context = context;

    }
    public void sendToDb(Kipande kipande) {
        open();

        db.execSQL("CREATE TABLE IF NOT EXISTS scanned(ID_NO VARCHAR UNIQUE,SERIAL VARCHAR,MRZ VARCHAR," +
                "COUNTRY_CODE VARCHAR,DOB VARCHAR,SEX VARCHAR," +
                "FULLNAMES VARCHAR,DOI VARCHAR,IMG_PATH VARCHAR,COUNTRY VARCHAR); ");

        String SERIAL = kipande.getSerial_no();
        String MRZ =kipande.getMRZ_lines();
        String COUNTRY_CODE =kipande.getCountry_code();
        String  DOB= kipande.getDob();
        String SEX = kipande.getSex();
        String FULLNAMES = kipande.getFull_names();
        String DOI = kipande.getDoi();
        String ID_NO = kipande.getId_no();
        String  COUNTRY=kipande.getCountry();
        String  IMG_PATH = kipande.getImg_path();



        try {

            ////////////////////////////
            ContentValues contentValues = new ContentValues();
            contentValues.put("SERIAL",SERIAL);
            contentValues.put("MRZ",MRZ);
            contentValues.put("COUNTRY_CODE",COUNTRY_CODE);
            contentValues.put("DOB",DOB);
            contentValues.put("SEX",SEX);
            contentValues.put("FULLNAMES",FULLNAMES);
            contentValues.put("DOI",DOI);
            contentValues.put("ID_NO",ID_NO);
            contentValues.put("COUNTRY",COUNTRY);
            contentValues.put("IMG_PATH",IMG_PATH);

            db.insert("scanned",null,contentValues);
            Toast.makeText(context, "Successfully saved", Toast.LENGTH_LONG).show();

        } catch (SQLiteException e) {
            Toast.makeText(context, "Failed!..Seems its saved already.", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    public void open(){
        db = context.openOrCreateDatabase("ScannedDB", Context.MODE_PRIVATE, null);
    }

    public  ArrayList<Kipande> getSavedKipandes(){
        ArrayList<Kipande> list = new ArrayList<>();
        open();
        Cursor cursor = null;
        String query = "SELECT *  FROM scanned";
        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Kipande newKipande = new Kipande();

                    newKipande.setId_no(cursor.getString(cursor.getColumnIndex("ID_NO")));
                    newKipande.setSerial_no(cursor.getString(cursor.getColumnIndex("SERIAL")));
                    newKipande.setMRZ_lines(cursor.getString(cursor.getColumnIndex("MRZ")));
                    newKipande.setCountry_code(cursor.getString(cursor.getColumnIndex("COUNTRY_CODE")));
                    newKipande.setDob(cursor.getString(cursor.getColumnIndex("DOB")));
                    newKipande.setSex((cursor.getString(cursor.getColumnIndex("SEX"))));
                    newKipande.setFull_names(cursor.getString(cursor.getColumnIndex("FULLNAMES")));
                    newKipande.setDoi(cursor.getString(cursor.getColumnIndex("DOI")));
                    newKipande.setCountry(cursor.getString(cursor.getColumnIndex("COUNTRY")));
                    newKipande.setImg_path(cursor.getString(cursor.getColumnIndex("IMG_PATH")));

                    list.add(newKipande);

                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        db.close();
        return  list;
    }

    public void  deleteFromFavDB(Kipande toDelete){

        open();
        String query = "DELETE FROM scanned WHERE ID_NO='"+toDelete.getId_no()+"' AND SERIAL = '"
                +toDelete.getSerial_no() + "'";
        try{
            db.execSQL(query);
            Toast.makeText(context, "Deleted Successfully..", Toast.LENGTH_SHORT).show();

        }catch (SQLiteException e){
            e.printStackTrace();
        }
        db.close();
    }
}
