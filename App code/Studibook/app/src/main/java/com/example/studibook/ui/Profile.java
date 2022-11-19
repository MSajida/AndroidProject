package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.studibook.R;

public class Profile extends AppCompatActivity {

    TextView profileemail,profileSid,profileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileemail=findViewById(R.id.profileName);
        profileSid=findViewById(R.id.profileSid);
        profileName=findViewById(R.id.profileName);

        String name=getIntent().getStringExtra("MEMBERNAME");
        String mail=getIntent().getStringExtra("MEMBERMAIL");
        String sidd=getIntent().getStringExtra("MEMBERSID");

        profileemail.setText(mail);
        profileSid.setText(sidd);
        profileName.setText(name);
    }
}