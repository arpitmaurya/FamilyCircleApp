package com.india.AccidentNotifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EnterCircle extends AppCompatActivity {



    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref ;
    SharedPreferences sharedPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enter_circle);

       final  EditText cName = (EditText) findViewById(R.id.cNameE);
       final  EditText cPass = (EditText) findViewById(R.id.cPassE);
        Button  createCirclebtn = (Button) findViewById(R.id.enterCircleBtn);

        createCirclebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

              final  String circleName = cName.getText().toString();
              final  String circlePass = cPass.getText().toString();
              sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);
              final  String email = Objects.requireNonNull(sharedPreferences.getString("Email", null)).replace(".com","");


             //   Toast.makeText(EnterCircle.this , "cName : "+circleName + "  cPass : "+circlePass, Toast.LENGTH_SHORT).show();


                final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                rootRef.child(cName.getText().toString());

                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.getValue() == null) {
                            Toast.makeText(EnterCircle.this ,"Circle not found!", Toast.LENGTH_SHORT).show();

                        }else{
                         if(!circleName.isEmpty()){
                            rootRef.child(circleName).child("password").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               try {


                                   if(!circlePass.isEmpty() && circlePass.equals(Objects.requireNonNull(dataSnapshot.getValue()).toString()))
                                   {

                                       try {
                                           DBManager.setCircleName(circleName);
                                           SharedPreferences.Editor editor = sharedPreferences.edit();
                                           {
                                               ref = firebaseDatabase.getReference(circleName);
                                               ref.child(email).child("cX").setValue("TestValue123");
                                               ref.child(email).child("cY").setValue("TestValue123");
                                               ref.child(email).child("isEmergency").setValue(false);
                                               ref.child(email).child("phone").setValue("9451243978");
                                               ref.child(email).child("direction").setValue("90 testvalue");

                                                editor.putString("CIRCLE_NAME",circleName);
                                                editor.putBoolean("ISHOST",false);
                                                editor.apply();


                                               Toast.makeText(EnterCircle.this, "Entered in "+circleName+"!", Toast.LENGTH_SHORT).show();

                                               Intent i = new Intent(EnterCircle.this,MainActivity.class);
                                               startActivity(i);


                                           }


                                       }catch (Exception e ){
                                           Toast.makeText(EnterCircle.this , e.toString() + "Error Appeared", Toast.LENGTH_SHORT).show();
                                       }
                                   }

                                   else{
                                       Toast.makeText(EnterCircle.this , "Password is wrong , please check the password and try again.", Toast.LENGTH_SHORT).show();

                                   }




                               }catch (Exception e){
                                   Toast.makeText(EnterCircle.this ,e.toString(), Toast.LENGTH_LONG).show();

                               }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });





            }else{
                             Toast.makeText(EnterCircle.this ,"Circle Name cannot be empty", Toast.LENGTH_LONG).show();

                         }}
        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
}
        });
    }


}