package com.india.AccidentNotifier.SignIn;

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

public class Email_Page extends AppCompatActivity {


    Tools tools;
    Button proceed;
    TextView enterEmail_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_page);

        tools = new Tools(getApplicationContext());

        enterEmail_textView = findViewById(R.id.email_id_text);
        Tools.setGradient(enterEmail_textView);
        Tools.setAnimation(1);

        tools.setProgress((ProgressBar) findViewById(R.id.profile_progressEACT),32,(WaveView)findViewById(R.id.wave_view_nact) ,20,25);
        proceed = (Button) findViewById(R.id.proceed_eact);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Email_Page.this, Create_Password_Page.class));
            }
        });



    }
}