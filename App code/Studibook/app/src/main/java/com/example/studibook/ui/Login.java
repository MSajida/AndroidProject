package com.example.studibook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studibook.R;

public class Login extends AppCompatActivity {

    Button login;
    EditText _id_enter_username,_id_enter_paswword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _id_enter_paswword=findViewById(R.id._id_enter_paswword);
        _id_enter_username=findViewById(R.id._id_enter_username);
        login=findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=_id_enter_username.getText().toString();
                String password=_id_enter_paswword.getText().toString();
                
                if(username.equals("Instructor") && password.equals("nwmissouri"))
                {
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this, "Please enter correct username/password", Toast.LENGTH_LONG).show();
                }

                //        Intent intent=new Intent(Login.this,EnterProjectDetails.class);
                //      startActivity(intent);
            }
        });
    }
}