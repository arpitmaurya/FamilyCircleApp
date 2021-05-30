package com.india.AccidentNotifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.india.AccidentNotifier.Circle_Init.PhotoPage;

import org.w3c.dom.Text;

public class InviteCodePage extends AppCompatActivity {

    Tools tools;
    TextView inviteCodeTextView;
    Button shareBtn, DoneSharing;
    String InviteCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_code_page);

        tools = new Tools(this);
        inviteCodeTextView = findViewById(R.id.InviteCodeTextView);
        InviteCode = Tools.inviteCodeGenerator(6);
        shareBtn = findViewById(R.id.sharecode);

    }

    @Override
    protected void onResume() {
        super.onResume();

        inviteCodeTextView.setText(InviteCode);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InviteCodePage.this, PhotoPage.class));
            }
        });
    }
}