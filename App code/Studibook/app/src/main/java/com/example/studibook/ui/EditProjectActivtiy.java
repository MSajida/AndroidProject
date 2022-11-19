package com.example.studibook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.AddmemberModel;
import com.example.studibook.CommonUtils;
import com.example.studibook.DashBoard;
import com.example.studibook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class EditProjectActivtiy extends AppCompatActivity {


    String[] batchList = new String[]{"Fall", "Spring", "Summer"};
    String[] yearList = new String[]{"2026","2025","2024","2023","2022", "2021", "2020","2019","2018","2017","2016"};
    String[] categoryList = new String[]{"Food", "Travel", "Environment", "Medical", "Miscellaneous"};

    AutoCompleteTextView batchView, yearView, categoryView;
    TextInputEditText id_enterTitle, id_description, id_entername, id_enterSid, id_enterEmail,id_entergithub;
    Button id_continue, id_addmember, id_back;
    ArrayList<AddMemberModel> addMemberModelArrayList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project_activtiy);

        batchView = findViewById(R.id.id_selectbatch);
        yearView = findViewById(R.id.id_selectyear);
        id_entergithub = findViewById(R.id.id_entergithub);
        categoryView = findViewById(R.id.id_selectcategory);
        id_back = findViewById(R.id.id_back);
        id_addmember = findViewById(R.id.id_addmember);
        id_continue = findViewById(R.id.id_continue);
        id_enterEmail = findViewById(R.id.id_enterEmail);
        id_enterSid = findViewById(R.id.id_enterSid);
        id_entername = findViewById(R.id.id_entername);
        id_description = findViewById(R.id.id_description);
        id_enterTitle = findViewById(R.id.id_enterTitle);
        AddProjectmodel addProjectmodel= (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");

        setSpinnerAdapterValues(addProjectmodel);
        setData(addProjectmodel);
id_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(EditProjectActivtiy.this, DashBoard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
});
        id_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressDialog=new ProgressDialog(EditProjectActivtiy.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();

                    if (CommonUtils.isConnectedToInternet(EditProjectActivtiy.this)){
                        String enterProjectTitle = id_enterTitle.getText().toString();
                        String enterProjectdescription = id_description.getText().toString();
                        String batch = batchView.getText().toString();
                        String year = yearView.getText().toString();
                        String category = categoryView.getText().toString();
                        String githubLink = id_entergithub.getText().toString();
                        if (TextUtils.isEmpty(enterProjectTitle)) {
                            Toast.makeText(EditProjectActivtiy.this, "Enter project Title", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(enterProjectdescription)) {
                            Toast.makeText(EditProjectActivtiy.this, "Enter project Description", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(batch)) {
                            Toast.makeText(EditProjectActivtiy.this, "Select Batch", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(year)) {
                            Toast.makeText(EditProjectActivtiy.this, "Select Year", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(category)) {
                            Toast.makeText(EditProjectActivtiy.this, "Select Category", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(githubLink)) {
                            Toast.makeText(EditProjectActivtiy.this, "Select GitHub Link", Toast.LENGTH_SHORT).show();
                        }else{
                            ArrayList<AddmemberModel> addMemberModelArrayList=addProjectmodel.getMemberList();
                            ArrayList<AddmemberModel> list = new ArrayList<>();
                            for (int i = 0; i < addMemberModelArrayList.size(); i++) {
                                list.add(new AddmemberModel(addMemberModelArrayList.get(i).getName()
                                        , addMemberModelArrayList.get(i).getSid(), addMemberModelArrayList.get(i).getMail()));
                            }

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRefstudent = database.getReference("Projects").child(category)
                                    .child(addProjectmodel.getKey());

                            AddProjectmodel addProjectmodelss = new AddProjectmodel(enterProjectTitle, enterProjectdescription,
                                    batch, year, category, githubLink, addProjectmodel.getKey(), list);
                            myRefstudent.setValue(addProjectmodelss).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference= firebaseDatabase.getReference("Keywords").child("Categories");
                                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.hasChild(category)){
                                                for (DataSnapshot snapshot1:snapshot.getChildren()){
                                                    String keyword=snapshot1.getValue(String.class);
                                                    if(snapshot1.getKey().toString().toLowerCase().contains(category.toLowerCase())){
                                                        if(keyword.toLowerCase().contains(enterProjectTitle.toLowerCase())){

                                                        }else{
                                                            if(keyword.toLowerCase().contains(enterProjectTitle.toLowerCase())){

                                                            }else{
                                                                String appendKeyword=keyword+","+enterProjectTitle;
                                                                databaseReference.child(category).setValue(appendKeyword);
                                                            }

                                                        }
                                                    }



                                                }

                                            }else{
                                                databaseReference.child(category).setValue(enterProjectTitle);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });



                                }
                            });
                            if(category.toString().toLowerCase().equals(addProjectmodel.getCategory().toLowerCase())){
                                if(progressDialog.isShowing()){
                                    progressDialog.cancel();
                                }
                                Intent intent=new Intent(EditProjectActivtiy.this, DashBoard.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }else {

                                saveCount(category,addProjectmodel.getCategory());


                            }
                        }

                    }else{
                        Toast.makeText(EditProjectActivtiy.this, "Check Internet....", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
            }
        });
    }

    private void saveCount(String category, String category1) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects");
        myRefstudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        ProjectsListModel projectsListModel = postSnapshot.getValue(ProjectsListModel.class);
                        String medical = projectsListModel.getMedical();
                        String travel = projectsListModel.getTravel();
                        String food = projectsListModel.getFood();
                        String miscellaneous = projectsListModel.getMiscellaneous();
                        String environment = projectsListModel.getEnvironment();

                        String med=postSnapshot.child(category1.toString().toLowerCase()).getKey();
                        if(med.toString().toLowerCase().equalsIgnoreCase("Medical")){
                            int num=Integer.parseInt(medical);
                            medical=String.valueOf(num-1);
                        }else     if(med.toString().toLowerCase().equalsIgnoreCase("Travel")){
                            int num=Integer.parseInt(travel);
                            travel=String.valueOf(num-1);
                        }else     if(med.toString().toLowerCase().equalsIgnoreCase("Food")){
                            int num=Integer.parseInt(food);
                            food=String.valueOf(num-1);
                        }else     if(med.toString().toLowerCase().equalsIgnoreCase("Miscellaneous")){
                            int num=Integer.parseInt(miscellaneous);
                            miscellaneous=String.valueOf(num-1);
                        }else     if(med.toString().toLowerCase().equalsIgnoreCase("Environment")){
                            int num=Integer.parseInt(environment);
                            environment=String.valueOf(num-1);
                        }
                        int medicalCount = 0;
                        int travelCount = 0;
                        int foodCount = 0;
                        int miscellaneousCount = 0;
                        int environmentCount = 0;

                        if (category.equals("Medical")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            environmentCount = Integer.parseInt(environment);
                            medicalCount = medicalCount + 1;
                        } else if (category.equals("Travel")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            environmentCount = Integer.parseInt(environment);
                            travelCount = travelCount + 1;
                        } else if (category.equals("Food")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            environmentCount = Integer.parseInt(environment);

                            foodCount = foodCount + 1;
                        } else if (category.equals("Miscellaneous")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            environmentCount = Integer.parseInt(environment);
                            miscellaneousCount = miscellaneousCount + 1;
                        } else if (category.equals("Environment")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            environmentCount = Integer.parseInt(environment);

                            environmentCount = environmentCount + 1;
                        }
                        ProjectsListModel studentPieModels =
                                new ProjectsListModel(Integer.toString(foodCount),
                                        Integer.toString(travelCount),
                                        Integer.toString(environmentCount),
                                        Integer.toString(medicalCount),
                                        Integer.toString(miscellaneousCount)
                                );
                        myRefstudent.child("Projects").setValue(studentPieModels);

                    }
                }else {
                    String medicalCount = "0";
                    String travelCount = "0";
                    String foodCount = "0";
                    String miscellaneousCount = "0";
                    String environmentCount = "0";
                    if (category.equals("Medical")) {
                        medicalCount = "1";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                    } else if (category.equals("Travel")) {
                        medicalCount = "0";
                        travelCount = "1";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                    } else if (category.equals("Food")) {
                        medicalCount = "0";
                        travelCount ="0";
                        miscellaneousCount ="0";
                        foodCount ="1";
                        environmentCount = "0";
                    } else if (category.equals("Miscellaneous")) {
                        medicalCount = "0";
                        travelCount ="0";
                        miscellaneousCount ="1";
                        foodCount = "0";
                        environmentCount ="0";
                    } else if (category.equals("Environment")) {
                        medicalCount = "0";
                        travelCount ="0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "1";
                    }
                    ProjectsListModel studentPieModels =
                            new ProjectsListModel(foodCount,travelCount,environmentCount,
                                    medicalCount,
                                    miscellaneousCount
                            );
                    myRefstudent.child("Projects").setValue(studentPieModels);

                }

                if(progressDialog.isShowing()){
                    progressDialog.cancel();
                }
                Intent intent=new Intent(EditProjectActivtiy.this, DashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                if(progressDialog.isShowing()){
                    progressDialog.cancel();
                }
            }

        });
    }

    private void setSpinnerAdapterValues(AddProjectmodel addProjectmodel) {
        batchView.setText(addProjectmodel.getBatch());
        yearView.setText(addProjectmodel.getYear());
        categoryView.setText(addProjectmodel.getCategory());
        ArrayAdapter<String> batchadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, batchList);
        batchView.setAdapter(batchadapter);

        ArrayAdapter<String> yearadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, yearList);
        yearView.setAdapter(yearadapter);

        ArrayAdapter<String> categoryadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, categoryList);
        categoryView.setAdapter(categoryadapter);
    }

    private void setData(AddProjectmodel addProjectmodel) {
        id_enterTitle.setText(addProjectmodel.getTitle());
        id_description.setText(addProjectmodel.getDescription());
      //  batchView.setText(addProjectmodel.getBatch());
        //yearView.setText(addProjectmodel.getYear());
        //categoryView.setText(addProjectmodel.getCategory());
        id_entergithub.setText(addProjectmodel.getGithubLink());

    }


}