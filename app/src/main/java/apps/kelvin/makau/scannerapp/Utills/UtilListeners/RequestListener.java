package apps.kelvin.makau.scannerapp.Utills.UtilListeners;

//import com.android.volley.VolleyError;

import com.android.volley.error.VolleyError;

/**
 * Created by Eric on 12/15/2017.
 */

public interface RequestListener {
    void onError(VolleyError error);

    void onError(String error);

    void onSuccess(String response);
}
