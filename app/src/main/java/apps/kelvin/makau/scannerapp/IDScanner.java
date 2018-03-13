 package apps.kelvin.makau.scannerapp;


 import android.Manifest;
 import android.content.Context;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.net.Uri;
 import android.os.Bundle;
 import android.os.Environment;
 import android.provider.MediaStore;
 import android.support.annotation.NonNull;
 import android.support.v4.app.ActivityCompat;
 import android.support.v4.content.FileProvider;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.util.SparseArray;
 import android.view.View;
 import android.widget.Button;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.android.gms.vision.Frame;
 import com.google.android.gms.vision.text.Text;
 import com.google.android.gms.vision.text.TextBlock;
 import com.google.android.gms.vision.text.TextRecognizer;
 import com.innovatrics.mrz.MrzParseException;
 import com.innovatrics.mrz.MrzParser;
 import com.innovatrics.mrz.MrzRange;
 import com.innovatrics.mrz.MrzRecord;
 import com.innovatrics.mrz.records.MrtdTd1;
 import com.innovatrics.mrz.types.MrzDocumentCode;
 import com.innovatrics.mrz.types.MrzFormat;
 import com.theartofdev.edmodo.cropper.CropImage;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 import apps.kelvin.makau.scannerapp.BluePrints.BluePrint;

 public class IDScanner extends AppCompatActivity {
    private static final String LOG_TAG = "Text API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private Uri imageUri;
    private TextRecognizer detector;
    private static final int REQUEST_WRITE_PERMISSION = 20;


     private Uri fimgUri;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idscanner  );
        Button button = (Button) findViewById(R.id.button);
        scanResults = (TextView) findViewById(R.id.results);


        detector = new TextRecognizer.Builder(getApplicationContext()).build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(IDScanner.this, new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
               // takePicture();

            }
        });

        ////////////////////////////////////
         final String data_to_perse = "IDKYA2359260760<<3222<<<<<3222\n9401219M1402058<B031813279M<<3\nKELVIN<MAKAU<<<<<<<<<<<<<<<<<<";

         MrzRecord record = MrzParser.parse(data_to_perse);



    Toast.makeText(this, record.documentNumber, Toast.LENGTH_SHORT).show();
    Toast.makeText(this, "Serial: " +data_to_perse.subSequence(5,14).toString().replace("<",""), Toast.LENGTH_LONG).show();
    Toast.makeText(this, "DOB: " +data_to_perse.subSequence(30,37).toString().replace("<",""), Toast.LENGTH_LONG).show();
    Toast.makeText(this, "Sex: " +data_to_perse.subSequence(38,39).toString().replace("<",""), Toast.LENGTH_LONG).show();
    Toast.makeText(this, "Date of Issue: " +data_to_perse.subSequence(39,45).toString().replace("<",""), Toast.LENGTH_LONG).show();
         Toast.makeText(this, "Id No: " +data_to_perse.subSequence(49,57).toString().replace("<",""), Toast.LENGTH_LONG).show();
         Toast.makeText(this, "Names: " +data_to_perse.subSequence(62,data_to_perse.length()).toString().replace("<"," ").trim(), Toast.LENGTH_LONG).show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(IDScanner.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {


            croppId(imageUri);

        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                 fimgUri = result.getUri();

               /* Intent intee = new Intent(this, IDScanner.class);
                intee.putExtra("path", result.getUri().getPath());

                startActivity(intee);*/
                launchMediaScanIntent();
                try {
                    Bitmap bitmap = decodeBitmapUri(this, fimgUri);
                    if (detector.isOperational() && bitmap != null) {
                        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                        SparseArray<TextBlock> textBlocks = detector.detect(frame);
                        String blocks = "";
                        String lines = "";
                        String words = "";

                        String dat = "";
                        for (int index = 0; index < textBlocks.size(); index++) {
                            //extract scanned text blocks here
                            TextBlock tBlock = textBlocks.valueAt(index);
                            dat = textBlocks.valueAt(index).getValue();
                            blocks = blocks + tBlock.getValue() + "\n" + "\n";
                            for (Text line : tBlock.getComponents()) {
                                //extract scanned text lines here
                                lines = lines + line.getValue() + "\n";
                                for (Text element : line.getComponents()) {
                                    //extract scanned text words here
                                    words = words + element.getValue() + ", ";
                                }
                            }
                        }
                        if (textBlocks.size() == 0) {
                            scanResults.setText("Scan Failed: Found nothing to scan");
                        } else {

                            if (blocks.toUpperCase().startsWith("IDKYA")) {
                                final String data_parsed = blocks.trim().toString();


/*
                                    buffText(lines);
                                    Intent datas = new Intent(this, AfterDetails.class);
                                    datas.putExtra("capt", dat);
                                    datas.putExtra("ser", buffText(dat));
                                    startActivity(datas);*/

                                Toast.makeText(this, data_parsed.toString().trim(), Toast.LENGTH_SHORT).show();
                                scanResults.setText(scanResults.getText() + "MRZ block: " + "\n");
                                scanResults.setText(scanResults.getText() + data_parsed.toString().trim() + "\n");

                                scanResults.setText(scanResults.getText() + "---------" + "\n");
                                scanResults.setText(scanResults.getText() + "Parsed: " + "\n");

                                if(checkKYA(data_parsed.toString().trim()) && data_parsed.toString().length() == 90)
                                Toast.makeText(this, "Serial: " +data_parsed.toString().trim().subSequence(5,14).toString().replace("<",""), Toast.LENGTH_LONG).show();
                                Toast.makeText(this, "DOB: " +data_parsed.toString().trim().subSequence(30,37).toString().replace("<",""), Toast.LENGTH_LONG).show();
                                Toast.makeText(this, "Sex: " +data_parsed.toString().trim().subSequence(38,39).toString().replace("<",""), Toast.LENGTH_LONG).show();
                                Toast.makeText(this, "Date of Issue: " +data_parsed.toString().trim().subSequence(39,45).toString().replace("<",""), Toast.LENGTH_LONG).show();
                                Toast.makeText(this, "Id No: " +data_parsed.toString().trim().subSequence(49,57).toString().replace("<",""), Toast.LENGTH_LONG).show();
                                Toast.makeText(this, "Names: " +data_parsed.toString().trim().subSequence(62,data_parsed.toString().length()).toString().replace("<"," ").trim(), Toast.LENGTH_LONG).show();

                           /* scanResults.setText(scanResults.getText() + "---------" + "\n");
                            scanResults.setText(scanResults.getText() + "Lines: " + "\n");
                            scanResults.setText(scanResults.getText() + lines + "\n");
                            scanResults.setText(scanResults.getText() + "---------" + "\n");
                            scanResults.setText(scanResults.getText() + "Words: " + "\n");
                            scanResults.setText(scanResults.getText() + words + "\n");
                            scanResults.setText(scanResults.getText() + "---------" + "\n");*/
                            }
                        }
                    } else {
                        scanResults.setText("Could not set up the detector!");
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
                            .show();
                    Log.e(LOG_TAG, e.toString());
                }

            }
        }
    }

     private String isID(String trim) {
         //    String txt="IDKYA2359260760<<3222<<<<<32229401219M1402058<B031813279M<<3KELVIN<MAKAU<<<<<<<<<<<<<<<<<<";
            String txt=trim;
String data="";
         String re1="(IDKYA)";	// Word 1

         Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
         Matcher m = p.matcher(txt);
         if (m.find())
         {
             String word1=m.group(1);
          data= word1.toString();
             Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
         }
         return data;
     }

     private void croppId(Uri imageUri) {
         CropImage.activity(imageUri)
                 .start(this);


     }

            private boolean checkKYA(String data_to) {
                //  String data="";
                String re1=".*?";	// Non-greedy match on filler
                String re2="((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))(?:[0]?[1-9]|[1][012])(?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";	// YYYYMMDD 1

                Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                Matcher m = p.matcher(data_to);
                return m.find();
            }

     private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        BluePrint bluePrint = new BluePrint(IDScanner.this);
        File out = bluePrint.createSaveMyImage("e-scanner","jpg");

           imageUri  = Uri.fromFile(out);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        startActivityForResult(intent, PHOTO_REQUEST);
    }



    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }
}