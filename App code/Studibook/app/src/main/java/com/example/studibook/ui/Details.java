package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.studibook.R;

public class Details extends AppCompatActivity {

    TextView member2,member1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        member2=findViewById(R.id.member2);
        member1=findViewById(R.id.member1);

        member1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details.this,Profile.class);
                startActivity(intent);
            }
        });

        member2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details.this,Profile.class);
                startActivity(intent);

            }
        });
    }
}