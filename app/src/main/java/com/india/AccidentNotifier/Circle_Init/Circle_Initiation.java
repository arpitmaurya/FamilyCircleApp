package com.india.AccidentNotifier.Circle_Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class Circle_Initiation extends AppCompatActivity {

    Tools tools;
    Button makeCircle, JoinCircle;
    TextView joinenter_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle__initiation);


        tools = new Tools(getApplicationContext());
        joinenter_textView = findViewById(R.id.circleIn_iact);
        makeCircle = findViewById(R.id.CREATE_CIRCLE);
        JoinCircle = findViewById(R.id.JOIN_CIRCLE);




        Tools.setGradient(joinenter_textView);
        Tools.setAnimation(1);

        tools.setProgress((ProgressBar) findViewById(R.id.profile_iact),37,(WaveView)findViewById(R.id.wave_viewIACT) ,15,10);

      // ((ProgressBar) findViewById(R.id.profile_iact)).setProgress(65);

      /*  proceed = (Button) findViewById(R.id.proceed_eact);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Circle_Initiation.this,Create_Password_Page.class));
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeCircle.setEnabled(true);
        JoinCircle.setEnabled(true);


        makeCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCircle.setEnabled(false);
                startActivity(new Intent(Circle_Initiation.this, MakeCircle.class));
            }
        });

        JoinCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinCircle.setEnabled(false);
                startActivity(new Intent(Circle_Initiation.this, JoinCircle.class));
            }
        });



    }
}