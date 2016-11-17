package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CreateCategoryActivity extends AppCompatActivity {

    String cat;
    EditText category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

    }

    public void goToGameSettings(View v){
        Intent gameSettings = new Intent(this, GameSettingsActivity.class);
        startActivity(gameSettings);
    }

    public void addCategory(View v){
        category = (EditText) findViewById(R.id.question);
        cat = category.getText().toString();

        DbHelper db = new DbHelper(getApplicationContext());

        db.addCategory(cat);

    }

    public String getCategory() {
        return cat;
    }
}
