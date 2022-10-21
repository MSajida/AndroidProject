package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.studibook.R;

public class FoodRelatedProject extends AppCompatActivity {

    TextView project1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_related_project);

        project1=findViewById(R.id.Project1);

        project1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FoodRelatedProject.this,Details.class);
                startActivity(intent);
            }
        });

    }
}
