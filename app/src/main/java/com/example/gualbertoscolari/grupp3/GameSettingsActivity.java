package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



//Inställningar för spelomgång antal spelare, val av profil, val av kategori.
//Här kan man välja att skapa nya kategorier, profiler och frågor.
//Valen sparas ner i ett intent som skickas vidare till Maingame där det skapas upp en instans av
//GameLogic
public class GameSettingsActivity extends AppCompatActivity {
    private List<String> category = new ArrayList<>();
    private ArrayList<String> profile = new ArrayList<>();
    private ArrayAdapter<String> chosenCategory;
    private ArrayAdapter<String> chosenProfileP1;
    private ArrayAdapter<String> chosenProfileP2;
    String p1;
    String p2;
    private String cat;
    private CheckBox c1;
    private CheckBox c2;

    private int players = 1;

    private Spinner profileSpinner1;
    private Spinner profileSpinner2;
    private TextView profile2Tv;
    private ImageView profileIv1;
    private ImageView profileIv2;
    private Spinner dropdownCategory;

    private List<Profile> profList;

    private Profile currentP;

    String p1name;
    String p2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);


        c1 = (CheckBox) findViewById(R.id.one_player_cb);
        c2 = (CheckBox) findViewById(R.id.two_player_cb);

        c1.setChecked(true);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(c1.isChecked() && c2.isChecked() || !c2.isChecked()){
                    c1.setChecked(true);
                    c2.setChecked(false);
                }

                profileSpinner2.setVisibility(View.GONE);
                profile2Tv.setVisibility(View.GONE);
                profileIv2.setVisibility(View.GONE);

                players = 1;
            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(!c1.isChecked() || c2.isChecked()){
                    c1.setChecked(false);
                    c2.setChecked(true);
                }

                profileSpinner2.setVisibility(View.VISIBLE);
                profile2Tv.setVisibility(View.VISIBLE);
                profileIv2.setVisibility(View.VISIBLE);
                players = 2;
            }
        });

        getStandardCategorys();
        getStandardProfiles();


        profileSpinner2.setVisibility(View.GONE);
        profile2Tv.setVisibility(View.GONE);
        profileIv2.setVisibility(View.GONE);
    }

    public void goToMainGame(View view) {
        DbHelper db = new DbHelper(this);

        cat = chosenCategory.getItem(dropdownCategory.getSelectedItemPosition());
        p1 = chosenProfileP1.getItem(profileSpinner1.getSelectedItemPosition());
        p2 = chosenProfileP2.getItem(profileSpinner2.getSelectedItemPosition());

        if(db.getAllQuestions(cat).size() == 10) {
            Intent playIntent = new Intent(this, MainGameActivity.class);
            playIntent.putExtra(MainGameActivity.CATEGORY, cat);
            playIntent.putExtra(MainGameActivity.PLAYERS, players);
            playIntent.putExtra(MainGameActivity.FIRSTPROFILE, p1);
            playIntent.putExtra(MainGameActivity.SECONDPROFILE, p2);
            startActivity(playIntent);

            finish();
        } else {
            Toast.makeText(this, "En kategori måste innehålla 10 frågor", Toast.LENGTH_SHORT).show();
        }

    }

    public void goToManageContent(View view) {
        startActivity(new Intent(this, ManageContentActivity.class));
    }


    public void goToCreateQuestion(View view) {
        Intent createQuestionIntent = new Intent(this, CreateQuestionActivity.class);
        startActivity(createQuestionIntent);
        finish();
    }

    public void goToCreateCategory(View view) {
        Intent createCategoryIntent = new Intent(this, CreateCategoryActivity.class);
        startActivity(createCategoryIntent);
        finish();
    }

    public void goToCreateProfile(View view) {
        Intent createProfileIntent = new Intent(this, CreateProfileActivity.class);
        startActivity(createProfileIntent);
        finish();
    }


    public void getStandardCategorys(){
        DbHelper db = new DbHelper(this);

        category = db.getAllCatagories();
        db.close();
        dropdownCategory = (Spinner) findViewById(R.id.category_spinner);
        chosenCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);
    }

    public void getStandardProfiles() {
        DbHelper db = new DbHelper(this);  // my profile bank class
        profList = db.getAllProfiles();  // this will fetch all quetonall questions
        db.close();
        for (int i = 0; i < profList.size(); i++) {
            currentP = profList.get(i); // the current profile
            profile.add(currentP.getName());
        }

        profileSpinner1 = (Spinner) findViewById(R.id.profile_spinner);
        profileSpinner2 = (Spinner) findViewById(R.id.profile_spinner2);
        profile2Tv = (TextView) findViewById(R.id.current_profile2_tv);
        profileIv1 = (ImageView) findViewById(R.id.profile_img_iv1);
        profileIv2 = (ImageView) findViewById(R.id.profile_img_iv2);

        chosenProfileP1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profile);
        chosenProfileP2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profile);
        profileSpinner1.setAdapter(chosenProfileP1);
        profileSpinner2.setAdapter(chosenProfileP2);
        p1 = profileSpinner1.getAdapter().toString();
        p2 = profileSpinner2.getAdapter().toString();

        profileSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                p1name = chosenProfileP1.getItem(profileSpinner1.getSelectedItemPosition());

                if(p1name.equals("Dervis")){
                    profileIv1.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
                }

                else if(p1name.equals("Fredrik")){
                    profileIv1.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
                }

                else if(p1name.equals("Gualberto")){
                    profileIv1.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
                }

                else if(p1name.equals("Simon")){
                    profileIv1.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
                }else{
                    profileIv1.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        profileSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                p2name = chosenProfileP2.getItem(profileSpinner2.getSelectedItemPosition());

                if(p2name.equals("Dervis")){
                    profileIv2.setImageDrawable(getResources().getDrawable(R.drawable.avatar1));
                }

                else if(p2name.equals("Fredrik")){
                    profileIv2.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
                }

                else if(p2name.equals("Gualberto")){
                    profileIv2.setImageDrawable(getResources().getDrawable(R.drawable.avatar3));
                }

                else if(p2name.equals("Simon")){
                    profileIv2.setImageDrawable(getResources().getDrawable(R.drawable.avatar4));
                }else{
                    profileIv2.setImageDrawable(getResources().getDrawable(R.drawable.avatar5));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
