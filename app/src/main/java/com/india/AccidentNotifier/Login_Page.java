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
import com.google.firebase.auth.FirebaseUser;
import com.india.AccidentNotifier.SignIn.Switcher;


public class Login_Page extends AppCompatActivity {


    EditText email,password;
    Button signinButton;
    TextView LinkSignIn;
    FirebaseAuth m1;
FirebaseAuth.AuthStateListener mlisr;
SharedPreferences sharedPreferences = null;
DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        m1 = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.email2);
        password= (EditText)findViewById(R.id.password2);
        signinButton = (Button)findViewById(R.id.LogInButton2);
        LinkSignIn = findViewById(R.id.LinkSignin2);

        checkIfAlreadyLoggedIn();
         mlisr = new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        FirebaseUser mFirebaseUser = m1.getCurrentUser();
        sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();




        if(mFirebaseUser != null){

            Toast.makeText(Login_Page.this,"Logged In",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login_Page.this, MainActivity.class));
            DBManager.setEmailId(email.getText().toString());
//
          //  DBManager.setIsAlreadyLoggedIn(true);

            editor.putString("alreadyloggedin","true");
            editor.apply();
//
        }
        else{
            Toast.makeText(Login_Page.this,"You are not Registered,Sign In",Toast.LENGTH_SHORT).show();
       email.setError("You are not Registered,Sign In");
        }


    }
    };

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String emailID2 = email.getText().toString();
                String pass2 = password.getText().toString();

                if (emailID2.isEmpty()){
                    email.setError("Please Enter the Email ID");
                }
                else if(pass2.isEmpty()){
                    password.setError("Please Enter your Respective Password");
                }
                else if (!emailID2.isEmpty() && !pass2.isEmpty()){

                    m1.signInWithEmailAndPassword(emailID2,pass2).addOnCompleteListener(Login_Page.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){

                            Toast.makeText(Login_Page.this,"An error occured: "+task.getException().toString(),Toast.LENGTH_SHORT).show();
                            email.setError("You are not Registered,Sign In");
                        }
                        else
                        {
                            SharedPreferences.Editor editor2 = sharedPreferences.edit();

                            startActivity(new Intent(Login_Page.this, Switcher.class));
                           DBManager.setEmailId(email.getText().toString());
                            editor2.putString("Email",email.getText().toString());
                            editor2.putString("alreadyloggedin","true");
                            editor2.apply();

                        }
                        }
                    });

                }
                else{

                    Toast.makeText(Login_Page.this,"An Error Occured!",Toast.LENGTH_SHORT).show();

                }



            }


        });

        LinkSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Page.this,Sign_In_Page.class));
            }
        });



    }



    @Override
    protected void onResume() {
        super.onResume();
        checkIfAlreadyLoggedIn();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    void checkIfAlreadyLoggedIn(){
        sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
       // String ispresent = "";
        if(sharedPreferences.getString("alreadyloggedin", "").equals("true"))
        {
            Toast.makeText(this ,"Auto Login Successful", Toast.LENGTH_SHORT).show();
         Intent i = new Intent(Login_Page.this,Switcher.class);
        startActivity(i);

        }else{
            editor.putString("alreadyloggedin","false");
            editor.apply();
            Toast.makeText(this ,"Log in", Toast.LENGTH_SHORT).show();

        }
    }

}

