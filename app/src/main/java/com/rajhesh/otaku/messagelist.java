package com.rajhesh.otaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class messagelist extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagelist);
        intent = new Intent(this, cyberchat.class);

    }

    public void cyberchat(View view) {
        if(view.getId()==R.id.firstmessage){
            startActivity(intent);
        }
//        else if(view.getId()==R.id.secondmessage){
//            startActivity(new Intent(this, networkchat.class));
//        }
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