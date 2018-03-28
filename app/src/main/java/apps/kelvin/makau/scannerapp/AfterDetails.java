package apps.kelvin.makau.scannerapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.bumptech.glide.Glide;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

import apps.kelvin.makau.scannerapp.BluePrints.BluePrint;
import apps.kelvin.makau.scannerapp.NetworkUtills.ApiConstants;
import apps.kelvin.makau.scannerapp.NetworkUtills.DumbVolleyRequest;
import apps.kelvin.makau.scannerapp.Sqlite.DbContentValues;
import apps.kelvin.makau.scannerapp.Utills.DateTimeUtils;
import apps.kelvin.makau.scannerapp.Utills.GeneralUtills;
import apps.kelvin.makau.scannerapp.Utills.UtilListeners.RequestListener;
import apps.kelvin.makau.scannerapp.models.Kipande;

import static apps.kelvin.makau.scannerapp.Utills.GeneralUtills.isFilledTextInputEditText;

public class AfterDetails extends AppCompatActivity {
EditText serialTv,id_noTv,namesTv,sexTv,doiTv,dobTv,mrzTv,edtCar,edtOffice;
ImageView img;
Spinner spinner;
private Button btnSave;

    private static final String TAG = "OcrCaptureActivity";

    // Intent request code to handle updating play services if needed.
    private static final int RC_HANDLE_GMS = 9001;

    // Permission request codes need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    // Constants used to pass extra data in the intent
    public static final String AutoFocus = "AutoFocus";
    public static final String UseFlash = "UseFlash";

    private Kipande mkips;
    private static final String LOG_TAG = "Text API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private Uri imageUri;
    private TextRecognizer detector;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_details);
       initVariables();
       attachViews();

    }

    private void attachViews() {
        mkips = getIntent().getParcelableExtra("Data");


        if(mkips!=null) {
            serialTv.setText(mkips.getSerial_no());
            id_noTv.setText(mkips.getId_no());
            namesTv.setText(mkips.getFull_names());
            sexTv.setText(mkips.getSex());
            doiTv.setText(mkips.getDoi());
            dobTv.setText(mkips.getDob());
            mrzTv.setText( mkips.getMRZ_lines());
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

        btnSave=findViewById(R.id.btn_save);
        img=findViewById(R.id.take_pic);
        spinner=findViewById(R.id.spinner_visit_type);

    }

    public void takePic(View view) {

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            takePicture();
           // createCameraSource(autoFocus, useFlash);
        } else {
            requestCameraPermission();
        }



    }
    private void requestCameraPermission() {
        Log.w("Camera", "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }

        final Activity thisActivity = this;

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions,
                        RC_HANDLE_CAMERA_PERM);
            }
        };

        Snackbar.make(btnSave, R.string.permission_camera_rationale,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, listener)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d("Camera", "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("Camera", "Camera permission granted - initialize the camera source");
            // We have permission, so create the camerasource
            boolean autoFocus = getIntent().getBooleanExtra(AutoFocus, false);
            boolean useFlash = getIntent().getBooleanExtra(UseFlash, false);
            //createCameraSource(autoFocus, useFlash);
            takePicture();
            return;
        }

        Log.e("Camera", "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Multitracker sample")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(R.string.ok, listener)
                .show();
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        BluePrint bluePrint = new BluePrint(AfterDetails.this);
        File out = bluePrint.createSaveMyImage("e-scanner","jpg");

        imageUri  = Uri.fromFile(out);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(intent, PHOTO_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {

            Glide.with(AfterDetails.this).load(imageUri).into(img);

            //croppId(imageUri);

        }

    }

    public void saveKipande(View view) {
        GeneralUtills generalUtills=new GeneralUtills(AfterDetails.this);
        if(isFilledTextInputEditText(edtOffice)&&isFilledTextInputEditText(id_noTv)
                &&isFilledTextInputEditText(namesTv)&&isFilledTextInputEditText(sexTv)&&generalUtills.isSpinnerSelected(spinner,"Select Visit")){

            String img="";
            String car="No";
            String id=id_noTv.getText().toString();
            String office=edtOffice.getText().toString();
            String visit=spinner.getSelectedItem().toString();
            String names=namesTv.getText().toString();
            String sex=sexTv.getText().toString();
            if(!edtCar.getText().toString().isEmpty()){
                car=edtCar.getText().toString();
            }
            if(imageUri!=null){
                img=imageUri.toString();
            }

            if(mkips!=null){
                mkips.setId_no(id);
                mkips.setOffice(office);
                mkips.setVehicle_plate(car);
                mkips.setVisit_type(visit);
                mkips.setFull_names(names);
                mkips.setSex(sex);
                mkips.setImg_path(img);
                mkips.setComment("Comment");
                mkips.setTimeStamp(DateTimeUtils.getNow());
                mkips.setCheck_out_time("Nill");
                Log.d("data","Office "+office+" Car  "+car+" visit  "+visit+" ");


                DumbVolleyRequest.kipande(mkips.getImg_path(), mkips, ApiConstants.upload, new RequestListener() {
                    @Override
                    public void onError(VolleyError error) {

                    }

                    @Override
                    public void onError(String error) {

                    }

                    @Override
                    public void onSuccess(String response) {


                        saveToDb(mkips);

                    }
                });


            }




        }
    }

    private void saveToDb(Kipande mkips){
        new DbContentValues.loadKipande(mkips, AfterDetails.this, new DbContentValues.MyInterface() {
            @Override
            public void onComplete(boolean result) {
                if(result){
                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    };

                    Snackbar.make(btnSave, "Saved Successfully",
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.ok, listener)
                            .show();


                }else {
                    View.OnClickListener listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //finish();

                        }
                    };

                    Snackbar.make(btnSave, "Error Saving",
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.ok, listener)
                            .show();
                }
            }
        }).execute();
    }
}
