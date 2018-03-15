package apps.kelvin.makau.scannerapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import apps.kelvin.makau.scannerapp.R;
import apps.kelvin.makau.scannerapp.Sqlite.DbOperations;
import apps.kelvin.makau.scannerapp.Utills.UtilListeners.OnclickRecyclerListener;
import apps.kelvin.makau.scannerapp.models.Kipande;

/**
 * Created by Eric on 11/29/2017.
 */

public class KipandeListAdapter extends RecyclerView.Adapter<KipandeListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Kipande> modelList;
    private DbOperations dbOperations;
    private OnclickRecyclerListener onclickRecyclerListener;
    private int type=0;
    private RequestOptions options;


    public KipandeListAdapter(Context context,
                              ArrayList<Kipande> modelList,
                              OnclickRecyclerListener onclickRecyclerListener) {
        this.context = context;
        this.type=type;
        this.modelList = modelList;
        dbOperations = new DbOperations(context);
        this.onclickRecyclerListener = onclickRecyclerListener;
        this.options = (new RequestOptions())
                .placeholder(R.drawable.icon)
                .error(R.drawable.icon)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kipande_item, parent, false);
        return new MyViewHolder(itemView, onclickRecyclerListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       // AppUtils appUtils = new AppUtils(context);
        dbOperations = new DbOperations(context);
        Kipande kipande = modelList.get(position);
        holder.mName.setText(kipande.getFull_names());

        holder.id.setText( "ID :"+kipande.getId_no());
        holder.office.setText( "Offce : "+kipande.getOffice());
        holder.date.setText("Date :" +kipande.getTimeStamp());


            Glide.with(context)
                    .load(kipande.getImg_path())
                    .apply(options)
                    .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void updateList(ArrayList<Kipande> list) {
        modelList = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView;
        private TextView mName,id,date,office;
       private CardView layout;
        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public MyViewHolder(View itemView, OnclickRecyclerListener onclickRecyclerListener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(onclickRecyclerListener);

            id = itemView.findViewById(R.id.txt_id);
            mName = itemView.findViewById(R.id.txt_name);
            date = itemView.findViewById(R.id.txt_date);
            office = itemView.findViewById(R.id.txt_office);

            imageView = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.card);

            layout.setOnClickListener(this);
            layout.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // modelList.get(0);
            listenerWeakReference.get().onClickListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listenerWeakReference.get().onLongClickListener(getAdapterPosition());
            return true;
        }
    }
}
