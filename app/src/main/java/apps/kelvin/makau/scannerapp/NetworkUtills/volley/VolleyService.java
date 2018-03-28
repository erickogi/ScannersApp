package apps.kelvin.makau.scannerapp.NetworkUtills.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

//import com.android.volley.toolbox.StringRequest;

/**
 * Created by kimani kogi on 5/27/2017.
 */

public class VolleyService {

    IResult mResultCallback = null;
    Context mContext;

    public VolleyService(IResult resultCallback, Context context) {
        mResultCallback = resultCallback;
        mContext = context;
    }

    public void getDataVolley(final String requestType, String url) {


        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                    response -> {

                        if (mResultCallback != null)
                            mResultCallback.notifySuccess(requestType, response);
                    }, error -> {
                if (mResultCallback != null)
                    mResultCallback.notifyError(requestType, error);
            });

            queue.add(stringRequest);

        } catch (Exception e) {

        }
    }

    public void getDataVolley2(final String requestType, String url) {


        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                    response -> {


                        if (mResultCallback != null)
                            mResultCallback.notifySuccess(requestType, response);
                    }, error -> {
                if (mResultCallback != null)
                    mResultCallback.notifyError(requestType, error);
            });

            queue.add(stringRequest);

        } catch (Exception e) {

        }
    }
}


