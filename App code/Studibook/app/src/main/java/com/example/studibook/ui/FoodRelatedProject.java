package com.example.studibook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.CommonUtils;
import com.example.studibook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodRelatedProject extends AppCompatActivity {


    RecyclerView project_list_view;
    ProgressDialog progressDialog;
    ProjectAdapter projectAdapter;
    TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_related_project);

        project_list_view = findViewById(R.id.project_list_view);
        noData = findViewById(R.id.noData);
        project_list_view.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<AddProjectmodel> details = new ArrayList<>();
        if (CommonUtils.isConnectedToInternet(FoodRelatedProject.this)) {
            try {

                Intent intent=getIntent();
                ArrayList<String> test = intent.getStringArrayListExtra("SEARCHEDARRAY");
                getDetails(details,test);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(FoodRelatedProject.this, "No Internet Connection....", Toast.LENGTH_SHORT).show();
        }
        int image = getIntent().getIntExtra("IMAGE",0);

        projectAdapter = new ProjectAdapter(this, details,image);
        project_list_view.setAdapter(projectAdapter);


    }

    private void getDetails(ArrayList<AddProjectmodel> details, ArrayList<String> test) {
        progressDialog = new ProgressDialog(FoodRelatedProject.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

       // String category = getIntent().getStringExtra("DomainSearch");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String key = database.getReference().push().getKey();
        DatabaseReference myRefstudent = database.getReference("Projects");

        myRefstudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(progressDialog.isShowing()){
                    progressDialog.cancel();
                }
                for (int i=0;i<test.size();i++){
                    if (snapshot.hasChild(test.get(i))){
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            for (DataSnapshot postSnapshot : snapshot1.getChildren()) {
                                if(snapshot1.getKey().equalsIgnoreCase(test.get(i))){
                                    AddProjectmodel post = postSnapshot.getValue(AddProjectmodel.class);
                                    details.add(post);
                                }

                            }
                        }
                    }
                }
                if (!(details.size() == 0)) {
                    projectAdapter.notifyDataSetChanged();
                    noData.setVisibility(View.INVISIBLE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if(progressDialog.isShowing()){
                    progressDialog.cancel();
                }
            }
        });



      /*  myRefstudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.cancel();
                Log.d("Student Course", snapshot.toString());
                details.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    AddProjectmodel post = postSnapshot.getValue(AddProjectmodel.class);

                    details.add(post);
                }
                if (!(details.size() == 0)) {
                    projectAdapter.notifyDataSetChanged();
                    noData.setVisibility(View.INVISIBLE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.cancel();
                Toast.makeText(FoodRelatedProject.this, "Data Failed", Toast.LENGTH_LONG).show();
            }
        });*/


    }
}
