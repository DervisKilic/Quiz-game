package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//Ska låta användaren lista highscoren efter specifik kategori eller alla.
//Spinner funktion skall finnas för att välja mellan kategorierna.
//
public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
    }

    public void displayCategoriesInSpinner(){
        //Hämtar alla kategorier från DBhelper och lägger till dom i spinner.
    }

    public void displayHighScore(){ // Tar in vald kategori från spinner som argument.
        //Hämtar sorterad high score-lista från DBhelper och skriver ut den i ListViev
    }
}
