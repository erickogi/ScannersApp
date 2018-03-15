package apps.kelvin.makau.scannerapp.Utills.UtilListeners;

import android.widget.ImageView;

/**
 * Created by Eric on 12/18/2017.
 */

public interface AppOnclickRecyclerListener {
    void onClickListener(int position);

    void onLongClickListener(int position);

    void onClickListener(int adapterPosition, ImageView thumbnail, ImageView select);

    // void onDeleteListener(int adapterPosition);
}
