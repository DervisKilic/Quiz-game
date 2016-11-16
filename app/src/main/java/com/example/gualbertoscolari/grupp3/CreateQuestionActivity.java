package com.example.gualbertoscolari.grupp3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.R.attr.category;


public class CreateQuestionActivity extends AppCompatActivity {
    private ArrayList<String> category = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        category.add("Choose your category");
        category.add("Sport");
        category.add("Nature");
        category.add("Culture");
        category.add("All");
        Spinner dropdown = (Spinner) findViewById(R.id.spinner_create_question);
        ArrayAdapter<String> chosenCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdown.setAdapter(chosenCategory);

    }

}
