package com.example.assassinfinalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class NarratorAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narrator_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)findViewById(R.id.NarratorAssignment);
        Intent intent = getIntent();
        final HashMap<String, ArrayList<String>> roles = (HashMap<String, ArrayList<String>>) intent.getSerializableExtra("HashMap");
        String output = roles.get("Narrator").get(0).toUpperCase() + " IS THE NARRATOR. PLEASE HAND " + roles.get("Narrator").get(0).toUpperCase() + " THE PHONE";
        textView.setText(output);
        Button seeRoles =  findViewById(R.id.seeRoles);
        seeRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newScreen(roles);

            }
        });

    }
    private void newScreen(HashMap<String, ArrayList<String>> roles){
        final Intent newScreen = new Intent(this, Roles.class);
        newScreen.putExtra("HashMap", roles);
        startActivity(newScreen);
    }

}
