package apps.kelvin.makau.scannerapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import apps.kelvin.makau.scannerapp.Sqlite.DbConstants;
import apps.kelvin.makau.scannerapp.Sqlite.DbOperations;
import apps.kelvin.makau.scannerapp.Utills.DateTimeUtils;
import apps.kelvin.makau.scannerapp.models.Kipande;

public class Vistor extends AppCompatActivity {
    private Kipande mkips;
    TextView serialTv,id_noTv,namesTv,sexTv,doiTv,dobTv,mrzTv,edtCar,edtOffice,edtVist;
    ImageView img;
    Spinner spinner;
    private Button btnSave;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initVariables();
        attachViews();
    }

    private void attachViews() {
        mkips = getIntent().getParcelableExtra("Data");

        Log.d("data","viiOffice "+mkips.getOffice()+" Car  "+mkips.getVehicle_plate()+" visit  "+mkips.getVisit_type()+" ");

        if(mkips!=null) {
            serialTv.setText("Serial No.: " + mkips.getSerial_no());
            id_noTv.setText("Id No.: " + mkips.getId_no());
            namesTv.setText("Names: " + mkips.getFull_names());
            sexTv.setText("Sex: " + mkips.getSex());
            doiTv.setText("Date of Issue: " + mkips.getDoi());
            dobTv.setText("Date of Birth: " + mkips.getDob());
            mrzTv.setText("Mrz: " + mkips.getMRZ_lines());

            edtCar.setText("Car : "+mkips.getVehicle_plate());
            edtOffice.setText("Office : "+mkips.getOffice());
            edtVist.setText("Vist : "+mkips.getVisit_type());

            Glide.with(Vistor.this).load(mkips.getImg_path()).into(img);
        }



    }

    private void initVariables() {
        serialTv = findViewById(R.id.serialTV);
        id_noTv = findViewById(R.id.id_noTV);
        namesTv = findViewById(R.id.namesTV);
        sexTv = findViewById(R.id.sexTV);
        doiTv = findViewById(R.id.doiTV);
        dobTv = findViewById(R.id.dobTV);
        mrzTv = findViewById(R.id.mrzTV);
        edtCar = findViewById(R.id.edt_car);
        edtOffice = findViewById(R.id.edt_office);
        edtVist = findViewById(R.id.edt_vist);

        btnSave=findViewById(R.id.btn_save);
        img=findViewById(R.id.take_pic);
       // spinner=findViewById(R.id.spinner_visit_type);

    }

    public void checkout(View view) {

    }

    public void checkOut(View view) {
        ContentValues cv=new ContentValues();
        cv.put(DbConstants.check_out_time, DateTimeUtils.getNow());

        DbOperations dbOperations=new DbOperations(Vistor.this);
        if(dbOperations.update(DbConstants.TABLE_DATA,DbConstants.KEY_ID,mkips.getKEY_ID(),cv)){
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            };

            Snackbar.make(view, "Action was Successfully",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, listener)
                    .show();
        }else {
            Snackbar.make(view, "Error Checking out", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
