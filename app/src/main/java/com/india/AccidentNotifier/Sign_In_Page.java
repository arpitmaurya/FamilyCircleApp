package com.india.AccidentNotifier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.india.AccidentNotifier.SignIn.Switcher;


public class Sign_In_Page extends AppCompatActivity {


    EditText email,password;
    Button signinButton;
    TextView LinkLogin;
    FirebaseAuth m;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);
  m = FirebaseAuth.getInstance();
  email = (EditText)findViewById(R.id.email);
  password= (EditText)findViewById(R.id.password);
signinButton = (Button)findViewById(R.id.SignInButton);
        LinkLogin = findViewById(R.id.LinkLogin);
        sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);



        signinButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
   final String emailID = email.getText().toString();
   String pass = password.getText().toString();

   if (emailID.isEmpty()){
       email.setError("Please Enter the Email ID");
   }
   else if(pass.isEmpty()){
       password.setError("Please Enter your Respective Password");
   }
   else if(emailID.isEmpty() && pass.isEmpty()){

       email.setError("Please Enter the Email ID");
       password.setError("Please Enter your Respective Password");
   }

   else if (!emailID.isEmpty() && !pass.isEmpty()){

       m.createUserWithEmailAndPassword(emailID,pass).addOnCompleteListener(Sign_In_Page.this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {

               SharedPreferences.Editor editor = sharedPreferences.edit();
               if(!task.isSuccessful()){

                   //Toast.makeText(Sign_In_Page.this,"Sign Up UnSuccessfull!.Please Try Again",Toast.LENGTH_SHORT).show();
                   Toast.makeText(Sign_In_Page.this,task.getException()+"",Toast.LENGTH_SHORT).show();

               }
               else
               {
                   Toast.makeText(Sign_In_Page.this,"Sign Up Successfull!",Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(Sign_In_Page.this, Switcher.class));
                   editor.putString("alreadyloggedin","true");
                   editor.putString("Email",emailID);
                   editor.apply();
               }
           }
       });
   }
   else{
       Toast.makeText(Sign_In_Page.this,"An Error Occured!",Toast.LENGTH_SHORT).show();
   }

    }


  });
                LinkLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sign_In_Page.this,Login_Page.class)); }
                });

}

}

