package com.india.AccidentNotifier.SignIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class Phone_Number_Actvity extends AppCompatActivity {

    TextView getstaTxt,privacyPolicyTxt,validitynotifier ;
   Tools tools;
   ProgressBar progressBarPA;
   WaveView waveViewPA;
   EditText phonenumberEditText;
   ImageView ellipse;
   Button sendOTPbutton ;
    Animation animFadeIn,animFadeOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__number__actvity);
        tools = new Tools(getApplicationContext());
        getstaTxt = findViewById(R.id.lets_get_st);
        progressBarPA = findViewById(R.id.profile_progressPA);
        privacyPolicyTxt = findViewById(R.id.privacypolicyText);
        waveViewPA = findViewById(R.id.wave_view2);
        phonenumberEditText = findViewById(R.id.editTextPhone);

        tools.setGradient(getstaTxt);


        ellipse = findViewById(R.id.ellipse_1);
        sendOTPbutton = findViewById(R.id.sendotpbtn);
        validitynotifier = findViewById(R.id.validityNotifier);
        animFadeIn  = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeinanim);
        animFadeOut  = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeoutanim);










    }

    @Override
    protected void onResume() {
        super.onResume();
      sendOTPbutton.setEnabled(true);


        String help = getString(R.string.by_signing_up_you_accept_our_terms_of_service_and_privacy_policy);
        help = help.replace("TERMS OF SERVICE", "<font color='#2D62C9'>TERMS OF SERVICE</font>");
        privacyPolicyTxt.setText(Html.fromHtml(help));
        help = help.replace("PRIVACY POLICY", "<font color='#2D62C9'>PRIVACY POLICY</font>");
        privacyPolicyTxt.setText(Html.fromHtml(help));

        tools.setProgress(progressBarPA,20,waveViewPA,20,10);




        phonenumberEditText.addTextChangedListener(new TextWatcher() {
            Drawable drawable;
            Boolean valid = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_neutral, null);
                ellipse.setImageDrawable(drawable);

            }

            @Override
            public void onTextChanged(CharSequence s, int start,int before, int count) {

                if(TextUtils.isEmpty(phonenumberEditText.getText())) {
                    phonenumberEditText.setError("Field cannot be Empty");
                    phonenumberEditText.requestFocus();
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_red, null);
                    ellipse.setImageDrawable(drawable);
                    valid = false;

                }

                if(TextUtils.isDigitsOnly(phonenumberEditText.getText())){
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_green,null);
                    ellipse.setImageDrawable(drawable);
                    valid = true;
                    validitynotifier.setVisibility(View.INVISIBLE);
                }else{
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_red, null);
                    ellipse.setImageDrawable(drawable);
                    valid = false;
                    validitynotifier.setVisibility(View.VISIBLE);
                    //validitynotifier.setVisibility(View.VISIBLE);

                }

                if (valid && s.length() ==10){
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_green,null);
                    ellipse.setImageDrawable(drawable);
                    valid = true;
                    validitynotifier.setVisibility(View.INVISIBLE);
                    validitynotifier.clearAnimation();



                }else{
                    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ellipse_red, null);
                    ellipse.setImageDrawable(drawable);
                    valid = false;
                    validitynotifier.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        sendOTPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!phonenumberEditText.getText().toString().equals("") &&validitynotifier.getVisibility()!=View.VISIBLE){

                    sendOTPbutton.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Query Ok",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Phone_Number_Actvity.this, OtpValidationActivity.class);
                    startActivity(i);
                    onPause();
                }else{
                    phonenumberEditText.setError("Please make sure the number is Valid");
                    phonenumberEditText.requestFocus();
                }
            }
        });




    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}