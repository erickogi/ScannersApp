package apps.kelvin.makau.scannerapp.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Eric on 1/11/2018.
 */

public class DbClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "scannerAppDb.db";


    public DbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private String creatTable(String tableName, HashMap<String, String> fieldNames) {

        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE " + tableName + " (");

        Set set = fieldNames.entrySet();
        Iterator iterator = set.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            if ((i + 1) != fieldNames.size()) {
                builder.append(mentry.getKey() + " " + mentry.getValue() + ", ");
            } else {
                builder.append(mentry.getKey() + " " + mentry.getValue());
            }
            i++;
        }
        builder.append(");");


        return builder.toString();


    }

    private HashMap<String, String> createTableProducts() {
        HashMap<String, String> fieldsName = new HashMap<>();
//        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");

        return fieldsName;

    }

    private HashMap<String, String> createTableData() {
        HashMap<String, String> fieldsName = new HashMap<>();
        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
        fieldsName.put(DbConstants.DATE, "varchar ");

        fieldsName.put(DbConstants.TimeStamp, "varchar ");
        fieldsName.put(DbConstants.MRZ_lines, "varchar ");
        fieldsName.put(DbConstants.Full_names, "varchar ");
        fieldsName.put(DbConstants.Date_of_issue, "varchar ");
        fieldsName.put(DbConstants.Serial_no, "varchar  ");
        fieldsName.put(DbConstants.Img_path, "varchar ");
        fieldsName.put(DbConstants.Country, "varchar ");
        fieldsName.put(DbConstants.Country_code, "varchar ");
        fieldsName.put(DbConstants.Sex, "varchar ");
        fieldsName.put(DbConstants.Id_no, "varchar  ");
        fieldsName.put(DbConstants.Dob, "varchar  ");

        fieldsName.put(DbConstants.Vehicle_plate, "varchar  ");
        fieldsName.put(DbConstants.Office, "varchar  ");
        fieldsName.put(DbConstants.Visit_type, "varchar  ");
        fieldsName.put(DbConstants.check_out_time, "varchar  ");
        fieldsName.put(DbConstants.comment, "varchar  ");
        fieldsName.put(DbConstants.Vehicle_plate, "varchar  ");



//        cv.put(DbConstants.DATE, kipande.getTimeStamp());
//        cv.put(DbConstants.Serial_no, kipande.getSerial_no());
//        cv.put(DbConstants.MRZ_lines, kipande.getMRZ_lines());
//        cv.put(DbConstants.Country_code, kipande.getCountry_code());
//        cv.put(DbConstants.Dob, kipande.getDob());
//        cv.put(DbConstants.Sex, kipande.getSex());
//        cv.put(DbConstants.Full_names, kipande.getFull_names());
//        cv.put(DbConstants.Date_of_issue, kipande.getDate_of_issue());
//        cv.put(DbConstants.Id_no, kipande.getId_no());
//        cv.put(DbConstants.Country, kipande.getCountry());
//        cv.put(DbConstants.Img_path, kipande.getImg_path());
//        cv.put(DbConstants.TimeStamp, kipande.getTimeStamp());
//        cv.put(DbConstants.Vehicle_plate, kipande.getVehicle_plate());
//        cv.put(DbConstants.Office, kipande.getOffice());
//        cv.put(DbConstants.Visit_type, kipande.getVisit_type());
//        cv.put(DbConstants.check_out_time, kipande.getCheck_out_time());
//        cv.put(DbConstants.comment, kipande.getComment());


        return fieldsName;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        // db.execSQL(creatTable(DbConstants.TABLE_FORM_DATA, createFormData()));
        db.execSQL(creatTable(DbConstants.TABLE_DATA, createTableData()));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(newVersion);

        //db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_FORM_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_DATA);


        onCreate(db);
    }

}
