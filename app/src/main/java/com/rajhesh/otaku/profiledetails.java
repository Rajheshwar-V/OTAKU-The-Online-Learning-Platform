package com.rajhesh.otaku;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class profiledetails extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private  Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetails);

        logout =(Button) findViewById(R.id.viewbutton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView username = (TextView) findViewById(R.id.namedispfield);
        final TextView mailid = (TextView) findViewById(R.id.maildispfield);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(username != null){
                    String usname = userProfile.username;
                    String email = userProfile.email;

                    username.setText(usname);
                    mailid.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(profiledetails.this,"Error Occured",Toast.LENGTH_LONG).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profiledetails.this, MainActivity.class));
                Toast.makeText(profiledetails.this,"Logout Successful", Toast.LENGTH_LONG).show();
            }
        });

    }



    public void message(View view) {
        if(view.getId()==R.id.imageButton1){
            startActivity(new Intent(this, messagelist.class));
        }
        else if(view.getId()==R.id.imageButton2){
            startActivity(new Intent(this, search.class));
            Toast.makeText(this, "Module Under Developement", Toast.LENGTH_SHORT).show();

        }
        else if(view.getId()==R.id.imageButton3){
            startActivity(new Intent(this, notification.class));
        }
        else if(view.getId()==R.id.imageButton4){
            startActivity(new Intent(this, profiledetails.class));
        }
        }
    }
