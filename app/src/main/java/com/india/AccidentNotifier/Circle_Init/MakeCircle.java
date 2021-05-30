package com.india.AccidentNotifier.Circle_Init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.india.AccidentNotifier.InviteCodePage;
import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.SignIn.Create_Password_Page;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class MakeCircle extends AppCompatActivity {


    Tools tools;
    Button proceed;
    TextView makeCircleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_circle);

        tools = new Tools(getApplicationContext());

        makeCircleText = findViewById(R.id.makeCircle_text);
        Tools.setGradient(makeCircleText);
        Tools.setAnimation(1);

        tools.setProgress((ProgressBar) findViewById(R.id.profile_progressMACT),40,(WaveView)findViewById(R.id.wave_view_mact) ,20,25);
        proceed = (Button) findViewById(R.id.proceed_mact);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MakeCircle.this, Create_Password_Page.class));
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
          proceed.setEnabled(true);
          proceed.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  proceed.setEnabled(false);
                  startActivity(new Intent(MakeCircle.this, InviteCodePage.class));

              }
          });


    }
}