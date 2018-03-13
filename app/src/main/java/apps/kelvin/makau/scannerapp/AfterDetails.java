package apps.kelvin.makau.scannerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AfterDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_details);
        TextView tv = findViewById(R.id.det);
        tv.setText(getIntent().getStringExtra("ser"));
    }
}
