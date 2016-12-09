package com.example.gualbertoscolari.grupp3.Logic;

import android.content.Context;
import android.util.Log;

import com.example.gualbertoscolari.grupp3.Activity.MainGameActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Standard frågor profiler och kategorier ska skapas upp i Gamelogic vid anrop och skickar en lista
//med dessa till MainGameActivity och skriver ut dom.
//Klassen sköter logiken för varje spelrunda.
//En timer metod som sköter värdet på poängen och avslutar rundan.
//
public class GameLogic extends MainGameActivity {

    private DbHelper db;
    private String category;
    private List<Question> tenQuestions;
    private Profile p1;
    private Profile p2;
    private Profile currentPlayer;
    private int numberOfAnsweredQ = 0;
    private int numberOfPlayers;

    public GameLogic(String profile, String category, int players, Context context) {

        p1 = new Profile(profile, 0);
        numberOfPlayers = players;
        this.category = category;
        db = new DbHelper(context);
        getQuestions();
        currentPlayer = p1;
        db.close();
    }

    public GameLogic(String profile1, String profile2, String category, int players,  Context context) {

        p1 = new Profile(profile1, 0);
        p2 = new Profile(profile2, 0);
        numberOfPlayers = players;
        currentPlayer = p1;
        this.category = category;
        db = new DbHelper(context);
        getQuestions();
        db.close();
    }



    public void getQuestions() {
        tenQuestions = db.getAllQuestions(category);
        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Question temp = tenQuestions.get(i);
            answerList.add(temp.getOPTA());
            answerList.add(temp.getOPTB());
            answerList.add(temp.getOPTC());
            answerList.add(temp.getOPTD());

            Collections.shuffle(answerList);

            tenQuestions.get(i).setOPTA(answerList.get(0));
            tenQuestions.get(i).setOPTB(answerList.get(1));
            tenQuestions.get(i).setOPTC(answerList.get(2));
            tenQuestions.get(i).setOPTD(answerList.get(3));

            answerList.clear();
        }
    }

    public boolean checkCorrectAnswer(String option){


        return option.equals(tenQuestions.get(numberOfAnsweredQ).getANSWER());



    }

    public Question getQuestion(){
        return tenQuestions.get(numberOfAnsweredQ);
    }

    public void increaseScore(int score){
        currentPlayer.setScore(currentPlayer.getScore() + score);
        Log.d("current player score ", "" + currentPlayer.getScore());
    }

    public void changePlayer(){

        if (currentPlayer == p1 && numberOfPlayers == 2) {
            p1 = currentPlayer;
            currentPlayer = p2;
        }else if(currentPlayer == p2 && numberOfPlayers == 2){
            p2 = currentPlayer;
            currentPlayer = p1;
        }
        else {
            p1 = currentPlayer;
        }
    }

    public int getNumberOfAnsweredQ(){
        return numberOfAnsweredQ;
    }

    public void increaseNrOfAnsweredQuestion(){
        if (!(numberOfPlayers == 2 && currentPlayer == p1)) {
            numberOfAnsweredQ++;
        }
    }

    public Profile getCurrentPlayer(){
        return currentPlayer;
    }

    public Profile getP1(){
        return p1;
    }
    public Profile getP2(){
        return p2;
    }




/*
    public void updateHighscore(Profile profile1, Profile profile2){
        DbHelper db = new DbHelper(this);
        if(profile1.getScore() > db.getHighScoredata()) {
            db.updateHighScore(profile1.getName(), profile1.getScore(), category);
        }
        else if(profile2.getScore() > db.getHighScoredata()){
            db.updateHighScore(profile1.getName(), profile1.getScore(), category);
        }
    }
    */
}
