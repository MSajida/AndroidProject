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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.AddmemberModel;
import com.example.studibook.CommonUtils;
import com.example.studibook.R;
import com.example.studibook.ui.AddMemberModel;
import com.example.studibook.ui.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    String[] batchList = new String[]{"Fall", "Spring", "Summer"};
    String[] yearList = new String[]{"2022", "2021", "2020"};
    String[] categoryList = new String[]{"Food", "Travel", "Environment", "Medical", "Miscellaneous"};

    AutoCompleteTextView batchView, yearView, categoryView;
    TextInputEditText id_enterTitle, id_description, id_entername, id_enterSid, id_enterEmail;
    Button id_continue, id_addmember, id_back;
    ArrayList<AddMemberModel> addMemberModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(getActivity(), Login.class);
        intent.putExtra("FRAGMENT","FRAGMENT");
        startActivity(intent);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.activity_enter_project_details, container, false);
        batchView = root.findViewById(R.id.id_selectbatch);
        yearView = root.findViewById(R.id.id_selectyear);
        categoryView = root.findViewById(R.id.id_selectcategory);
        id_back = root.findViewById(R.id.id_back);
        id_addmember = root.findViewById(R.id.id_addmember);
        id_continue = root.findViewById(R.id.id_continue);
        id_enterEmail = root.findViewById(R.id.id_enterEmail);
        id_enterSid = root.findViewById(R.id.id_enterSid);
        id_entername = root.findViewById(R.id.id_entername);
        id_description =root. findViewById(R.id.id_description);
        id_enterTitle = root.findViewById(R.id.id_enterTitle);

        setSpinnerAdapterValues();


        return root;
    }


    private void setSpinnerAdapterValues() {
        ArrayAdapter<String> batchadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, batchList);
        batchView.setAdapter(batchadapter);


        ArrayAdapter<String> yearadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, yearList);
        yearView.setAdapter(yearadapter);

        ArrayAdapter<String> categoryadapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, categoryList);
        categoryView.setAdapter(categoryadapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=id_entername.getText().toString().trim();
                String mail=id_enterEmail.getText().toString().trim();
                String sid=id_enterSid.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else   if(TextUtils.isEmpty(sid)){
                    Toast.makeText(getActivity(), "Enter SID", Toast.LENGTH_SHORT).show();
                }
                else   if(TextUtils.isEmpty(mail)){
                    Toast.makeText(getActivity(), "Enter Mail", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(addMemberModelArrayList!=null){
                        saveList(addMemberModelArrayList,name,mail,sid);
                    }else{
                        addMemberModelArrayList=new ArrayList<>();
                        saveList(addMemberModelArrayList,name,mail,sid);
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
                    String name=id_entername.getText().toString().trim();
                    String mail=id_enterEmail.getText().toString().trim();
                    String sid=id_enterSid.getText().toString().trim();
                    if(addMemberModelArrayList!=null){
                        saveList(addMemberModelArrayList,name,mail,sid);
                    }else{
                        addMemberModelArrayList=new ArrayList<>();
                        saveList(addMemberModelArrayList,name,mail,sid);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    if(CommonUtils.isConnectedToInternet(getActivity())){
                        String enterProjectTitle=id_enterTitle.getText().toString();
                        String enterProjectdescription=id_description.getText().toString();
                        String batch=batchView.getText().toString();
                        String year=yearView.getText().toString();
                        String category=categoryView.getText().toString();

                        if(TextUtils.isEmpty(enterProjectTitle)){
                            Toast.makeText(getActivity(), "Enter project Title", Toast.LENGTH_SHORT).show();
                        } else if(TextUtils.isEmpty(enterProjectdescription)){
                            Toast.makeText(getActivity(), "Enter project Description", Toast.LENGTH_SHORT).show();
                        } else if(TextUtils.isEmpty(batch)){
                            Toast.makeText(getActivity(), "Select Batch", Toast.LENGTH_SHORT).show();
                        } else if(TextUtils.isEmpty(year)){
                            Toast.makeText(getActivity(), "Select Year", Toast.LENGTH_SHORT).show();
                        }  else if(TextUtils.isEmpty(category)){
                            Toast.makeText(getActivity(), "Select Category", Toast.LENGTH_SHORT).show();
                        } else{
                            if(addMemberModelArrayList!=null){
                                if(addMemberModelArrayList.size()>=0){
                                    ArrayList<AddmemberModel> list=new ArrayList<>();
                                    for (int i=0;i<addMemberModelArrayList.size();i++){
                                        list.add(new AddmemberModel(addMemberModelArrayList.get(i).getName()
                                                ,addMemberModelArrayList.get(i).getSid(),addMemberModelArrayList.get(i).getEmail()));
                                    }

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    String key=database.getReference().push().getKey();
                                    DatabaseReference myRefstudent = database.getReference("Projects")
                                            .child(key);


                                    AddProjectmodel addProjectmodel=new AddProjectmodel(enterProjectTitle,enterProjectdescription,
                                            batch,year,category,list);

                                    myRefstudent.setValue(addProjectmodel).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("FAILURE","FAILURE"+e.getMessage().toString());
                                        }
                                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getActivity(), "Projected Added", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }else{
                                    Toast.makeText(getActivity(), "Add member details", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }


                    }else{
                        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });
    }

    private void saveList(ArrayList<AddMemberModel> addMemberModelArrayList, String name, String mail, String sid) {
        AddMemberModel addMemberModel=new AddMemberModel(name,mail,sid);
        addMemberModelArrayList.add(addMemberModel);

    }


}