package com.example.gualbertoscolari.grupp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gualbertoscolari.grupp3.Logic.DbHelper;
import com.example.gualbertoscolari.grupp3.R;

import java.util.List;

//Ska låta användaren lista highscoren efter specifik kategori eller alla.
//Spinner funktion skall finnas för att välja mellan kategorierna.
//
public class HighscoreActivity extends AppCompatActivity {

    private Spinner dropdownCategory;
    private String cat;
    private ArrayAdapter<String> chosenCategory;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        db = new DbHelper(this);
        List<String> category = db.getAllCatagories();

        dropdownCategory = (Spinner) findViewById(R.id.spinner_highscore);
        chosenCategory = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);
        cat = chosenCategory.getItem(dropdownCategory.getSelectedItemPosition());
        displayHighScore();
    }

    public void displayCategoriesInSpinner() {
        //Hämtar alla kategorier från DBhelper och lägger till dom i spinner.
        List<String> allHighscores = db.getHighScoredata(cat);
        if(allHighscores.size() == 0){
            Toast.makeText(this, "There are no highscores to display!", Toast.LENGTH_SHORT).show();
        }
        GridView hsGridV = (GridView) findViewById(R.id.hs_gridv);
        ArrayAdapter<String> gridAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allHighscores);
        hsGridV.setAdapter(gridAdapter);
    }

    public void displayHighScore() {
        // Tar in vald kategori från spinner som argument.
        //Hämtar sorterad high score-lista från DBhelper och skriver ut den i GridView
        dropdownCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                cat = chosenCategory.getItem(dropdownCategory.getSelectedItemPosition());

                displayCategoriesInSpinner();
                db.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
