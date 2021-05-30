package com.india.AccidentNotifier.Circle_Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.india.AccidentNotifier.R;


public class PhotoPage extends AppCompatActivity {

    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_page);

        Continue = findViewById(R.id.contd);

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(PhotoPage.this, PermissionPage.class));
            }
        });

    }
}