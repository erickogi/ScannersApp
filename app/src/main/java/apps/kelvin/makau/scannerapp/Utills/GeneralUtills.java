package apps.kelvin.makau.scannerapp.Utills;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import apps.kelvin.makau.scannerapp.GlobalConsts;
import apps.kelvin.makau.scannerapp.R;

//import com.erickogi14gmail.photozuri.GlobalConsts;
//import com.erickogi14gmail.photozuri.R;
//import com.erickogi14gmail.photozuri.Utills.UtilListeners.downloadListener;
//import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Target;

/**
 * Created by Eric on 12/21/2017.
 */

public class GeneralUtills {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    static String path;
    private static Spinner spinner;
    private static ArrayAdapter arrayAdapter;
    private static int downloaded = 0;
    private Context context;
    private String bitmapString = "";

    public GeneralUtills(Context context) {
        this.context = context;
    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    public static double checkFileSize(String f) {
        double file_size = 0.00;
        File file = new File(f);
        if (file.exists()) {
            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            double gigabytes = (megabytes / 1024);
            double terabytes = (gigabytes / 1024);
            double petabytes = (terabytes / 1024);
            double exabytes = (petabytes / 1024);
            double zettabytes = (exabytes / 1024);
            double yottabytes = (zettabytes / 1024);
            file_size = kilobytes;
        }
        return round((float) file_size,1);
    }

    public static int[] getResolution(String f) {
        //String pickedImagePath = "path/of/the/selected/file";
        int hw[] = new int[2];
        try {
            BitmapFactory.Options bitMapOption = new BitmapFactory.Options();
            bitMapOption.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(f, bitMapOption);
            int imageWidth = bitMapOption.outWidth;
            int imageHeight = bitMapOption.outHeight;

            hw[0] = imageHeight;
            hw[1] = imageWidth;
        } catch (Exception nm) {
            nm.printStackTrace();
            return null;
        }
        return hw;

    }
    public static String getImageCreationDate(String file) {
        String last_modified_date = "";
        File f = new File(file);
        ExifInterface intf = null;
        try {
            Date lastModDate = new Date(f.lastModified());
            last_modified_date = lastModDate.toString();
        } catch (Exception ex) {
        }
        if (intf == null) {
            Date lastModDate = new Date(f.lastModified());
            last_modified_date = lastModDate.toString();
        }
        Log.d("Dated : ", "" + last_modified_date);
        return last_modified_date;
    }

    public static int getRandon(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * @param target
     * @return
     */
    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * @param mobile
     * @return
     */
    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    /**
     * @param spinner
     * @param arrayAdapter
     */
    public static Spinner setSpinner(Spinner spinner, ArrayAdapter arrayAdapter) {

        spinner.setAdapter(arrayAdapter);

        return spinner;

    }

    /**
     * @param spinner
     * @param pos
     * @return
     */
    public static Spinner setSpinner(Spinner spinner, int pos) {

        spinner.setSelection(pos);


        return spinner;


    }

    /**
     * @param textInputEditText
     * @return
     */
    public static boolean isFilledTextInputEditText(TextInputEditText textInputEditText) {
        if ((textInputEditText == null) || textInputEditText.getText().toString().equals("")) {
            if (textInputEditText != null) {
                textInputEditText.setError("Required");
            }
            return false;
        }
        return true;

    }

    /**
     * @param field
     * @return
     */
    public static boolean validateFields(TextInputEditText field) {
        return isValidEmail(field.getText().toString()) || isValidPhoneNumber(field.getText().toString());

    }

    /**
     * Generate a value suitable for use in .
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static boolean isFilledTextInputEditText(EditText edttitle) {
        if ((edttitle == null) || edttitle.getText().toString().equals("")) {
            if (edttitle != null) {
                edttitle.setError("Required");
            }
            return false;
        }
        return true;


    }

//    public void upload(Context context,String path, ProgressBar progressBar){
//        Ion.with(context).load( "https://koush.clockworkmod.com/test/echo")
//                .uploadProgressBar(progressBar)
//                .setMultipartFile("filename.pdf", new File(path))
//                .asJsonObject()
//                .setCallback((e, result) -> {
//
//                });
//    }

    public static String sanitizePhone(String phone) {

        if (phone.equals("")) {
            return "";
        }

        if (phone.length() < 11 & phone.startsWith("0")) {
            String p = phone.replaceFirst("^0", "254");
            return p;
        }
        if (phone.length() == 13 && phone.startsWith("+")) {
            String p = phone.replaceFirst("^+", "");
            return p;
        }
        return phone;
    }

    public static Bitmap base64ToBitmap(String b64) {
        try {
            byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        } catch (Exception nm) {
            return null;
        }
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String getStringImage(Bitmap bmp) {
        String re = "n";
        try {
            // create lots of objects here and stash them somewhere
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (bmp != null) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                re = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            }
        } catch (OutOfMemoryError E) {

            // release some (all) of the above objects
        }



//        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
//        byte [] b = baos.toByteArray();
//        return Base64.encodeToString(b, Base64.DEFAULT);
        return re;
    }

    public static String getStringImage5(Bitmap bmp) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
        // return encodedImage;
    }

    public static Bitmap loadImagesFromStorage(String path, Context context) {
        Bitmap b = null;
        try {
            File f = new File(path);
            b = BitmapFactory.decodeStream(new FileInputStream(f));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("loadException", e.toString());
        }

        return b;
    }

    public static boolean isBelowRightResolution(String path) {
        //int rightHeight= GlobalConsts.RightHeightDim;
        int res[] = {240, 240};
        try {
            res = getResolution(path);
        } catch (Exception nm) {
            nm.printStackTrace();
        }

        return res != null && (res[0] < GlobalConsts.RightHeightDim || res[1] < GlobalConsts.RightWidthDim);


    }

    public boolean isEmptyOrNullOrnull(String s, String msg) {
        if (s != null && !s.isEmpty() && !s.equals("null") && !s.equals("Null")) return false;
        errorToast(msg);
        return true;
    }

    /**
     * @param radioGroup
     * @param msg
     * @param context
     * @return
     */
    public boolean isSelected_RadioGroup(RadioGroup radioGroup, String msg) {

        if (radioGroup.getCheckedRadioButtonId() > -1) {

            return true;
        }
        errorToast(msg);
        return false;

    }

    /**
     * @param spinner
     * @param errmsg
     * @return
     */
    public boolean isSpinnerSelected(Spinner spinner, String errmsg) {
        if (spinner.getSelectedItemPosition() < 1) {
            errorToast(errmsg);
            return false;
        }
        return true;
    }

    /**
     * @param button
     * @param defaultText
     * @param errMsg
     *
     * @return
     */
    public boolean isButtonTextChanged(Button button, String defaultText, String errMsg) {
        if (button.getText().toString().equals(defaultText)) {
            errorToast(errMsg);
            return false;
        }
        return true;
    }

    /**
     * @param msg

     */
    private void errorToast(String msg) {
        MyToast.toast(msg, context, R.drawable.ic_error_outline_black_24dp, Constants.TOAST_LONG);
    }

    public String saveProfilePic(Bitmap resizedbitmap, String name) {
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File mypath = new File(directory, name + "thumbnail.png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            //   new AndroidBmpUtil().save(bmImage, file);
            //resizedbitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            new AndroidBmpUtil().save(resizedbitmap, mypath.getPath());
            AndroidBmpUtil androidBmpUtil = new AndroidBmpUtil();

            if (androidBmpUtil.save(resizedbitmap, mypath.getAbsolutePath())) {
                Log.d("mypathsave", mypath.getAbsolutePath());
            }
            fos.close();
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
        }
        return mypath.getAbsolutePath();
    }

    public Bitmap loadImageFromStorage(String name) {
        Bitmap b = null;
        try {
            ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
            File path1 = cw.getDir("images", Context.MODE_PRIVATE);
            File f = new File(path1, name + "thumbnail.png");

            Log.d("drawingbit", f.toString() + "    \n");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            // ImageView img = (ImageView) findViewById(R.id.viewImage);
            // img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("loadException", e.toString());
        }

        return b;
    }

    public Bitmap loadImageFromStorage2(String path) {
        Bitmap b = null;
        try {
            //  ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
            //  File path1 = cw.getDir("images", Context.MODE_PRIVATE);
            File f = new File(path);

            // Log.d("drawingbit", f.toString() + "    \n");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            // ImageView img = (ImageView) findViewById(R.id.viewImage);
            // img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Log.d("loadException", e.toString());
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            //Log.d("outOfMemory", e.toString());
        }

        return b;
    }

}
