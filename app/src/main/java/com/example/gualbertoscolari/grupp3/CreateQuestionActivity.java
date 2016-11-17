package com.example.gualbertoscolari.grupp3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    ArrayList<String> category = new ArrayList<>();
    ArrayAdapter<String> chosenCategory;



    private int ID;

    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String CAT;
    private String ANSWER;

    private EditText inputQuestion;
    private EditText inputOpta;
    private EditText inputOptb;
    private EditText inputOptc;
    private EditText inputOptd;
    private Spinner chosenCat;
    private EditText inputcorrectAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        category.add("Sport");
        category.add("Nature");
        category.add("Culture");
        category.add("All");
        Spinner dropdownCategory = (Spinner) findViewById(R.id.spinner_create_question);
        chosenCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);
    }

    public void saveQuestion(View v){
        inputQuestion = (EditText) findViewById(R.id.question);
        String question = inputQuestion.getText().toString();

        inputOpta = (EditText) findViewById(R.id.opt_a);
        String opta = inputOpta.getText().toString();

        inputOptb = (EditText) findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        inputOptc = (EditText) findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        inputOptd = (EditText) findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        chosenCat = (Spinner) findViewById(R.id.spinner_create_question);
        String cat = chosenCat.toString();

        inputcorrectAnswer = (EditText) findViewById(R.id.right_answer);
        String correctAnswer = inputcorrectAnswer.getText().toString();


        Question q = new Question(question,opta,optb, optc, optd, cat, correctAnswer);
        DbHelper db = new DbHelper(getApplicationContext());

        db.addQuestion(q);
    }




}
