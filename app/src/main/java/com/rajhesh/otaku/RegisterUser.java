package com.rajhesh.otaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    TextView banner, bannerdes;
    EditText regusername, regmail, regpassword;
    Button regbutton;
    Spinner spinner;
    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();
        banner = (TextView) findViewById(R.id.banner);
        bannerdes = (TextView) findViewById(R.id.banner_des);
        regusername = (EditText) findViewById(R.id.reg_username);
        regmail= (EditText) findViewById(R.id.reg_email);
        regpassword=(EditText) findViewById(R.id.reg_password);
        regbutton = (Button) findViewById(R.id.reg_button);
        regbutton.setOnClickListener(this);
        listItems=getResources().getStringArray(R.array.category);
        spinner=(Spinner)findViewById(R.id.reg_category);

        ArrayAdapter adapter =  new ArrayAdapter(RegisterUser.this, android.R.layout.simple_spinner_item,listItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.reg_button:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String username= regusername.getText().toString().trim();
        String email= regmail.getText().toString().trim();
        String password= regpassword.getText().toString().trim();
//        String category = spinner.getOnItemSelectedListener().toString().trim();

        if (username.isEmpty()){
            regusername.setError("Username is Required!");
            regusername.requestFocus();
            return;
        }
        if (email.isEmpty()){
            regmail.setError("Email is Required!");
            regmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regmail.setError("Provide a Valid Mail");
            regmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            regpassword.setError("Password is Required!");
            regpassword.requestFocus();
            return;
        }
        if (password.length()<8){
            regpassword.setError("Minimum Password Length Should be 6 Characters!");
            regpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()){
                     User user = new User(username, email, password);

                     FirebaseDatabase.getInstance().getReference("Users")
                             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                             .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful()){
                                 Toast.makeText(RegisterUser.this, "User have been Registered Successfully!", Toast.LENGTH_LONG).show();
                             }
                             else {
                                 Toast.makeText(RegisterUser.this, "Failed To Register!", Toast.LENGTH_LONG).show();
                             }
                         }
                     });
                 }
                 else {
                     Toast.makeText(RegisterUser.this, "Failed To Register!", Toast.LENGTH_LONG).show();
                 }
            }
        });

    }
}