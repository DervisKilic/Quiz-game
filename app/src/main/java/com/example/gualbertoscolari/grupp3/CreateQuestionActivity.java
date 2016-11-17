package com.example.gualbertoscolari.grupp3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateQuestionActivity extends AppCompatActivity {

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
    private EditText inputcorrectAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);

        inputQuestion = (EditText) findViewById(R.id.question);
        inputOpta = (EditText) findViewById(R.id.answer1);
        inputOptb = (EditText) findViewById(R.id.answer2);
        inputOptc = (EditText) findViewById(R.id.answer3);
        inputOptd = (EditText) findViewById(R.id.answer4);
        inputcorrectAnswer = (EditText) findViewById(R.id.correct_a);
    }

    public void Onclixk(){
        Question q = new Question(QUESTION,OPTA,OPTB, OPTC, OPTD, CAT, ANSWER);
        DbHelper.addQuestion();
    }
}
