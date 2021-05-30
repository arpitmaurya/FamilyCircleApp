package com.india.AccidentNotifier.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class First_Page extends AppCompatActivity {

    TextView phrase;
    TextView alreadyhav;
    ProgressBar progressBar;
    Tools tools;
    Button enter;
    WaveView waveView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

         phrase = (TextView) findViewById(R.id.phrase);
         alreadyhav= (TextView) findViewById(R.id.already_hav);
      progressBar = (ProgressBar) findViewById(R.id.profile_progress);
        tools = new Tools(getApplicationContext());
        enter =(Button) findViewById(R.id.phoneeditbo);
       waveView = (WaveView) findViewById(R.id.wave_view);



    }

    @Override
    protected void onResume() {
        super.onResume();

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animlefttoright);
        Animation animFadeIn2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);

        enter.setEnabled(true);

        enter.startAnimation(animFadeIn2);
        phrase.startAnimation(animFadeIn);
        alreadyhav.startAnimation(animFadeIn2);
        Shader shader = new LinearGradient(0f, 20f, 0f, phrase.getTextSize(),Color.parseColor("#2D62C9"),  Color.parseColor("#3E7DF8"), Shader.TileMode.CLAMP);
        phrase.getPaint().setShader(shader);

        String help = getString(R.string.already_hav);
        help = help.replace("SIGN IN", "<font color='#2D62C9'>SIGN IN</font>");
        alreadyhav.setText(Html.fromHtml(help));

        tools.setProgress(progressBar,10,waveView,20,0);


enter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        enter.setEnabled(false);
        Intent i = new Intent(First_Page.this, Phone_Number_Actvity.class);
        startActivity(i);

        onPause();

    }
});

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}