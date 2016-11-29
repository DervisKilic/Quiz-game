package com.example.gualbertoscolari.grupp3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//Skapar upp en fråga med 4 alternativ och 1 rätt svar.
//Man väljer även vilken kategori frågan skall läggas til i som hämtas från databasen.
//Toasters och if satser för felhantering.

public class CreateQuestionActivity extends AppCompatActivity {

    List<String> category = new ArrayList<>();
    ArrayAdapter<String> chosenCategory;

    private int ID;

    private EditText inputQuestion;
    private EditText inputOpta;
    private EditText inputOptb;
    private EditText inputOptc;
    private EditText inputOptd;
    private EditText inputcorrectAnswer;
    private Spinner chosenCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        DbHelper db = new DbHelper(this);
        category = db.getAllCatagories();
        Spinner dropdownCategory = (Spinner) findViewById(R.id.spinner_create_question);
        chosenCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);


    }

    public void saveQuestion(View v){
        inputQuestion = (EditText) findViewById(R.id.question_tv);
        String question = inputQuestion.getText().toString();

        inputcorrectAnswer = (EditText) findViewById(R.id.opt_a);
        String correctAnswer = inputcorrectAnswer.getText().toString();

        inputOptb = (EditText) findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        inputOptc = (EditText) findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        inputOptd = (EditText) findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        chosenCat = (Spinner) findViewById(R.id.spinner_create_question);
        String cat = chosenCat.getSelectedItem().toString();

        if (question.length() > 20 || question.length() < 1){
            Toast.makeText(this, "Max 150 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputQuestion.setText("");
            inputQuestion.setHint(R.string.enter_q_hint);

        } else if (correctAnswer.length() > 15 || correctAnswer.length() < 1){
            Toast.makeText(this, "Max 15 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputcorrectAnswer.setText("");
            inputcorrectAnswer.setHint(R.string.correct_answer);

        } else if (optb.length() > 15 || optb.length() < 1){
            Toast.makeText(this, "Max 15 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptb.setText("");
            inputOptb.setHint(R.string.enter_opt_b_hint);

        } else if (optc.length() > 15 || optc.length() < 1){
            Toast.makeText(this, "Max 15 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptc.setText("");
            inputOptc.setHint(R.string.enter_opt_c_hint);

        } else if (optd.length() > 15 || optd.length() < 1){
            Toast.makeText(this, "Max 15 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptd.setText("");
            inputOptd.setHint(R.string.enter_opt_d_hint);

        } else {
            Toast.makeText(this, "You added a new question", Toast.LENGTH_SHORT).show();
            Question q = new Question(question, correctAnswer, optb, optc, optd, cat, correctAnswer);
            DbHelper db = new DbHelper(getApplicationContext());
            db.addQuestion(q);
        }

    }

    public void backToMain(View view){
        Intent GameStettingsActivity = new Intent(this, GameSettingsActivity.class);
        startActivity(GameStettingsActivity);

    }

}
