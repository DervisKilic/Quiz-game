package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.icu.util.ULocale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreateCategoryActivity extends AppCompatActivity {
    
    private EditText category;



    private String cat;





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
        category = (EditText) findViewById(R.id.categorie_name);
        cat = category.getText().toString();

        DbHelper db = new DbHelper(getApplicationContext());
        db.addCategorys(cat);

        category.setText("");
        Toast.makeText(this, "You added a new category", Toast.LENGTH_SHORT).show();
        category.setHint(this.getString(R.string.enter_category_name));

    }

    public String getCategory() {
        return cat;

    }
}

