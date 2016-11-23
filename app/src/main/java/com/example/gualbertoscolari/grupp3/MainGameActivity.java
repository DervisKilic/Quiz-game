package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

//Metoden skall skapa upp ett gamelogic objekt som innehåller 10 frågor.
//Skall visa upp 1 fråga och 4 svar. Skall visa en timer från gamelogic.
//
public class MainGameActivity extends AppCompatActivity {

    public final static String CATEGORY = "chosen category";
    public final static String PLAYERS = "number of players";
    public final static String FIRSTPROFILE = "name of the player 1";
    public final static String SECONDPROFILE = "name of the player 2";

    private int numberOfPlayers;
    private String p1Name;
    private String p2Name;

    private Profile p1;
    private Profile p2;
    private GameLogic g1;

    private String answer;
    private TextView questiontv;
    private String chosenCat;
    private Button optABtn;
    private Button optBBtn;
    private Button optCBtn;
    private Button optDBtn;
    private TextView cat;
    private TextView timerTV;
    Timer timer;

    private static final String TAG = "MAINGAME_ACTIVITY";

    private int numberOfAnsweredQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Bundle extras = getIntent().getExtras();

        chosenCat = extras.getString(CATEGORY);
        p1Name = extras.getString(FIRSTPROFILE);
        p2Name = extras.getString(SECONDPROFILE);
        numberOfPlayers = extras.getInt(String.valueOf(PLAYERS));

        p1 = new Profile(p1Name, 0);
        p2 = new Profile(p2Name, 0);

        cat = (TextView) findViewById(R.id.chosen_category);


        if(numberOfPlayers == 1){
            g1 = new GameLogic(p1, chosenCat, this);
        }else{

            g1 = new GameLogic(p1,p2, chosenCat, this);
        }

        questiontv = (TextView) findViewById(R.id.question_tv);
        optABtn = (Button) findViewById(R.id.answer_btn_a);
        optBBtn = (Button) findViewById(R.id.answer_btn_b);
        optCBtn = (Button) findViewById(R.id.answer_btn_c);
        optDBtn = (Button) findViewById(R.id.answer_btn_d);
        displayQuestion();



       /* new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerTV.setText("Seconds remaining : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTV.setText("Done!");
            }
        }.start();
        */

    }

    public void displayQuestion() {
        //Hämtar fråga från GameLogic och skriver ut den i TextView:n
        //och skriver ut svaren på knapparna.
        questiontv.setText(g1.getQuestions().get(numberOfAnsweredQ).getQUESTION());
        cat.setText(g1.getQuestions().get(numberOfAnsweredQ).getCATEGORY());
        optABtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTA());
        optBBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTB());
        optCBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTC());
        optDBtn.setText(g1.getQuestions().get(numberOfAnsweredQ).getOPTD());
    }

    public void checkCorrectAnswer(String optString) {
        // Ska användas OnClick på alla knappar när användaren gissar.
        // Ska kolla om den intrykta knappens text är lika med frågans correctAnswer.
        answer = g1.getQuestions().get(numberOfAnsweredQ).getANSWER();
        if (answer.equals(optString)) {
            //Ifall man svarar rätt händer detta
            Log.d("Svarstest", "Rätt");
            //Ifall man svarar rätt händer detta
            Log.d(TAG, "Answer gotten from database:  " + answer + " The string on the button :  " + optString + " The Question was answered correctly ");
            questiontv.setText("Hurra du svarade rätt på den här frågan");

        } else {
            //Ifall man svarar fel händer detta
            Log.d(TAG, "Answer: " + answer + "optstring:  " + optString + " The Question was answered wrongly");
            questiontv.setText("Du svarade fel , du är dum");

        }
        numberOfAnsweredQ++;
        if (numberOfAnsweredQ == 9) {
            //Du har svarat på alla frågor , du tas till resultskärmen.
            goToResult();

        }
        displayQuestion();
    }




    public void goToResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void btn_a_pressed(View view) {
        checkCorrectAnswer(optABtn.getText().toString());
    }

    public void btn_b_pressed(View view) {
        checkCorrectAnswer(optBBtn.getText().toString());
    }

    public void btn_c_pressed(View view) {
        checkCorrectAnswer(optCBtn.getText().toString());
    }

    public void btn_d_pressed(View view) {
        checkCorrectAnswer(optDBtn.getText().toString());
    }
}