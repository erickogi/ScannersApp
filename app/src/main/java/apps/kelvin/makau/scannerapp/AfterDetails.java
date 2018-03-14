package apps.kelvin.makau.scannerapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import apps.kelvin.makau.scannerapp.models.Kipande;

public class AfterDetails extends AppCompatActivity {
TextView serialTv,id_noTv,namesTv,sexTv,doiTv,dobTv,mrzTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_details);
       initVariables();
       attachViews();

    }

    private void attachViews() {
        Kipande mkips = getIntent().getParcelableExtra("Data");
        serialTv.setText("Serial No.: "+mkips.getSerial_no());
        id_noTv.setText("Id No.: "+mkips.getId_no());
        namesTv.setText("Names: "+mkips.getFull_names());
        sexTv.setText("Sex: "+mkips.getSex());
        doiTv.setText("Date of Issue: "+mkips.getDoi());
        dobTv.setText("Date of Birth: "+mkips.getDob());
        mrzTv.setText("Mrz: "+mkips.getMRZ_lines());

    }

    private void initVariables() {
        serialTv = findViewById(R.id.serialTV);
        id_noTv = findViewById(R.id.id_noTV);
        namesTv = findViewById(R.id.namesTV);
        sexTv = findViewById(R.id.sexTV);
        doiTv = findViewById(R.id.doiTV);
        dobTv = findViewById(R.id.dobTV);
        mrzTv = findViewById(R.id.mrzTV);

    }
}
