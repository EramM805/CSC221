package com.example.assassinfinalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;

import android.view.View;
import android.widget.TextView;
import java.util.*;
import java.security.SecureRandom;
import org.w3c.dom.Text;

public class nameEnter extends AppCompatActivity {
    private boolean hasEmpty = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_enter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final boolean hasNarrator = intent.getBooleanExtra("hasNarrator", false);
        final TextView inputTV =  findViewById((R.id.players));
        Button done =  findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputTV.getText().toString();
                input = input.replaceAll("\\s+","");
                String[] list = input.split( "," );
                if(hasEmpty == false){
                    Set<String> names = new HashSet<String>(Arrays.asList(list));
                    if(names.contains("")){
                        inputTV.setError("Please do not enter empty names");
                    }
                    else{
                        if(list.length < 7){
                            inputTV.setError("Please enter at least 7 names");
                        }
                        else{
                            if(names.size() < list.length) {
                                inputTV.setError("Please do not enter duplicate names");
                            }
                            else{
                                openMurderersCount(hasNarrator, list.length, list);
                            }


                        }
                    }

                }

            }
        });
    }
    protected void openMurderersCount(boolean hasNarrator, int length, String[] list) {
        Intent intent = new Intent(this, MurderersCount.class);
        intent.putExtra("hasNarrator", hasNarrator);
        intent.putExtra("listLength", length);
        intent.putExtra("list", list);

        startActivity(intent);
    }

}
