package com.example.studibook.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.AddmemberModel;
import com.example.studibook.CommonUtils;
import com.example.studibook.DashBoard;
import com.example.studibook.R;
import com.example.studibook.ui.AddMemberModel;
import com.example.studibook.ui.EditProjectActivtiy;
import com.example.studibook.ui.EnterProjectDetails;
import com.example.studibook.ui.Login;
import com.example.studibook.ui.ProjectsListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class GalleryFragment extends Fragment {
    String category ="";

    String[] batchList = new String[]{"Fall", "Spring", "Summer"};
    //  String[] yearList = new String[]{"2022", "2021", "2020"};
    ArrayList<String> yearList;
   // String[] categoryList = new String[]{"Food", "Travel", "TransportTourism", "ChatMedia", "Evironment", "MarketPlaceEcommerce", "EventManagementEventTracking", "Medical", "Miscellaneous", "EducationTechnology"};
    String[] categoryList = new String[]{"Food","EducationTechnology","TransportTourism","Medical", "ChatMedia", "MarketPlaceEcommerce", "EventManagementEventTracking","HousingAccomodation","Evironment",  "Miscellaneous"};
    String[] showlist = new String[]{"Food","Education & Technology","Transport & Tourism","Medical", "Chat & Media", "Market Place & E-commerce", "Event Management & Event Tracking","Housing & Accomodation","ENvironment",  "Miscellaneous"};


    AutoCompleteTextView batchView, yearView, categoryView;
    TextInputEditText id_enterTitle, id_description, id_entername, id_enterSid, id_enterEmail, id_entergithub;
    Button id_continue, id_addmember, id_back;
    ArrayList<AddMemberModel> addMemberModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), Login.class);
        intent.putExtra("FRAGMENT", "TRUE");
        intent.putExtra("DELETE", "FALSE");
        intent.putExtra("EDIT", "FALSE");
        startActivityForResult(intent, 2);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_enter_project_details, container, false);
        batchView = root.findViewById(R.id.id_selectbatch);
        yearView = root.findViewById(R.id.id_selectyear);
        categoryView = root.findViewById(R.id.id_selectcategory);
        id_back = root.findViewById(R.id.id_back);
        id_entergithub = root.findViewById(R.id.id_entergithub);
        id_addmember = root.findViewById(R.id.id_addmember);
        id_continue = root.findViewById(R.id.id_continue);
        id_enterEmail = root.findViewById(R.id.id_enterEmail);
        id_enterSid = root.findViewById(R.id.id_enterSid);
        id_entername = root.findViewById(R.id.id_entername);
        id_description = root.findViewById(R.id.id_description);
        id_enterTitle = root.findViewById(R.id.id_enterTitle);
        yearList = new ArrayList<>();
        setYear(yearList);

        setSpinnerAdapterValues();

        id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }

    private void setYear(ArrayList<String> yearList) {
        for (int i = 2016; i <= 2026; i++) {
            yearList.add("" + i);
        }
    }


    private void setSpinnerAdapterValues() {
        ArrayAdapter<String> batchadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, batchList);
        batchView.setAdapter(batchadapter);


        ArrayAdapter<String> yearadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, yearList);
        yearView.setAdapter(yearadapter);

        ArrayAdapter<String> categoryadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, showlist);
        categoryView.setAdapter(categoryadapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = id_entername.getText().toString().trim();
                String mail = id_enterEmail.getText().toString().trim();
                String sid = id_enterSid.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sid)) {
                    Toast.makeText(getActivity(), "Enter SID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getActivity(), "Enter Mail", Toast.LENGTH_SHORT).show();
                } else {

                    if (addMemberModelArrayList != null) {
                        saveList(addMemberModelArrayList, name, mail, sid);
                    } else {
                        addMemberModelArrayList = new ArrayList<>();
                        saveList(addMemberModelArrayList, name, mail, sid);
                    }

                    id_enterEmail.setText("");
                    id_entername.setText("");
                    id_enterSid.setText("");


                }
            }
        });
        id_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String name = id_entername.getText().toString().trim();
                    String mail = id_enterEmail.getText().toString().trim();
                    String sid = id_enterSid.getText().toString().trim();
                    if (name.isEmpty()) {

                    } else if (mail.isEmpty()) {

                    } else if (sid.isEmpty()) {

                    } else {
                        if (addMemberModelArrayList != null) {
                            saveList(addMemberModelArrayList, name, mail, sid);
                        } else {
                            addMemberModelArrayList = new ArrayList<>();
                            saveList(addMemberModelArrayList, name, mail, sid);
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (CommonUtils.isConnectedToInternet(getActivity())) {
                        String enterProjectTitle = id_enterTitle.getText().toString().trim();
                        String enterProjectdescription = id_description.getText().toString();
                        String batch = batchView.getText().toString();
                        String year = yearView.getText().toString();
                        String ca = categoryView.getText().toString();
                    /*    "Food","EducationTechnology","TransportTourism","Medical", "ChatMedia",
                                "MarketPlaceEcommerce", "EventManagementEventTracking","HousingAccomodation",
                                "Evironment",  "Miscellaneous";*/

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
                            Toast.makeText(getActivity(), "Enter project Title", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(enterProjectdescription)) {
                            Toast.makeText(getActivity(), "Enter project Description", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(batch)) {
                            Toast.makeText(getActivity(), "Select Batch", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(year)) {
                            Toast.makeText(getActivity(), "Select Year", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(category)) {
                            Toast.makeText(getActivity(), "Select Category", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(githubLink)) {
                            Toast.makeText(getActivity(), "Select GitHub Link", Toast.LENGTH_SHORT).show();
                        } else {
                            if (addMemberModelArrayList != null) {
                                if (addMemberModelArrayList.size() >= 0) {
                                    ArrayList<AddmemberModel> list = new ArrayList<>();
                                    for (int i = 0; i < addMemberModelArrayList.size(); i++) {
                                        list.add(new AddmemberModel(addMemberModelArrayList.get(i).getName()
                                                , addMemberModelArrayList.get(i).getSid(), addMemberModelArrayList.get(i).getEmail()));
                                    }

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    String key = database.getReference().push().getKey();
                                    DatabaseReference myRefstudent = database.getReference("Projects").child(category)
                                            .child(key);


                                    AddProjectmodel addProjectmodel = new AddProjectmodel(enterProjectTitle, enterProjectdescription,
                                            batch, year, category, githubLink, key, list);

                                    myRefstudent.setValue(addProjectmodel).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("FAILURE", "FAILURE" + e.getMessage().toString());
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                            DatabaseReference databaseReference = firebaseDatabase.getReference("Title");
                                            String unquekey = databaseReference.push().getKey();
                                            String cate = "ProjectTitles";
                                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.hasChild(cate)) {
                                                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                                            String keyword = snapshot1.getValue(String.class);
/*
                                                            if(snapshot1.getKey().toString().toLowerCase().equalsIgnoreCase(category.toLowerCase(Locale.ROOT))){
                                                                String appendKeyword=keyword+","+enterProjectTitle+",";
                                                                databaseReference.child(cate).setValue(appendKeyword);
                                                            }
*/
                                                            String appendKeyword = keyword + enterProjectTitle + ",";
                                                            databaseReference.child(cate).setValue(appendKeyword);


                                                        }
                                                    } else {
                                                        databaseReference.child(cate).setValue(enterProjectTitle + ",");
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });


                                            Toast.makeText(getActivity(), "Project Details are saved....", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    saveCount(category);


                                    //   navController.findNavController(this).navigate(R.id.action_b_to_a);


                                } else {
                                    Toast.makeText(getActivity(), "Add member details", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    private void saveCount(String category) {
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
                        }
                        else if (category.equals("EducationTechnology")) {
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
                        }
                        else if (category.equals("EventManagementEventTracking")) {
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
                                        Integer.toString(educationcount),
                                        Integer.toString(housingcount)
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
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";
                    }
                    else if (category.equals("HousingAccomodation")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="1";
                    }

                    else if (category.equals("EventManagementEventTracking")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="1";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";


                    } else if (category.equals("EducationTechnology")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="1";
                        housingcount="0";

                    } else if (category.equals("MarketPlaceEcommerce")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="1";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("Travel")) {
                        medicalCount = "0";
                        travelCount = "1";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("ChatMedia")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="1";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("TransportTourism")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="1";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("Food")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "1";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("Miscellaneous")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "1";
                        foodCount = "0";
                        environmentCount = "0";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    } else if (category.equals("Evironment")) {
                        medicalCount = "0";
                        travelCount = "0";
                        miscellaneousCount = "0";
                        foodCount = "0";
                        environmentCount = "1";
                        transportcount="0";
                        eventcount="0";
                        mediacount="0";
                        marketcount="0";
                        educationcount="0";
                        housingcount="0";

                    }
                    ProjectsListModel studentPieModels =
                            new ProjectsListModel(foodCount, travelCount,transportcount, environmentCount,mediacount,marketcount
                                    ,eventcount,medicalCount,
                                    miscellaneousCount,educationcount,housingcount);
                    myRefstudent.child("Projects").setValue(studentPieModels);

                }

                gotoDashBoard();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void gotoDashBoard() {
        Intent intent = new Intent(getActivity(), DashBoard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void saveList(ArrayList<AddMemberModel> addMemberModelArrayList, String name, String mail, String sid) {
        AddMemberModel addMemberModel = new AddMemberModel(name, mail, sid);
        addMemberModelArrayList.add(addMemberModel);

    }


}