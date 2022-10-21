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
import com.example.studibook.AddmemberModel;
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
    ArrayList<AddmemberModel> mem1 = new ArrayList<>();
    ArrayList<AddmemberModel> mem2 = new ArrayList<>();
    ArrayList<AddmemberModel> mem3 = new ArrayList<>();
    ArrayList<AddmemberModel> mem4 = new ArrayList<>();
    ArrayList<AddmemberModel> mem5 = new ArrayList<>();
    ArrayList<AddmemberModel> mem6 = new ArrayList<>();
    ArrayList<AddmemberModel> mem7 = new ArrayList<>();
    ArrayList<AddmemberModel> mem8 = new ArrayList<>();ArrayList<AddmemberModel> mem10 = new ArrayList<>();
    ArrayList<AddmemberModel> mem9 = new ArrayList<>();
    ArrayList<AddmemberModel> mem11 = new ArrayList<>();
    ArrayList<AddmemberModel> mem12 = new ArrayList<>();
    ArrayList<AddmemberModel> mem13 = new ArrayList<>();


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
                getDetails(details);

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

    private void getDetails(ArrayList<AddProjectmodel> details) {
        progressDialog = new ProgressDialog(FoodRelatedProject.this);
        progressDialog.setMessage("Loading....");
        // progressDialog.show();


        String category = getIntent().getStringExtra("DomainSearch");
        details.clear();
        AddmemberModel ad1= new AddmemberModel("karthik", "s12321","s12321@nwmissouri.edu");
        AddmemberModel ad2= new AddmemberModel("Abhi", "s12322","s12322@nwmissouri.edu");
        AddmemberModel ad3= new AddmemberModel("Jay", "s12323","s12323@nwmissouri.edu");
        AddmemberModel ad4= new AddmemberModel("Mani", "s12324","s12324@nwmissouri.edu");
        AddmemberModel ad5= new AddmemberModel("Dinesh", "s12325","s12325@nwmissouri.edu");
        AddmemberModel ad6= new AddmemberModel("Rahul", "s12326","s12326@nwmissouri.edu");
        AddmemberModel ad7= new AddmemberModel("Arjun", "s12327","s12327@nwmissouri.edu");
        AddmemberModel ad8= new AddmemberModel("Sania", "s12328","s12328@nwmissouri.edu");
        AddmemberModel ad9= new AddmemberModel("Sana", "s12329","s12329@nwmissouri.edu");
        AddmemberModel ad10= new AddmemberModel("Ajay", "s12330","s12330@nwmissouri.edu");
        AddmemberModel ad11= new AddmemberModel("Raj", "s12331","s12331@nwmissouri.edu");
        AddmemberModel ad12= new AddmemberModel("Tab", "s12332","s12332@nwmissouri.edu");
        AddmemberModel ad13= new AddmemberModel("Santosh", "s12333","s12333@nwmissouri.edu");
        AddmemberModel ad14= new AddmemberModel("Vasanth", "s12334","s12334@nwmissouri.edu");
        AddmemberModel ad15= new AddmemberModel("Alia", "s12335","s12335@nwmissouri.edu");
        AddmemberModel ad16= new AddmemberModel("Nora", "s12336","s12336@nwmissouri.edu");
        AddmemberModel ad17= new AddmemberModel("Ramya", "s12337","s12337@nwmissouri.edu");

        mem1.add(ad1); mem1.add(ad2);
        mem2.add(ad3); mem2.add(ad4);
        mem3.add(ad5); mem3.add(ad5);
        mem4.add(ad6); mem4.add(ad7);
        mem5.add(ad8); mem5.add(ad9);
        mem6.add(ad10); mem7.add(ad11);
        mem8.add(ad12); mem9.add(ad13);
        mem10.add(ad14); mem11.add(ad15);
        mem12.add(ad16); mem13.add(ad17);

        AddProjectmodel food_ap1= new AddProjectmodel("OrderFood", "Get your food", "Fall", "2019", "Food", mem1);
        AddProjectmodel food_ap2= new AddProjectmodel("Grab your Food", "Get tasty Food soon!!", "Spring", "2020", "Food", mem2);
        AddProjectmodel food_ap3= new AddProjectmodel("Cafe Bakery", "Favourite desserts and cakes available", "Summer", "2020", "Food", mem3);
        AddProjectmodel travel_ap1= new AddProjectmodel("Make your trip", "Have a safe trip", "Fall", "2022", "Travel", mem4);
        AddProjectmodel travel_ap2= new AddProjectmodel("find tour", "Travel with families in a luxury manner", "Spring", "2022", "Travel", mem5);
        AddProjectmodel travel_ap3= new AddProjectmodel("Make my journey", "Book your tickets", "Summer", "2021", "Travel", mem6);
        AddProjectmodel enviro_ap1= new AddProjectmodel("Weather Report", "Know the climate", "Summer", "2018", "Environment", mem7);
        AddProjectmodel enviro_ap2= new AddProjectmodel("Water Tracker", "Track your daily water requirements", "Spring", "2019", "Environment", mem8);
        AddProjectmodel medical_ap1= new AddProjectmodel("BMI Check", "Check your BMI", "Fall", "2022", "Medical", mem9);
        AddProjectmodel medical_ap2= new AddProjectmodel("Foot Steps", "Count you foot Steps on a daily basis", "Spring", "2019", "Medical", mem10);
        AddProjectmodel misc_ap1= new AddProjectmodel("Speaking Calculator", "Track your words", "Fall", "2019", "Miscellaneous", mem11);
        AddProjectmodel misc_ap2= new AddProjectmodel("Sleep Time", "Find your sleeping Hours on a daily basis", "Spring", "2018", "Miscellaneous", mem12);
        AddProjectmodel misc_ap3= new AddProjectmodel("Travel Time", "Check the duration time to destination", "Summer", "2019", "Miscellaneous", mem13);
        AddProjectmodel misc_ap4= new AddProjectmodel("Best Universities", "Find the best universities based on User rating", "Fall", "2022", "Miscellaneous", mem8);

        if(category.equals("Food"))
        {
            details.add(food_ap1);
            details.add(food_ap2);
            details.add(food_ap3);
        }
        else if(category.equals("Travel"))
        {
            details.add(travel_ap1);
            details.add(travel_ap2);
            details.add(travel_ap3);
        }
        else if(category.equals("Environment"))
        {
            details.add(enviro_ap1);
            details.add(enviro_ap2);

        }
        else if(category.equals("Medical"))
        {
            details.add(medical_ap1);
            details.add(medical_ap2);

        }
        else
        {
            details.add(misc_ap1);
            details.add(misc_ap2);
            details.add(misc_ap3);details.add(misc_ap4);

        }

        if (!(details.size() == 0)) {
            projectAdapter.notifyDataSetChanged();
            noData.setVisibility(View.INVISIBLE);
        } else {
            noData.setVisibility(View.VISIBLE);
        }
        progressDialog.cancel();
    }
}
