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
    String[] yearList = new String[]{"2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016"};
    //String[] categoryList = new String[]{"Food", "Travel", "TransportTourism","ChatMedia","Evironment","MarketPlaceEcommerce","EventManagementEventTracking", "Medical", "Miscellaneous","EducationTechnology"};
    String[] categoryList = new String[]{"Food", "EducationTechnology", "TransportTourism", "Medical", "ChatMedia", "MarketPlaceEcommerce", "EventManagementEventTracking", "HousingAccomodation", "Evironment", "Miscellaneous"};
    String[] showlist = new String[]{"Food","Education & Technology","Transport & Tourism","Medical", "Chat & Media", "Market Place & E-commerce", "Event Management & Event Tracking","Housing & Accomodation","ENvironment",  "Miscellaneous"};

    String category="";
    AutoCompleteTextView batchView, yearView, categoryView;
    TextInputEditText id_enterTitle, id_description, id_entername, id_enterSid, id_enterEmail, id_entergithub;
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
        AddProjectmodel addProjectmodel = (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");

        setSpinnerAdapterValues(addProjectmodel);
        setData(addProjectmodel);
        id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents = getIntent();

                try {
                    ArrayList<String> test = intents.getStringArrayListExtra("SEARCHEDARRAY");
                    String searcheddata = intents.getStringExtra("SEARCHEDDATA");
                    String isData = intents.getStringExtra("BOOLEAN");

                    Intent intent = new Intent();
                    intent.putExtra("MESSAGE", "message");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putStringArrayListExtra("SEARCHEDARRAY", test);
                    intent.putExtra("SEARCHEDDATA", searcheddata);
                    intent.putExtra("BOOLEAN", isData);

                    setResult(6, intent);
                    finish();//finishing activity

                } catch (Exception e) {
                    e.printStackTrace();
                }
             /*   Intent intent = new Intent(EditProjectActivtiy.this, DashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();*/
            }
        });
        id_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressDialog = new ProgressDialog(EditProjectActivtiy.this);
                    progressDialog.setMessage("Loading....");
                    progressDialog.show();

                    if (CommonUtils.isConnectedToInternet(EditProjectActivtiy.this)) {
                        String enterProjectTitle = id_enterTitle.getText().toString();
                        String enterProjectdescription = id_description.getText().toString();
                        String batch = batchView.getText().toString();
                        String year = yearView.getText().toString();
                        String ca = categoryView.getText().toString();

                        if(ca.contains("Food")){
                            category="Food";
                        } else if(ca.contains("Education")){
                            category="EducationTechnology";
                        } else if(ca.contains("Transport")){
                            category="TransportTourism";
                        } else if(ca.contains("Medical")){
                            category="Medical";
                        } else if(ca.contains("Chat")){
                            category="ChatMedia";
                        }else if(ca.contains("Market")){
                            category="MarketPlaceEcommerce";
                        }else if(ca.contains("Event")){
                            category="EventManagementEventTracking";
                        }else if(ca.contains("Housing")){
                            category="HousingAccomodation";
                        }else if(ca.contains("Housing")){
                            category="HousingAccomodation";
                        }else if(ca.contains("ironment")){
                            category="Evironment";
                        }else if(ca.contains("Miscellaneous")){
                            category="Miscellaneous";
                        }

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
                        } else {
                            ArrayList<AddmemberModel> addMemberModelArrayList = addProjectmodel.getMemberList();
                            ArrayList<AddmemberModel> list = new ArrayList<>();

                            //update list of members
                            for (int i = 0; i < addMemberModelArrayList.size(); i++) {
                                list.add(new AddmemberModel(addMemberModelArrayList.get(i).getName()
                                        , addMemberModelArrayList.get(i).getSid(), addMemberModelArrayList.get(i).getMail()));
                            }

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRefstudent = database.getReference("Projects").child(category)
                                    .child(addProjectmodel.getKey());

                            AddProjectmodel addProjectmodelss = new AddProjectmodel(enterProjectTitle, enterProjectdescription,
                                    batch, year, category, githubLink, addProjectmodel.getKey(), list);

                            // when you update category then remove existing project from its category
                            deleteProject(addProjectmodel.getCategory(), addProjectmodel.getKey());

                            // when you update category then add project to it
                            myRefstudent.setValue(addProjectmodelss);

                            //update and delete keywords
                            updateKeyword(addProjectmodel, category, enterProjectTitle);
                            if (progressDialog.isShowing()) {
                                progressDialog.cancel();
                            }

                            // update and delete project count
                            updateCount(category, addProjectmodel);

                        }

                    } else {
                        Toast.makeText(EditProjectActivtiy.this, "Check Internet....", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    private void updateCount(String category, AddProjectmodel addProjectmodel) {
        if (category.toString().toLowerCase().equals(addProjectmodel.getCategory().toLowerCase())) {
            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            Toast.makeText(EditProjectActivtiy.this, "Project Updated Successfully....", Toast.LENGTH_SHORT).show();

         /*   Intent intent = new Intent(EditProjectActivtiy.this, DashBoard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
         */
            Intent intents = getIntent();

            try {
                ArrayList<String> test = intents.getStringArrayListExtra("SEARCHEDARRAY");
                String searcheddata = intents.getStringExtra("SEARCHEDDATA");
                String isData = intents.getStringExtra("BOOLEAN");

                Intent intent = new Intent();
                intent.putExtra("MESSAGE", "message");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putStringArrayListExtra("SEARCHEDARRAY", test);
                intent.putExtra("SEARCHEDDATA", searcheddata);
                intent.putExtra("BOOLEAN", isData);

                setResult(6, intent);
                finish();//finishing activity
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            saveCount(category, addProjectmodel.getCategory());
        }
    }

    private void updateKeyword(AddProjectmodel addProjectmodel, String category, String enterProjectTitle) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Title");
        String cate = "ProjectTitles";

        databaseReference.child(cate).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String serverkeyword = snapshot.getValue(String.class);
                String remove = addProjectmodel.getTitle().toString().trim() + ",";
                String word = serverkeyword.replace(remove, "");
                databaseReference.child(cate).setValue(word + enterProjectTitle + ",");

                /*if (snapshot.hasChild(cate)) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String keyword = snapshot1.getValue(String.class);
                        String remove = addProjectmodel.getTitle().toString().trim()+ ",";
                        String word = keyword.replace(remove, "");


                    *//*    // delelte existing keyword from previous category
                        if (snapshot1.getKey().toString().toLowerCase().equalsIgnoreCase(addProjectmodel.getCategory().toLowerCase())) {
                            if (keyword.toLowerCase().contains(addProjectmodel.getTitle().toLowerCase())) {
                                String remove = addProjectmodel.getTitle() + ",";
                                String word = keyword.replace(remove, "");
                                databaseReference.child(cate).setValue(word);
                            }
                        }

                        // add keyword in new category
                        if (snapshot1.getKey().toString().toLowerCase().equalsIgnoreCase(category.toLowerCase())) {
                            if (keyword.toLowerCase().contains(enterProjectTitle.toLowerCase())) {

                            } else {
                                if (keyword.toLowerCase().equalsIgnoreCase(enterProjectTitle.toLowerCase())) {

                                } else {
                                    String appendKeyword = keyword + "," + enterProjectTitle + ",";
                                    databaseReference.child(category).setValue(appendKeyword);

                                }

                            }
                        }
*//*

                    }

                } else {

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String keyword = snapshot1.getValue(String.class);
                        // delelte existing keyword from previous category
                        if (snapshot1.getKey().toString().toLowerCase().equalsIgnoreCase(addProjectmodel.getCategory().toLowerCase())) {
                            if (keyword.toLowerCase().contains(addProjectmodel.getTitle().toLowerCase())) {
                                String remove = addProjectmodel.getTitle() + ",";
                                String word = keyword.replace(remove, "");
                                databaseReference.child(cate).setValue(word);
                            }
                        }
                    }

                    // create keyword in categories
                    databaseReference.child(cate).setValue(enterProjectTitle+",");

                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void deleteProject(String category, String key) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("Projects").child(category);
        myRefstudent.child(key).removeValue();
    }

    private void saveCount(String category, String category1) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects");
        myRefstudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        ProjectsListModel projectsListModel = postSnapshot.getValue(ProjectsListModel.class);
                        String medical = projectsListModel.getMedical();
                        String travel = projectsListModel.getTravel();
                        String food = projectsListModel.getFood();
                        String miscellaneous = projectsListModel.getMiscellaneous();
                        String environment = projectsListModel.getEvironment();
                        String transport = projectsListModel.getTransportTourism();
                        String media = projectsListModel.getChatMedia();
                        String market = projectsListModel.getMarketPlaceEcommerce();
                        String event = projectsListModel.getEventManagementEventTracking();
                        String education = projectsListModel.getEducationTechnology();
                        String housing = projectsListModel.getHousing();

                        String med = postSnapshot.child(category1.toString().toLowerCase()).getKey();


                        if (med.toString().toLowerCase().equalsIgnoreCase("Medical")) {
                            int num = Integer.parseInt(medical);
                            medical = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("Travel")) {
                            int num = Integer.parseInt(travel);
                            travel = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("HousingAccomodation")) {
                            int num = Integer.parseInt(housing);
                            housing = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("Food")) {
                            int num = Integer.parseInt(food);
                            food = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("Miscellaneous")) {
                            int num = Integer.parseInt(miscellaneous);
                            miscellaneous = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("Evironment")) {
                            int num = Integer.parseInt(environment);
                            environment = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("TransportTourism")) {
                            int num = Integer.parseInt(transport);
                            transport = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("ChatMedia")) {
                            int num = Integer.parseInt(media);
                            media = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("MarketPlaceEcommerce")) {
                            int num = Integer.parseInt(market);
                            market = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("EventManagementEventTracking")) {
                            int num = Integer.parseInt(event);
                            event = String.valueOf(num - 1);
                        } else if (med.toString().toLowerCase().equalsIgnoreCase("EducationTechnology")) {
                            int num = Integer.parseInt(education);
                            environment = String.valueOf(num - 1);
                        }


                        int medicalCount = 0;
                        int travelCount = 0;
                        int foodCount = 0;
                        int miscellaneousCount = 0;
                        int environmentCount = 0;
                        int transportcount = 0;
                        int mediacount = 0;
                        int marketcount = 0;
                        int eventcount = 0;
                        int educationcount = 0;
                        int housingcount = 0;

                        if (category.equals("Medical")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);
                            medicalCount = medicalCount + 1;
                        } else if (category.equals("HousingAccomodation")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);
                            housingcount = housingcount + 1;
                        } else if (category.equals("EducationTechnology")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            educationcount = educationcount + 1;
                        } else if (category.equals("EventManagementEventTracking")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            eventcount = eventcount + 1;
                        } else if (category.equals("MarketPlaceEcommerce")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            marketcount = marketcount + 1;

                        } else if (category.equals("ChatMedia")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);
                            mediacount = mediacount + 1;


                        } else if (category.equals("TransportTourism")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);
                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            transportcount = transportcount + 1;

                        } else if (category.equals("Travel")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);

                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            travelCount = travelCount + 1;
                        } else if (category.equals("Food")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);

                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            foodCount = foodCount + 1;
                        } else if (category.equals("Miscellaneous")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);

                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            miscellaneousCount = miscellaneousCount + 1;
                        } else if (category.equals("Evironment")) {
                            medicalCount = Integer.parseInt(medical);
                            travelCount = Integer.parseInt(travel);
                            miscellaneousCount = Integer.parseInt(miscellaneous);
                            foodCount = Integer.parseInt(food);

                            transportcount = Integer.parseInt(transport);
                            environmentCount = Integer.parseInt(environment);
                            mediacount = Integer.parseInt(media);
                            marketcount = Integer.parseInt(market);
                            educationcount = Integer.parseInt(education);
                            eventcount = Integer.parseInt(event);
                            housingcount = Integer.parseInt(housing);

                            environmentCount = environmentCount + 1;
                        }
                        ProjectsListModel studentPieModels =
                                new ProjectsListModel(Integer.toString(foodCount),
                                        Integer.toString(travelCount),
                                        Integer.toString(transportcount),
                                        Integer.toString(environmentCount),
                                        Integer.toString(mediacount),
                                        Integer.toString(marketcount),
                                        Integer.toString(eventcount),
                                        Integer.toString(medicalCount),
                                        Integer.toString(miscellaneousCount),
                                        Integer.toString(educationcount), Integer.toString(housingcount)
                                );
                        myRefstudent.child("Projects").setValue(studentPieModels);

                    }
                } else {
                    String medicalCount = "0";
                    String travelCount = "0";
                    String foodCount = "0";
                    String miscellaneousCount = "0";
                    String environmentCount = "0";
                    String transportcount = "0";
                    String eventcount = "0";
                    String mediacount = "0";
                    String marketcount = "0";
                    String educationcount = "0";
                    String housingcount = "0";

                    if (category.equals("Medical")) {
                        medicalCount = "1";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("HousingAccomodation")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "1";


                    } else if (category.equals("EventManagementEventTracking")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "1";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("EducationTechnology")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "1";
                        housingcount = "0";

                    } else if (category.equals("MarketPlaceEcommerce")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "1";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("Travel")) {
                        medicalCount = "0";
                        travelCount = "1";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("ChatMedia")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "1";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("TransportTourism")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "1";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("Food")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "1";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("Miscellaneous")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "1";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    } else if (category.equals("Evironment")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "1";
                        transportcount = "0";
                        eventcount = "0";
                        mediacount = "0";
                        marketcount = "0";
                        educationcount = "0";
                        housingcount = "0";

                    }
                    ProjectsListModel studentPieModels =
                            new ProjectsListModel(foodCount, travelCount, transportcount, environmentCount, mediacount, marketcount
                                    , eventcount, medicalCount,
                                    miscellaneousCount, educationcount, housingcount);
                    myRefstudent.child("Projects").setValue(studentPieModels);
                    //   myRefstudent.child("Projects").setValue(studentPieModels);

                }

                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                Toast.makeText(EditProjectActivtiy.this, "Project Updated Successfully....", Toast.LENGTH_SHORT).show();

                /*Intent intent = new Intent(EditProjectActivtiy.this, DashBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                */
                Intent intents = getIntent();

                try {
                    ArrayList<String> test = intents.getStringArrayListExtra("SEARCHEDARRAY");
                    String searcheddata = intents.getStringExtra("SEARCHEDDATA");
                    String isData = intents.getStringExtra("BOOLEAN");

                    Intent intent = new Intent();
                    intent.putExtra("MESSAGE", "message");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putStringArrayListExtra("SEARCHEDARRAY", test);
                    intent.putExtra("SEARCHEDDATA", searcheddata);
                    intent.putExtra("BOOLEAN", isData);

                    setResult(6, intent);
                    finish();//finishing activity

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                if (progressDialog.isShowing()) {
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
                android.R.layout.simple_dropdown_item_1line, showlist);
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