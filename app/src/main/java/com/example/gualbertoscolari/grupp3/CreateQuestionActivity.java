package com.example.gualbertoscolari.grupp3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {

    ArrayList<String> category = new ArrayList<>();
    ArrayAdapter<String> chosenCategory;



    private int ID;

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
        inputQuestion = (EditText) findViewById(R.id.question_tv);
        String question = inputQuestion.getText().toString();

        inputQuestion.setText("");
        inputQuestion.setHint("Enter your question:");

        inputOpta = (EditText) findViewById(R.id.opt_a);
        String opta = inputOpta.getText().toString();

        inputOpta.setText("");
        inputOpta.setHint("Option: A");

        inputOptb = (EditText) findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        inputOptb.setText("");
        inputOptb.setHint("Option: B");

        inputOptc = (EditText) findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        inputOptc.setText("");
        inputOptc.setHint("Option: C");

        inputOptd = (EditText) findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        inputOptd.setText("");
        inputOptd.setHint("Option: D");

        chosenCat = (Spinner) findViewById(R.id.spinner_create_question);
        String cat = chosenCat.toString();

        inputcorrectAnswer = (EditText) findViewById(R.id.right_answer);
        String correctAnswer = inputcorrectAnswer.getText().toString();

        inputcorrectAnswer.setText("");
        inputcorrectAnswer.setHint("Correct answer:");

        Toast.makeText(this, "You added a new question", Toast.LENGTH_SHORT).show();


        Question q = new Question(question,opta,optb, optc, optd, cat, correctAnswer);
        DbHelper db = new DbHelper(getApplicationContext());

        db.addQuestion(q);
    }




}
