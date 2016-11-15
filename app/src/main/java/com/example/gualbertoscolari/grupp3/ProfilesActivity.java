package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ProfilesActivity extends AppCompatActivity {

    private ArrayList<String> profiles = new ArrayList<>();
    private ArrayAdapter<String> chosenProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);


        profiles.add("Putin");
        profiles.add("Gual");
        profiles.add("Simon");
        profiles.add("Fredrik");
        profiles.add("Dervis");
        Spinner dropdown = (Spinner) findViewById(R.id.profile_spinner);
        chosenProfile= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profiles);
        dropdown.setAdapter(chosenProfile);

    }



}
