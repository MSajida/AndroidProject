package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.studibook.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EnterProjectDetails extends AppCompatActivity {


    String[] batchList = new String[]{"Fall", "Spring", "Summer"};
    String[] yearList = new String[]{"2022", "2021", "2020"};
    String[] categoryList = new String[]{"Food", "Travel", "Environment", "Medical", "Miscellaneous"};

    AutoCompleteTextView batchView, yearView, categoryView;
    TextInputEditText id_enterTitle, id_description, id_entername, id_enterSid, id_enterEmail, id_entergithub;
    Button id_continue, id_addmember, id_back;
    ArrayList<AddMemberModel> addMemberModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_project_details);
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


        setSpinnerAdapterValues();

        id_addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = id_entername.getText().toString().trim();
                String mail = id_enterEmail.getText().toString().trim();
                String sid = id_enterSid.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(EnterProjectDetails.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sid)) {
                    Toast.makeText(EnterProjectDetails.this, "Enter SID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(EnterProjectDetails.this, "Enter Mail", Toast.LENGTH_SHORT).show();
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

    }

    private void saveList(ArrayList<AddMemberModel> addMemberModelArrayList, String name, String mail, String sid) {
        AddMemberModel addMemberModel = new AddMemberModel(name, mail, sid);
        addMemberModelArrayList.add(addMemberModel);


    }

    private void setSpinnerAdapterValues() {
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

}