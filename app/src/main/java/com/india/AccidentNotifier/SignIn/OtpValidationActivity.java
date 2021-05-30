package com.india.AccidentNotifier.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.india.AccidentNotifier.R;
import com.india.AccidentNotifier.Tools;
import com.john.waveview.WaveView;

public class OtpValidationActivity extends AppCompatActivity {

    TextView otpTextView ;
    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four;
    Button verify_otp;

    Tools tools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_validation);

        otpTextView = findViewById(R.id.verifyOtpText);
        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        verify_otp = (Button) findViewById(R.id.verifyOtpBTN);
        tools = new Tools(getApplicationContext());


        Tools.setGradient(otpTextView);
        Tools.setAnimation(1);

        EditText[] edit = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four};

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));

       final String otp = otp_textbox_one.getText().toString()+otp_textbox_two.getText().toString()+otp_textbox_three.getText().toString()+otp_textbox_four.getText().toString();

       // verify_otp.setOnClickListener(new View.OnClickListener() {
      //      public void onClick(View view) {
        //        Toast.makeText(getApplicationContext(), "Otp :"+otp, Toast.LENGTH_SHORT).show();

      //      }
     //   });


        tools.setProgress((ProgressBar) findViewById(R.id.profile_progressOTP),25,(WaveView)findViewById(R.id.wave_viewOTP) ,20,20);


        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(OtpValidationActivity.this, NamePage.class));

            }
        });

    }

}