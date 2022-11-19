package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.AddmemberModel;
import com.example.studibook.R;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    TextView detailsTitle,enter_description,githubtext,details_year;
    RecyclerView detailsList_view;
    TeamMemberadapter teamMemberadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsList_view=findViewById(R.id.detailsList_view);
        enter_description=findViewById(R.id.enter_description);
        githubtext=findViewById(R.id.githubtext);
        detailsTitle=findViewById(R.id.detailsTitle);
        details_year=findViewById(R.id.details_year);
        detailsList_view.setLayoutManager(new LinearLayoutManager(this));
        AddProjectmodel addProjectmodel= (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");
        detailsTitle.setText(addProjectmodel.getTitle());
        enter_description.setText(addProjectmodel.getDescription());
        githubtext.setText(addProjectmodel.getBatch());
        details_year.setText(addProjectmodel.getYear());

        ArrayList<AddmemberModel> list=new ArrayList<>();
        for (AddmemberModel postSnapshot : addProjectmodel.getMemberList()) {
            list.add(postSnapshot);

        }

        teamMemberadapter = new TeamMemberadapter( this,list);
        detailsList_view.setAdapter(teamMemberadapter);


    }

}