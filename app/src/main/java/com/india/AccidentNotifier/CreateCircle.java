package com.india.AccidentNotifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateCircle extends AppCompatActivity {

    Button createCirclebtn;
    EditText cName;
    EditText cPass;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref ;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
 SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_circle);

        createCirclebtn = (Button) findViewById(R.id.createCircleBtn);
        cName = (EditText) findViewById(R.id.cName);
        cPass = (EditText) findViewById(R.id.cPass);

        sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);
        createCirclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String circleName = cName.getText().toString();
                String circlePass = cPass.getText().toString();
                String email = Objects.requireNonNull(sharedPreferences.getString("Email", null)).replace(".com","");
                DBManager.setCircleName(circleName);
                DBManager.setCirclePassword(circlePass);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {

                    ref = firebaseDatabase.getReference(circleName);
                    ref.child("password").setValue(circlePass);
                    ref.child(email).child("cX").setValue("TestValue123");
                    ref.child(email).child("cY").setValue("TestValue123");
                    ref.child(email).child("isEmergency").setValue(false);
                    ref.child(email).child("phone").setValue("9451243978");
                    ref.child(email).child("direction").setValue("90 testvalue");
                    ref.child(email).child("isHost").setValue(true);
                    Toast.makeText(CreateCircle.this, "Circle Added ", Toast.LENGTH_SHORT).show();

                    editor.putString("CIRCLE_NAME",circleName);
                    editor.putBoolean("ISHOST",true);
                    editor.apply();


                    Intent i = new Intent(CreateCircle.this, MainActivity.class);
                    startActivity(i);


                }catch (Exception e ){
                    Toast.makeText(CreateCircle.this , e.getCause().toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}