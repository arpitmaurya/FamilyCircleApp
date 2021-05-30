package com.india.AccidentNotifier.Circle_Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.india.AccidentNotifier.Places_map;
import com.india.AccidentNotifier.R;

public class AddPlaces extends AppCompatActivity {

    Button contdPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);


        contdPlaces = findViewById(R.id.proceed_places);
        contdPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPlaces.this, Places_map.class));
            }
        });

    }
}