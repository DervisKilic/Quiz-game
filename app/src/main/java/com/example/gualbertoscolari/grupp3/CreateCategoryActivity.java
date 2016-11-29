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
//Klassen är till för att skapa egna kategorier.
//Den tar emot en text sträng från användaren med valt kategori namn,
//och skickar in det till databasen.
//Metoden getCategory ska tas bort då vi hämtar kategorin från databasen.
//Toasters och if satser för felhantering.

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
        DbHelper db = new DbHelper(getApplicationContext());
        category = (EditText) findViewById(R.id.categorie_name);
        cat = category.getText().toString();

        if(cat.length() > 12 || cat.length() < 1){
            Toast.makeText(this, "Max 12 letters", Toast.LENGTH_SHORT).show();
            category.setText("");
            category.setHint(this.getString(R.string.enter_category_name));

        } else {
            Toast.makeText(this, "You added a new category", Toast.LENGTH_SHORT).show();
            category.setText("");
            category.setHint(this.getString(R.string.enter_category_name));
            db.addCategorys(cat);
            db.addPlaceholderHSCategory(cat);

        }
    }

    public String getCategory() {
        return cat;

    }
}

