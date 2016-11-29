package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        DbHelper db = new DbHelper(this);
        List<String> allHighscores = db.getHighScoredata();
        hsGridV = (GridView) findViewById(R.id.hs_gridv);

        gridAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allHighscores);

        hsGridV.setAdapter(gridAdapter);

    }

    public void displayCategoriesInSpinner() {
        //Hämtar alla kategorier från DBhelper och lägger till dom i spinner.
    }

    public void displayHighScore() { // Tar in vald kategori från spinner som argument.
        //Hämtar sorterad high score-lista från DBhelper och skriver ut den i ListViev
    }
}
