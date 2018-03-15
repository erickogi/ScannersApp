package apps.kelvin.makau.scannerapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import apps.kelvin.makau.scannerapp.models.Kipande;

/**
 * Created by Eric on 1/17/2018.
 */

public class DbContentValues {
   
    public ArrayList<Kipande> getKipande(Cursor cursor,boolean all) {


        ArrayList<Kipande> kipandes = new ArrayList<>();


        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
              


                Kipande kipande = new Kipande();

                kipande.setKEY_ID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));
                kipande.setSerial_no(cursor.getString(cursor.getColumnIndex(DbConstants.Serial_no)));
                kipande.setMRZ_lines(cursor.getString(cursor.getColumnIndex(DbConstants.MRZ_lines)));
                kipande.setCountry_code(cursor.getString(cursor.getColumnIndex(DbConstants.Country_code)));
                kipande.setDob(cursor.getString(cursor.getColumnIndex(DbConstants.Dob)));
                kipande.setSex(cursor.getString(cursor.getColumnIndex(DbConstants.Sex)));
                kipande.setFull_names(cursor.getString(cursor.getColumnIndex(DbConstants.Full_names)));
                kipande.setDate_of_issue(cursor.getString(cursor.getColumnIndex(DbConstants.Date_of_issue)));

                kipande.setId_no(cursor.getString(cursor.getColumnIndex(DbConstants.Id_no)));
                kipande.setCountry(cursor.getString(cursor.getColumnIndex(DbConstants.Country)));
                
                kipande.setImg_path(cursor.getString(cursor.getColumnIndex(DbConstants.Img_path)));
                kipande.setTimeStamp(cursor.getString(cursor.getColumnIndex(DbConstants.TimeStamp)));
                kipande.setVehicle_plate(cursor.getString(cursor.getColumnIndex(DbConstants.Vehicle_plate)));
               
                kipande.setOffice(cursor.getString(cursor.getColumnIndex(DbConstants.Office)));
                kipande.setVisit_type(cursor.getString(cursor.getColumnIndex(DbConstants.Visit_type)));
                kipande.setCheck_out_time(cursor.getString(cursor.getColumnIndex(DbConstants.check_out_time)));
                kipande.setComment(cursor.getString(cursor.getColumnIndex(DbConstants.comment)));
                if(!all&&!kipande.getCheck_out_time().equals("Nill")){

                }else {
                    Log.d("data","dcOffice "+kipande.getOffice()+" Car  "+kipande.getVehicle_plate()+" visit  "+kipande.getVisit_type()+" ");

                    kipandes.add(kipande);
                }

               // Log.d("data","Office "+kipande.getOffice()+" Car  "+kipande.getVehicle_plate()+" visit  "+kipande.getVisit_type()+" ");

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }


        return kipandes;
    }



    public interface MyInterface {
        void onComplete(boolean result);


    }

    public interface MyInterfaceTitles {

        void onComplete(int titleid);
    }

    public static class loadKipande extends AsyncTask<Void, Void, Boolean> {

        private final ThreadLocal<Context> context = new ThreadLocal<>();
        private Kipande kipandes;
        private MyInterface mListener;


        private DbOperations dbOperations;



        public loadKipande(Kipande kipandes, Context context,  MyInterface myInterface) {

            this.context.set(context);
            this.kipandes = kipandes;
            this.mListener = myInterface;

            dbOperations = new DbOperations(context);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean inserted = false;
            //for (int a = 0; a < kipandes.size(); a++) {
                Kipande kipande = kipandes;
                ContentValues cv = new ContentValues();
                //cv.put(DbConstants.IMAGE_STATUS,status);

                cv.put(DbConstants.DATE, kipande.getTimeStamp());
                cv.put(DbConstants.Serial_no, kipande.getSerial_no());
                cv.put(DbConstants.MRZ_lines, kipande.getMRZ_lines());
                cv.put(DbConstants.Country_code, kipande.getCountry_code());
                cv.put(DbConstants.Dob, kipande.getDob());
                cv.put(DbConstants.Sex, kipande.getSex());
                cv.put(DbConstants.Full_names, kipande.getFull_names());
                cv.put(DbConstants.Date_of_issue, kipande.getDate_of_issue());
                cv.put(DbConstants.Id_no, kipande.getId_no());
                cv.put(DbConstants.Country, kipande.getCountry());
                cv.put(DbConstants.Img_path, kipande.getImg_path());
                cv.put(DbConstants.TimeStamp, kipande.getTimeStamp());
                cv.put(DbConstants.Vehicle_plate, kipande.getVehicle_plate());
                cv.put(DbConstants.Office, kipande.getOffice());
                cv.put(DbConstants.Visit_type, kipande.getVisit_type());
                cv.put(DbConstants.check_out_time, kipande.getCheck_out_time());
                cv.put(DbConstants.comment, kipande.getComment());

                inserted = dbOperations.insert(DbConstants.TABLE_DATA, cv);


          //  }

            return inserted;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            mListener.onComplete(aBoolean);


        }


    }


}
