package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class GameSettingsActivity extends AppCompatActivity {
    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<String> profile = new ArrayList<>();
    private int players = 0;
    private ArrayAdapter<String> chosenCategory;
    private ArrayAdapter<String> chosenProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        final CheckBox c1 = (CheckBox) findViewById(R.id.one_player_cb);
        final CheckBox c2 = (CheckBox) findViewById(R.id.two_player_cb);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c2.setChecked(false);
                c1.setChecked(b);
                players = 1;
            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c1.setChecked(false);
                c2.setChecked(b);
                players = 2;


            }
        });

        category.add("Sport");
        category.add("Nature");
        category.add("Culture");
        category.add("All");
        Spinner dropdownCategory = (Spinner) findViewById(R.id.category_spinner);
        chosenCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);


        profile.add("Dervis");
        profile.add("Fredrik");
        profile.add("Gualberto");
        profile.add("Simon");
        Spinner dropdownProfile = (Spinner) findViewById(R.id.profile_spinner);
        chosenProfile = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profile);
        dropdownProfile.setAdapter(chosenProfile);
    }

    public void goToMainGame(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);
        startActivity(playIntent);
    }

    public void goToCreateQuestion(View view) {
        Intent createQuestionIntent = new Intent(this, CreateQuestionActivity.class);
        startActivity(createQuestionIntent);
    }

    public void goToCreateCategory(View view) {
            Intent createCategoryIntent = new Intent(this, CreateCategoryActivity.class);
            startActivity(createCategoryIntent);
    }

    public void goToCreateProfile(View view) {
        Intent createProfileIntent = new Intent(this, CreateProfileActivity.class);
        startActivity(createProfileIntent);
    }
}
