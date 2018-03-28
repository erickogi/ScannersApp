package apps.kelvin.makau.scannerapp.NetworkUtills;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.request.StringRequest;

import java.util.HashMap;
import java.util.Map;

import apps.kelvin.makau.scannerapp.NetworkUtills.volley.MyApplication;
import apps.kelvin.makau.scannerapp.Utills.UtilListeners.RequestListener;
import apps.kelvin.makau.scannerapp.models.Kipande;

//import com.android.volley.toolbox.StringRequest;

/**
 * Created by Eric on 12/15/2017.
 */

public class DumbVolleyRequest {
    static String responseObj = null;

    public static String getPostData(String url, HashMap<String, String> params, RequestListener listener) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, response -> {


            // responseObj = response;
            Log.d("dvrR", response);
            listener.onSuccess(response);


        }, error -> {
            Log.e("dvrVE", "Error: " + error.getMessage());
            listener.onError(error);

        }) {

            @Override
            protected Map<String, String> getParams() {

                Log.e("posting params", "Posting params: " + params.toString());

                return params;
            }

        };


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);


        return responseObj;

    }

    public static String getGetData(String url, RequestListener requestListener) {

        //responseObj = response;
// Log.d("dvrR", response);
        // Log.e("dvrVE", "Error: " + error.getMessage());
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, requestListener::onSuccess, requestListener::onError) {


        };


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);


        return responseObj;

    }


    public static void kipande(final String imagePath, Kipande kipande, String url, RequestListener listener) {


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
                response -> {
                    Log.d("Response", response);

                    Log.d("dvrR", response);
                    listener.onSuccess(response);

                },
                error -> {
                    Log.e("dvrVE", "Error: " + error.getMessage());
                    listener.onError(error);
                }) {


        };

        smr.addMultipartParam("national_id", "text/plain", kipande.getId_no());
        smr.addMultipartParam("name", "text/plain", kipande.getFull_names());
        smr.addMultipartParam("time_in", "text/plain", kipande.getTimeStamp());
        smr.addMultipartParam("visit", "text/plain", kipande.getVisit_type());
        smr.addMultipartParam("office", "text/plain", kipande.getOffice());
        smr.addMultipartParam("car", "text/plain", kipande.getVehicle_plate());
        //smr.addMultipartParam("img", "text/plain", kipande.getImg_path());
        smr.addMultipartParam("country", "text/plain", kipande.getCountry());

        smr.addMultipartParam("comment", "text/plain", kipande.getComment());
        smr.addMultipartParam("time_out", "text/plain", kipande.getCheck_out_time());

        smr.addMultipartParam("sex", "text/plain", kipande.getSex());
        smr.addMultipartParam("dob", "text/plain", kipande.getDob());
        smr.addMultipartParam("country_code", "text/plain", kipande.getCountry_code());
        smr.addMultipartParam("mrz", "text/plain", kipande.getMRZ_lines());
        smr.addMultipartParam("serial_no", "text/plain", kipande.getSerial_no());
        smr.addMultipartParam("date_of_issue", "text/plain", kipande.getDate_of_issue());

        smr.addMultipartParam("key_id", "text/plain", ""+kipande.getKEY_ID());



        smr.addFile("img", kipande.getImg_path());



        // smr.addStringParam()
        MyApplication.getInstance().addToRequestQueue(smr);

    }

}
