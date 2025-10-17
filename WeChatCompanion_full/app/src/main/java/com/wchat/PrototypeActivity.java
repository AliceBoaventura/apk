package com.wchat;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PrototypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prototype);

        TextView status = findViewById(R.id.statusText);
        status.setText("Prototype screen ready. Replace with your modified feature here.");
    }
}
