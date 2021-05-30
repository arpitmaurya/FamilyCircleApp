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

public class NamePage extends AppCompatActivity {

    Tools tools;
    Button proceed;
    TextView entername_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_page);

        entername_textView = findViewById(R.id.enter_your_name_text);

        tools = new Tools(getApplicationContext());

        Tools.setGradient(entername_textView);
        Tools.setAnimation(1);

        tools.setProgress((ProgressBar) findViewById(R.id.profile_progressNACT),30,(WaveView)findViewById(R.id.wave_view_nact) ,20,25);

        proceed = (Button) findViewById(R.id.proceed_nact);

    }

    @Override
    protected void onResume() {
        super.onResume();

        proceed.setEnabled(true);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed.setEnabled(false);
                startActivity(new Intent(NamePage.this, Email_Page.class));
            }
        });


    }
}