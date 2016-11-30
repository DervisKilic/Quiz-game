package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

//Ska låta användaren lista highscoren efter specifik kategori eller alla.
//Spinner funktion skall finnas för att välja mellan kategorierna.
//
public class HighscoreActivity extends AppCompatActivity {

    GridView hsGridV;
    String profileName = "Simon";
    int highscore = 10;
    ArrayAdapter<String> gridAdapter;
    private Spinner dropdownCategory;
    private String cat;
    private ArrayAdapter<String> chosenCategory;
    private List<String> category = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);


        DbHelper db = new DbHelper(this);
        category = db.getAllCatagories();

        dropdownCategory = (Spinner) findViewById(R.id.spinner_highscore);
        chosenCategory = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);
        cat = chosenCategory.getItem(dropdownCategory.getSelectedItemPosition());
        db.close();

        displayHighScore();
    }

    public void displayCategoriesInSpinner() {
        //Hämtar alla kategorier från DBhelper och lägger till dom i spinner.
        DbHelper db = new DbHelper(this);
        List<String> allHighscores = db.getHighScoredata(cat);
        hsGridV = (GridView) findViewById(R.id.hs_gridv);
        gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allHighscores);
        hsGridV.setAdapter(gridAdapter);
        db.close();
    }

    public void displayHighScore() {
        // Tar in vald kategori från spinner som argument.
        //Hämtar sorterad high score-lista från DBhelper och skriver ut den i GridView

        dropdownCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                cat = chosenCategory.getItem(dropdownCategory.getSelectedItemPosition());
                displayCategoriesInSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
}
