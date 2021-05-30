package com.india.AccidentNotifier.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.india.AccidentNotifier.Circle_Init.Circle_Initiation;
import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class Create_Password_Page extends AppCompatActivity {
    Tools tools;
    Button proceed;
    TextView enterPassword_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_password_page);

         tools = new Tools(this);

        enterPassword_textView = findViewById(R.id.password_text);

        Tools.setGradient(enterPassword_textView);
        Tools.setAnimation(1);

        tools.setProgress((ProgressBar) findViewById(R.id.profile_progressCACT),40,(WaveView)findViewById(R.id.wave_viewCPASS) ,20,35);

        proceed = (Button) findViewById(R.id.proceed_cact);





    }

    @Override
    protected void onResume() {
        super.onResume();

        proceed.setEnabled(true);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed.setEnabled(false);
                startActivity(new Intent(Create_Password_Page.this, Circle_Initiation.class));
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();


    }
}