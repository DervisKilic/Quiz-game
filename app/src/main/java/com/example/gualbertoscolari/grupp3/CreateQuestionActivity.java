package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;
//Skapar upp en fråga med 4 alternativ och 1 rätt svar.
//Man väljer även vilken kategori frågan skall läggas til i som hämtas från databasen.
//Toasters och if satser för felhantering.

public class CreateQuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        DbHelper db = new DbHelper(this);
        List<String> category = db.getAllCatagories();
        Spinner dropdownCategory = (Spinner) findViewById(R.id.spinner_create_question);
        ArrayAdapter<String> chosenCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);
    }

    /**
     * Adds the input question parameters to database, SQLite, if conditions are met.
     * @param v
     */
    public void saveQuestion(View v){
        EditText inputQuestion = (EditText) findViewById(R.id.question_tv);
        String question = inputQuestion.getText().toString();

        EditText inputcorrectAnswer = (EditText) findViewById(R.id.opt_a);
        String correctAnswer = inputcorrectAnswer.getText().toString();

        EditText inputOptb = (EditText) findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        EditText inputOptc = (EditText) findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        EditText inputOptd = (EditText) findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        Spinner chosenCat = (Spinner) findViewById(R.id.spinner_create_question);
        String cat = chosenCat.getSelectedItem().toString();

        if (!question.matches(("^[a-zåäöA-ZÅÄÖ -? ]{3,150}$"))){
            Toast.makeText(this, "Max 150 letters or at least 3", Toast.LENGTH_SHORT).show();
            inputQuestion.setText("");
            inputQuestion.setHint(R.string.enter_q_hint);

        } else if (!correctAnswer.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputcorrectAnswer.setText("");
            inputcorrectAnswer.setHint(R.string.correct_answer_et);

        } else if (!optb.matches("^[a-zåäöA-ZÅÄÖ ]{1,20}$")){
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptb.setText("");
            inputOptb.setHint(R.string.enter_opt_b_hint);

        } else if (!optc.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptc.setText("");
            inputOptc.setHint(R.string.enter_opt_c_hint);

        } else if (!optd.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptd.setText("");
            inputOptd.setHint(R.string.enter_opt_d_hint);

        } else {
            Toast.makeText(this, "You added a new question", Toast.LENGTH_SHORT).show();
            Question q = new Question(question, correctAnswer, optb, optc, optd, cat, correctAnswer);
            DbHelper db = new DbHelper(getApplicationContext());
            db.addQuestion(q);
            db.close();
        }
    }

    /**
     *
     * @param view     takes the player back to game settings
     */
    public void backToMain(View view){
        Intent GameStettingsActivity = new Intent(this, GameSettingsActivity.class);
        startActivity(GameStettingsActivity);
        finish();
    }
}
