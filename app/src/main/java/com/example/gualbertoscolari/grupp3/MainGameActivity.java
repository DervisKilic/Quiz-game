package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainGameActivity extends AppCompatActivity {

    TextView questiontv;
    Button optABtn;
    Button optBBtn;
    Button optCBtn;
    Button optDBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
    }

    public void displayQuestion(GameLogic) {
        //Hämtar fråga från GameLogic och skriver ut den i TextView:n
        //och skriver ut svaren på knapparna.

        questiontv = (TextView) findViewById(R.id.question_tv);
        // questiontv.settext() = information ifrån gamelogic/dbhelper

        optABtn = (Button) findViewById(R.id.answer_btn_a);
        optABtn.setText(/*information ifrån GameLogic/DBhelper*/);

        optBBtn = (Button) findViewById(R.id.answer_btn_b);
        optBBtn.setText(/*information ifrån GameLogic/DBhelper*/);

        optCBtn = (Button) findViewById(R.id.answer_btn_c);
        optCBtn.setText(/*information ifrån GameLogic/DBhelper*/);

        optDBtn = (Button) findViewById(R.id.answer_btn_d);
        optDBtn.setText(/*information ifrån GameLogic/DBhelper*/);
    }

    public void checkCorrectAnswer(String optString) {
        // Ska användas OnClick på alla knappar när användaren gissar.
        // Ska kolla om den intrykta knappens text är lika med frågans correctAnswer.

        if (optString.equals(questiontv.getText().toString()) ) {
            questiontv.setText("Hurra du svarade rätt på den här frågan");
        } else {
            questiontv.setText("Du svarade fel , du är dum");
        }
    }

    public void goToResult() {
        // Går vidare till result activity:n
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
