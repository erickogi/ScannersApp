package apps.kelvin.makau.scannerapp.BluePrints;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BluePrint {
    private File file;
    private Context context;
    private File filee;
    private File file1;

    public BluePrint(Context ctx) {
        this.context = ctx;
    }


    public File createSaveMyImage(String folderName, String fileName,String extension) {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName + "/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        filee = new File(folder, fileName+"."+extension);
        return  filee;
    }
        public void createSaveMyImage(String folderName, String fileName, Bitmap imageBitmap) {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName + "/");
        try {
            if (folder.exists()) {
                File file = new File(folder, fileName);

                try {
                    FileOutputStream out = new FileOutputStream(file);

                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

                    out.flush();
                    out.close();

                    MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {

                        }
                    });

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if(!folder.exists()) {
                folder.mkdirs();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createSaveMyImage(String folderName, String fileName,String extension,Bitmap imageBitmap) {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName + "/");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
                 file = new File(folder, fileName+"."+extension);

                try {
                    FileOutputStream out = new FileOutputStream(file);

                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

                    out.flush();
                    out.close();

                    MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {

                        }
                    });

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getMyImagePath(){
        String path = null;
        if(file.exists()){
            path = file.getPath();
        }

        return path;
    }

    public File createSaveMyImage(String folderName,String extension) {
        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), folderName + "/");
        if (!folder.exists()) {
            folder.mkdirs();
        }else{
            String tstamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            file1 =new File(folder.getPath()+File.separator+"IMG_"+tstamp +"."+extension);
        }

        return file1;
    }

    public void addToGallery() {
        Intent md = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        md.setData(Uri.fromFile(file1));
        context.sendBroadcast(md);
    }
}
