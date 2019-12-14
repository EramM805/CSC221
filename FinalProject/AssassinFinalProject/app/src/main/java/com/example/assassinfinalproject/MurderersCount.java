package com.example.assassinfinalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MurderersCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_murderers_count);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        final boolean hasNarrator = intent.getBooleanExtra("hasNarrator", false);
        final int listLength = intent.getIntExtra("listLength", 0);
        final String[] list= intent.getStringArrayExtra("list");
        final Spinner dropdown = findViewById(R.id.spinner);
        String[] items = getList(listLength);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Button doneMurderers =  findViewById(R.id.doneMurderers);
        doneMurderers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String murdererCount = dropdown.getItemAtPosition(dropdown.getSelectedItemPosition()).toString();
                int intMurdererCount = Integer.parseInt(murdererCount);
                getRoles(hasNarrator, list, intMurdererCount);

            }
        });
    }
    private String[] getList(int length){
        if(length > 7 && length <= 9){
            return new String[] { "1", "2" };
        }
        else{
            if(length > 7 && length < 15){
                return new String[] { "1", "2", "3" };
            }
            else{
                if(length >= 15){
                    return new String[] { "1", "2", "3" , "4"};
                }
            }
        }
        return  new String[] {"1"};
    }

    private void getRoles(boolean hasNarrator, String[] list, int intMurdererCount){
        HashMap<String, ArrayList<String>>  output = new HashMap<>();
        Intent intent;
        if(hasNarrator == false){
            output = notHasNarratorGetMap(list, intMurdererCount);
             intent = new Intent(this, NarratorAssignment.class);
        }
        else{
            output = hasNarratorGetMap(list,intMurdererCount);
            intent = new Intent(this, Roles.class);
        }
        intent.putExtra("HashMap", output);
        startActivity(intent);

    };

    private HashMap<String, ArrayList<String>> notHasNarratorGetMap(String[] list, int intMurdererCount){
        HashMap<String, ArrayList<String>>  output = new HashMap<>();
        SecureRandom rand = new SecureRandom();
        int count = intMurdererCount + 2;
        String[] roles = {"Narrator", "Doctor", "Murderer"};
        HashSet<Integer> randomNumbers = new HashSet<Integer>();
        while(count != 0){
            int randomList = rand.nextInt(list.length);
            if(!randomNumbers.contains(randomList)){
                if(!output.containsKey("Narrator")){
                    ArrayList<String> insert = new ArrayList<>();
                    insert.add(list[randomList]);
                    output.put("Narrator", insert);
                    randomNumbers.add(randomList);
                    count--;
                }
                else{
                    if(!output.containsKey("Doctor")){
                        ArrayList<String> insert = new ArrayList<>();
                        insert.add(list[randomList]);
                        output.put("Doctor", insert);
                        randomNumbers.add(randomList);
                        count--;
                    }
                    else{
                        if(!output.containsKey("Murderer")){
                            ArrayList<String> insert = new ArrayList<>();
                            insert.add(list[randomList]);
                            output.put("Murderer", insert);
                            randomNumbers.add(randomList);
                            count--;
                        }
                        else{
                            output.get("Murderer").add(list[randomList]);
                            count--;
                        }
                    }
                }
            }

        }

        return output;
    };
    private HashMap<String, ArrayList<String>> hasNarratorGetMap(String[] list, int intMurdererCount){
        HashMap<String, ArrayList<String>>  output = new HashMap<>();
        SecureRandom rand = new SecureRandom();
        int count = intMurdererCount + 1;
        String[] roles = {"Doctor", "Murderer"};
        HashSet<Integer> randomNumbers = new HashSet<Integer>();
        while(count != 0) {
            int randomList = rand.nextInt(list.length);
            String key = "";
            if (!randomNumbers.contains(randomList)) {
                if (!output.containsKey("Doctor")) {
                    ArrayList<String> insert = new ArrayList<>();
                    insert.add(list[randomList]);
                    output.put("Doctor", insert);
                    randomNumbers.add(randomList);
                    count--;
                } else {
                    if (!output.containsKey("Murderer")) {
                        ArrayList<String> insert = new ArrayList<>();
                        insert.add(list[randomList]);
                        output.put("Murderer", insert);
                        randomNumbers.add(randomList);
                        count--;
                    } else {
                        output.get("Murderer").add(list[randomList]);
                        count--;
                    }
                }

            }
        }
        return output;
    };


}
