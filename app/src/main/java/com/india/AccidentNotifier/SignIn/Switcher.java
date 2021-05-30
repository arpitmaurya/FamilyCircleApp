package com.india.AccidentNotifier.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.india.AccidentNotifier.CreateCircle;
import com.india.AccidentNotifier.EnterCircle;
import com.india.AccidentNotifier.R;

public class Switcher extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switcher);

        final Button enterCircle , createCircle;

        enterCircle = findViewById(R.id.enterCircle);
        createCircle = findViewById(R.id.createCicle);


        enterCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Switcher.this, EnterCircle.class);
                startActivity(i);
            }
        });


       createCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Switcher.this, CreateCircle.class);
                startActivity(i);
            }
        });



    }
}