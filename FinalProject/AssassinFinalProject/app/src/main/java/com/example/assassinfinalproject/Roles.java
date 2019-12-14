package com.example.assassinfinalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Roles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView murdererTextView = (TextView)findViewById(R.id.MurdererAssignment);
        Intent intent = getIntent();
        final HashMap<String, ArrayList<String>> roles = (HashMap<String, ArrayList<String>>) intent.getSerializableExtra("HashMap");
        String murderer = "Murderers: ";
        for (int i = 0; i < roles.get("Murderer").size(); i++) {
           if(i == 0){
               murderer += roles.get("Murderer").get(i);
           }
           else{
               murderer += "," + roles.get("Murderer").get(i);
           }

        }
        murdererTextView.setText(murderer);
        TextView doctorTextView = (TextView)findViewById(R.id.DoctorAssignment);
        String doctor = "Doctor: " + roles.get("Doctor").get(0);
        doctorTextView.setText(doctor);
    }

}
