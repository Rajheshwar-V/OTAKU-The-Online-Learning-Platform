package com.rajhesh.otaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private FirebaseAuth mAuth;
    private Button loginbtn;
    private EditText loginmailinput, loginpassinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.registernow);
        register.setOnClickListener(this);

        loginbtn = (Button) findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(this);

        loginmailinput = (EditText) findViewById(R.id.loginmailinput);
        loginpassinput = (EditText) findViewById(R.id.loginpassinput);

        mAuth= FirebaseAuth.getInstance();
    }

    @Override
        public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registernow:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.loginbutton:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = loginmailinput.getText().toString().trim();
        String password = loginpassinput.getText().toString().trim();

        if (email.isEmpty()){
            loginmailinput.setError("Email is Required!");
            loginmailinput.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginmailinput.setError("Enter a Valid Email!");
            loginmailinput.requestFocus();
            return;
        }

        if (password.isEmpty()){
            loginpassinput.setError("Password is Required!");
            loginpassinput.requestFocus();
            return;
        }

        if (password.length()<8){
            loginpassinput.setError("Password is Short Than Expected!");
            loginpassinput.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, dashboard.class));
                    Toast.makeText(MainActivity.this, "Logged In Successfully!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_LONG).show();
                }
                }
            }
        );

    }
}