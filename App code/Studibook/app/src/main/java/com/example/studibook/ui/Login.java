package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studibook.AddProjectmodel;
import com.example.studibook.R;

public class Login extends AppCompatActivity {

    Button login;
    EditText _id_enter_username,_id_enter_paswword;
    Boolean edit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _id_enter_paswword=findViewById(R.id._id_enter_paswword);
        _id_enter_username=findViewById(R.id._id_enter_username);
        login=findViewById(R.id.login);

        try {
            String fromEdit=getIntent().getStringExtra("EDIT");
            if(fromEdit.equalsIgnoreCase("TRUE")){
                edit=true;
            }else{
                edit=false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=_id_enter_username.getText().toString();
                String password=_id_enter_paswword.getText().toString();
               // if(username.equals("Instructor") && password.equals("nwmissouri"))
                if(username.equals("Instructor") && password.equals("nwmissouri"))
                {
                    if (edit){
                        AddProjectmodel addProjectmodel= (AddProjectmodel) getIntent().getSerializableExtra("PROJECTDETAILS");

                        Intent intent=new Intent(Login.this,EditProjectActivtiy.class);
                        intent.putExtra("PROJECTDETAILS", addProjectmodel);
                        startActivity(intent);
                    }else{
                        finish();
                    }

                }
                else
                {
                    Toast.makeText(Login.this, "Please enter correct username/password", Toast.LENGTH_LONG).show();
                }
                //         finish();




                //        Intent intent=new Intent(Login.this,EnterProjectDetails.class);
                //      startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}