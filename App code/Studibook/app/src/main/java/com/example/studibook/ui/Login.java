package com.example.studibook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button login;
    EditText _id_enter_username, _id_enter_paswword;
    Boolean edit = false, fragment = false;
    ImageView id_back;
    Boolean delete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _id_enter_paswword = findViewById(R.id._id_enter_paswword);
        id_back = findViewById(R.id.id_back);
        _id_enter_username = findViewById(R.id._id_enter_username);
        login = findViewById(R.id.login);

        try {
            String fromEdit = getIntent().getStringExtra("EDIT");
            String fromfragment = getIntent().getStringExtra("FRAGMENT");
            String fromDelete = getIntent().getStringExtra("DELETE");
            if (fromEdit.equalsIgnoreCase("TRUE")) {
                edit = true;
            } else {
                edit = false;
            }
            if (fromfragment.equalsIgnoreCase("TRUE")) {
                fragment = true;
            } else {
                fragment = false;
            }
            if (fromDelete.equalsIgnoreCase("TRUE")) {
                delete = true;
            } else {
                delete = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        id_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment) {
                    Intent intent = new Intent();
                    intent.putExtra("MESSAGE", "message");
                    setResult(2, intent);
                    finish();//finishing activity
                } else {
                    finish();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = _id_enter_username.getText().toString();
                String password = _id_enter_paswword.getText().toString();
                //username = "Instructor";
                //password = "nwmissouri";

                // if(username.equals("Instructor") && password.equals("nwmissouri"))
                if (username.equals("Instructor") && password.equals("nwmissouri")) {
                    if (delete) {
                        String CATEGORY = getIntent().getStringExtra("CATEGORY");
                        int POSITION = getIntent().getIntExtra("POSITION", 0);
                        String KEY = getIntent().getStringExtra("KEY");
                        AddProjectmodel addProjectmodel = (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String key = database.getReference().push().getKey();
                        DatabaseReference myRefstudent = database.getReference("Projects").child(addProjectmodel.getCategory());
                        myRefstudent.child(KEY).removeValue();
                        removeCount(CATEGORY, POSITION);
                        removeKeyword(addProjectmodel);
                    }

                    if (edit) {
                        Intent intents = getIntent();
                        ArrayList<String> test = intents.getStringArrayListExtra("SEARCHEDARRAY");
                        String searcheddata = intents.getStringExtra("SEARCHEDDATA");
                        String isData = intents.getStringExtra("BOOLEAN");

                        AddProjectmodel addProjectmodel = (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");
                        Intent intent = new Intent(Login.this, EditProjectActivtiy.class);
                        intent.putExtra("PROJECTDETAILS", addProjectmodel);
                        intent.putStringArrayListExtra("SEARCHEDARRAY", test);
                        intent.putExtra("SEARCHEDDATA", searcheddata);
                        intent.putExtra("BOOLEAN", isData);

                        startActivityForResult(intent, 6);
                    } else {

                        finish();

                         //   Toast.makeText(Login.this, "Please enter correct username/password", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(Login.this, "Please enter correct username/password", Toast.LENGTH_LONG).show();
                }
                //         finish();


                //        Intent intent=new Intent(Login.this,EnterProjectDetails.class);
                //      startActivity(intent);
            }
        });
    }

    private void removeKeyword(AddProjectmodel addProjectmodel) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        String cate = "ProjectTitles";
        DatabaseReference databaseReference = firebaseDatabase.getReference("Title");
        databaseReference.child(cate).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String serverkeyword = snapshot.getValue(String.class);
                String remove = addProjectmodel.getTitle().toString().trim() + ",";
                String word = serverkeyword.replace(remove, "");
                databaseReference.child(cate).setValue(word);

                Toast.makeText(Login.this, "Project Deleted....", Toast.LENGTH_SHORT).show();


                Intent intents = getIntent();
                ArrayList<String> test = intents.getStringArrayListExtra("SEARCHEDARRAY");
                String searcheddata = intents.getStringExtra("SEARCHEDDATA");
                String isData = intents.getStringExtra("BOOLEAN");
                Intent intent = new Intent(Login.this, FoodRelatedProject.class);
                intent.putExtra("MESSAGE", "message");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putStringArrayListExtra("SEARCHEDARRAY", test);
                intent.putExtra("SEARCHEDDATA", searcheddata);
                intent.putExtra("BOOLEAN", isData);
                startActivity(intent);
                finish();//finishing activity

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 6) {
            String fromEdit = getIntent().getStringExtra("EDIT");
            String fromfragment = getIntent().getStringExtra("FRAGMENT");
            String fromDelete = getIntent().getStringExtra("DELETE");
            finish();
        }
    }

    private void removeCount(String toLowerCase, int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefstudent = database.getReference("CountOfProjects");

        myRefstudent.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
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

                            String med = postSnapshot.child(toLowerCase.toString().toLowerCase()).getKey();


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
                                education = String.valueOf(num - 1);
                            }


                            ProjectsListModel studentPieModels =
                                    new ProjectsListModel(food,
                                            travel,
                                            transport,
                                            environment,
                                            media,
                                            market,
                                            event,
                                            medical,
                                            miscellaneous,
                                            education,
                                            housing
                                    );
                            myRefstudent.child("Projects").setValue(studentPieModels);

                        }

                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        if (fragment) {
            Intent intent = new Intent();
            intent.putExtra("MESSAGE", "message");
            setResult(3, intent);
            finish();//finishing activity
        } else {
            finish();
        }
    }
}