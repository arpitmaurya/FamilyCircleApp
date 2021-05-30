package com.india.AccidentNotifier.Circle_Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.india.AccidentNotifier.R;

public class PermissionPage extends AppCompatActivity {

    Button contdbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_page);

        contdbtn = findViewById(R.id.contdpermiact);
        contdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PermissionPage.this, AddPlaces.class));
            }
        });

    }
}