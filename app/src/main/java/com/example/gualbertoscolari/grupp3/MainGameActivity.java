package com.example.gualbertoscolari.grupp3;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.gualbertoscolari.grupp3.ResultActivity.CORRECT_ANS_P1;
import static com.example.gualbertoscolari.grupp3.ResultActivity.CORRECT_ANS_P2;
import static com.example.gualbertoscolari.grupp3.ResultActivity.FIRST_PROFILE;
import static com.example.gualbertoscolari.grupp3.ResultActivity.SCORE_PLAYER1;
import static com.example.gualbertoscolari.grupp3.ResultActivity.SCORE_PLAYER2;
import static com.example.gualbertoscolari.grupp3.ResultActivity.SECOND_PROFILE;
import static com.example.gualbertoscolari.grupp3.ResultActivity.TIME_PLAYED_PLAYER1;
import static com.example.gualbertoscolari.grupp3.ResultActivity.TIME_PLAYED_PLAYER2;

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
    private int correctAnsP1 = 0;
    private int correctAnsP2 = 0;
    private String smsQ;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String phoneNr;

    private GameLogic g1;
    private TextView qAnswered;

    private ImageView questionFrame;
    private TextView playerName;
    private TextView questiontv;

    private String chosenCat;
    private Button optABtn;
    private Button optBBtn;
    private Button optCBtn;
    private Button optDBtn;

    private TextView cat;
    private TextView timerTV;
    private CountDownTimer timer;
    private ProgressBar progressbar;
    private int scoreValue;
    private int currentTime = 0;
    private int currentTime2 = 0;
    private final Handler handler = new Handler();
    private int timePlayed = 0;
    private int timePlayed2 = 0;
    private boolean quit;

    private int gameRound = 1;

    private MediaPlayer mp;
    private MediaPlayer mp2;
    private MediaPlayer mp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Bundle extras = getIntent().getExtras();
        chosenCat = extras.getString(CATEGORY);
        p1Name = extras.getString(FIRSTPROFILE);
        p2Name = extras.getString(SECONDPROFILE);
        numberOfPlayers = extras.getInt(String.valueOf(PLAYERS));
        cat = (TextView) findViewById(R.id.chosen_category);
        timerTV = (TextView) findViewById(R.id.timer_tv);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setScaleY(4f);
        mp = MediaPlayer.create(this, R.raw.fail);
        mp2 = MediaPlayer.create(this, R.raw.correct_answer);
        mp3 = MediaPlayer.create(this, R.raw.clock);

        if (numberOfPlayers == 1) {
            g1 = new GameLogic(p1Name, chosenCat, numberOfPlayers, this);
        } else {
            g1 = new GameLogic(p1Name, p2Name, chosenCat, numberOfPlayers, this);
        }

        playerName = (TextView) findViewById(R.id.profile_name);
        questiontv = (TextView) findViewById(R.id.question_tv);
        optABtn = (Button) findViewById(R.id.answer_btn_a);
        optBBtn = (Button) findViewById(R.id.answer_btn_b);
        optCBtn = (Button) findViewById(R.id.answer_btn_c);
        optDBtn = (Button) findViewById(R.id.answer_btn_d);
        optABtn.setVisibility(View.GONE);
        optBBtn.setVisibility(View.GONE);
        optCBtn.setVisibility(View.GONE);
        optDBtn.setVisibility(View.GONE);
        questionFrame = (ImageView) findViewById(R.id.question_frame);

        loadQuestionFrame();
        getReadyDialog();
    }

    public void onButtonGuess(String optString) {
        // Ska användas OnClick på alla knappar när användaren gissar.
        // Ska kolla om den intrykta knappens text är lika med frågans correctAnswer.
        timer.cancel();
        currentTime += timePlayed;
        currentTime2 += timePlayed2;
        Log.d("Current time", ""+ currentTime);

        Log.d("Nummer av frågs", "" + g1.getNumberOfAnsweredQ());

        if (g1.checkCorrectAnswer(optString)) {
            //Ifall man svarar rätt händer detta
            g1.increaseScore(scoreValue);

            if(g1.getCurrentPlayer() == g1.getP1()){
                correctAnsP1++;
            }else {
                correctAnsP2++;
            }
        }
        g1.increaseNrOfAnsweredQuestion();
        g1.changePlayer();

        if (g1.getNumberOfAnsweredQ() == 2) {
            //Du har svarat på alla frågor , du tas till resultskärmen.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToResult();
                    finish();
                }
            }, 1000); // 1000 milliseconds = 1 second
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (numberOfPlayers == 2) {
                        resetQuestion();
                        AlertDialog diabox = AskOption();
                        diabox.show();
                    } else {
                        displayQuestion();
                        if(!quit) {
                            resetTimer();
                        }
                    }
                }
            }, 1000); // 1000 milliseconds = 1 second
        }
    }

    public void resetTimer() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                timer = new CountDownTimer(10000, 10) {
                    public void onTick(long millisUntilFinished) {
                        timerTV.setText("Points " + (millisUntilFinished / 100));
                        scoreValue = (int) (millisUntilFinished / 100);
                        timePlayed =  10 - ((int) (millisUntilFinished / 1000));
                        if(g1.getCurrentPlayer() == g1.getP2()){
                            timePlayed2 =  10 - ((int) (millisUntilFinished / 1000));
                        }


                        int progress = (int) (millisUntilFinished / 100);
                        progressbar.setProgress(progress);
                    }


                    public void onFinish() {

                        progressbar.setProgress(0);
                        timerTV.setText("0");
                        onButtonGuess("");
                        Log.d("I timer, on finished", "Hej");
                    }
                }.start();
            }
        }, 1000); // 1000 milliseconds = 1 second

    }

    public void goToResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(CATEGORY, chosenCat);
        intent.putExtra(PLAYERS, String.valueOf(numberOfPlayers));
        intent.putExtra(TIME_PLAYED_PLAYER1, currentTime);
        Log.d("Main game", "time " + currentTime);
        intent.putExtra(FIRST_PROFILE, g1.getP1().getName());
        intent.putExtra(SCORE_PLAYER1, String.valueOf(g1.getP1().getScore()));
        intent.putExtra(CORRECT_ANS_P1, correctAnsP1);
        updateHighscore(g1.getP1());

        if (numberOfPlayers == 2) {
            intent.putExtra(SECOND_PROFILE, g1.getP2().getName());
            intent.putExtra(SCORE_PLAYER2, String.valueOf(g1.getP2().getScore()));
            intent.putExtra(TIME_PLAYED_PLAYER2, currentTime2);
            intent.putExtra(CORRECT_ANS_P2, correctAnsP2);
            updateHighscore(g1.getP2());
        }
        startActivity(intent);
        finish();
    }

    public void btnPressed(View view){
        String buttonText = ((Button)view).getText().toString();
        Button button = (Button) findViewById(view.getId());
        if (g1.checkCorrectAnswer(buttonText)) {
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.correctanswerbutton));
            mp2.start();
            mp3.stop();
        } else {
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.wronganswerbutton));
            mp.start();
            mp3.stop();
        }

        optABtn.setEnabled(false);
        optBBtn.setEnabled(false);
        optCBtn.setEnabled(false);
        optDBtn.setEnabled(false);
        onButtonGuess(buttonText);
    }

    public void resetQuestion() {
        playerName.setText("");
        questiontv.setText("");
        cat.setText("");
        optABtn.setText("");
        optBBtn.setText("");
        optCBtn.setText("");
        optDBtn.setText("");
        optABtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
        optBBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
        optCBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
        optDBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));

    }

    public void loadQuestionFrame() {

        switch (chosenCat) {
            case "Sport":
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.sportruta));
                break;

            case "Samhälle":
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.samhallruta));
                break;

            case "Kultur/Nöje":
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.kulturnojeruta));
                break;

            case "Historia":
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.historiaruta));
                break;

            case "Natur":
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.naturruta));
                break;

            default:
                questionFrame.setBackgroundDrawable(getResources().getDrawable(R.drawable.blandat));
        }
    }

    public void setRound() {

        if (numberOfPlayers == 1) {
            qAnswered = (TextView) findViewById(R.id.questions_answered_tv);
            qAnswered.setText("Q " + gameRound + "/10");
            gameRound++;

        } else if (numberOfPlayers == 2) {
            qAnswered = (TextView) findViewById(R.id.questions_answered_tv);
            qAnswered.setText("Q " + gameRound + "/20");
            gameRound++;
        }

    }

    public void updateHighscore(Profile profile1) {
        DbHelper db = new DbHelper(this);
        db.updateHighScore(profile1, chosenCat);
    }

    @Override
    public void onBackPressed() {
        quit = true;
        Intent intent = new Intent(this, GameSettingsActivity.class);
        startActivity(intent);
        finish();
    }
    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)

                //set message, title, and icon
                .setTitle("Nästa spelare " + g1.getCurrentPlayer().getName())
                .setMessage("Tryck ok för att köra")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        displayQuestion();
                        if(!quit) {
                            resetTimer();
                        }
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }

    private AlertDialog getReadyDialog() {
        final AlertDialog alertDialog  = new AlertDialog.Builder(this).create();

        alertDialog.setTitle("Gör dig redo " + g1.getCurrentPlayer().getName());
        alertDialog.setMessage("4");
        alertDialog.show();

        new CountDownTimer(4000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage(""+ (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                alertDialog.dismiss();
                progressbar.setProgress(0);
                displayQuestion();
                if(!quit) {
                    resetTimer();
                }
            }
        }.start();
        return alertDialog;
    }

    public void displayQuestion() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                optABtn.setVisibility(View.VISIBLE);
                optBBtn.setVisibility(View.VISIBLE);
                optCBtn.setVisibility(View.VISIBLE);
                optDBtn.setVisibility(View.VISIBLE);
                optABtn.setEnabled(true);
                optBBtn.setEnabled(true);
                optCBtn.setEnabled(true);
                optDBtn.setEnabled(true);
                playerName.setText(g1.getCurrentPlayer().getName());
                questiontv.setText(g1.getQuestion().getQUESTION());
                cat.setText(chosenCat);
                //Randomize options
                optABtn.setText(g1.getQuestion().getOPTA());
                optBBtn.setText(g1.getQuestion().getOPTB());
                optCBtn.setText(g1.getQuestion().getOPTC());
                optDBtn.setText(g1.getQuestion().getOPTD());
                optABtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
                optBBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
                optCBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
                optDBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.standardcustombutton));
                setRound();
            }
        }, 1000);// 1000 milliseconds = 1 second
    }

    public void smsSend(View view) {

        questiontv.setText(g1.getQuestion().getQUESTION());
        smsQ = questiontv.getText().toString();
        optA = optABtn.getText().toString();
        optB= optBBtn.getText().toString();
        optC = optCBtn.getText().toString();
        optD = optDBtn.getText().toString();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Skriv in nummret");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(input);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phoneNr = input.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+46" + phoneNr, null, " Fråga: " + smsQ + " A : " + optA + " B : " + optB + " C : " + optC + " D : " + optD, null, null);

                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
