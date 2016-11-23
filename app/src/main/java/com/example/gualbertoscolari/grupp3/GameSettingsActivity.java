package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//Inställningar för spelomgång antal spelare, val av profil, val av kategori.
//Här kan man välja att skapa nya kategorier, profiler och frågor.
//Valen sparas ner i ett intent som skickas vidare till Maingame där det skapas upp en instans av
//GameLogic
public class GameSettingsActivity extends AppCompatActivity {
    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<String> profile = new ArrayList<>();
    private ArrayAdapter<String> chosenCategory;
    private ArrayAdapter<String> chosenProfileP1;
    private ArrayAdapter<String> chosenProfileP2;
    String p1;
    String p2;
    public static String cat;
    private CheckBox c1;
    private CheckBox c2;

    private int players;

    private Spinner profileSpinner1;
    private Spinner profileSpinner2;
    private TextView profile2Tv;
    private ImageView profileIv2;

    private List<Profile> profList;

    private Profile currentP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        c1 = (CheckBox) findViewById(R.id.one_player_cb);
        c2 = (CheckBox) findViewById(R.id.two_player_cb);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c2.setChecked(false);
                c1.setChecked(b);

                profileSpinner2.setVisibility(View.GONE);
                profile2Tv.setVisibility(View.GONE);
                profileIv2.setVisibility(View.GONE);

                players = 1;

            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                c1.setChecked(false);
                c2.setChecked(b);


                profileSpinner2.setVisibility(View.VISIBLE);
                profile2Tv.setVisibility(View.VISIBLE);
                profileIv2.setVisibility(View.VISIBLE);
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
        cat = chosenCategory.toString();

        profile.add("Dervis");
        profile.add("Fredrik");
        profile.add("Gualberto");
        profile.add("Simon");
        profileSpinner1 = (Spinner) findViewById(R.id.profile_spinner);
        profileSpinner2 = (Spinner) findViewById(R.id.profile_spinner2);
        profile2Tv = (TextView) findViewById(R.id.current_profile2_tv);
        profileIv2 = (ImageView) findViewById(R.id.profile_img_iv2);

        chosenProfileP1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profile);
        chosenProfileP2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profile);
        profileSpinner1.setAdapter(chosenProfileP1);
        profileSpinner2.setAdapter(chosenProfileP2);
        p1 = profileSpinner1.getAdapter().toString();
        p2 = profileSpinner2.getAdapter().toString();

        DbHelper db = new DbHelper(this);  // my profile bank class
        profList = db.getAllProfiles();  // this will fetch all quetonall questions

        for (int i = 0; i < profList.size(); i++ ){
            currentP = profList.get(i); // the current profile
            profile.add(currentP.getName());
        }
        profileSpinner2.setVisibility(View.GONE);
        profile2Tv.setVisibility(View.GONE);
        profileIv2.setVisibility(View.GONE);
    }

    public void goToMainGame(View view) {
        Intent playIntent = new Intent(this, MainGameActivity.class);

        playIntent.putExtra(GameLogic.CATEGORY, cat);
        playIntent.putExtra((GameLogic.PLAYERS), players);
        playIntent.putExtra(GameLogic.FIRSTPROFILE,p1);
        playIntent.putExtra(GameLogic.SECONDPROFILE,p2);
        startActivity(playIntent);
        finish();
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
        finish();
    }
}
