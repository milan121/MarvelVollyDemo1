package com.milanapp.marvelvollydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    TextView marvel_charecter_name,marvel_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String name = getIntent().getExtras().getString("charecter_name");
        String bio = getIntent().getExtras().getString("marvel_desc");

        marvel_charecter_name = findViewById(R.id.charecter_name);
        marvel_desc = findViewById(R.id.marvel_desc);



        marvel_charecter_name.setText(name);
        marvel_desc.setText(bio);
    }
}
