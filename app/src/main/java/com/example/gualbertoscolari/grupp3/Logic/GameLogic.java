package com.example.gualbertoscolari.grupp3.Logic;

import android.content.Context;

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

    /**
     * Constructor of the class. Used for one player.
     *
     * @param profile
     * @param category
     * @param players
     * @param context
     */

    public GameLogic(String profile, String category, int players, Context context) {

        p1 = new Profile(profile, 0);
        numberOfPlayers = players;
        this.category = category;
        db = new DbHelper(context);
        getQuestions();
        currentPlayer = p1;
        db.close();
    }

    /**
     * Constructor of the class. Used for two players.
     *
     * @param profile1
     * @param profile2
     * @param category
     * @param players
     * @param context
     */

    public GameLogic(String profile1, String profile2, String category, int players, Context context) {

        p1 = new Profile(profile1, 0);
        p2 = new Profile(profile2, 0);
        numberOfPlayers = players;
        currentPlayer = p1;
        this.category = category;
        db = new DbHelper(context);
        getQuestions();
        db.close();
    }

    /**
     * Method for getting 10 questions from the corresponding category set in constructor.
     * Sets the 10 questions to tenQuestions List<>
     */

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
            db.close();
        }
    }

    /**
     * Method for checking if a String is the same as currentQuestion.
     *
     * @param option
     * @return
     */
    public boolean checkCorrectAnswer(String option) {


        return option.equals(tenQuestions.get(numberOfAnsweredQ).getANSWER());

    }

    /**
     * Method for getting current question in tenQuestion List<>.
     * Question changes depending on numberOfAnsweredQ.
     *
     * @return
     */
    public Question getQuestion() {
        return tenQuestions.get(numberOfAnsweredQ);
    }

    public void increaseScore(int score) {
        currentPlayer.setScore(currentPlayer.getScore() + score);
    }

    /**
     * Method for changing player.
     * If only one player is currently playing sets currentPlayer to player one.
     * If two players are playing and currentPlayer is player one set currentPlayer to
     * player two.
     * If two players are playing and currentPlayer is player two set currentPlayer to
     * player one.
     */
    public void changePlayer() {

        if (currentPlayer == p1 && numberOfPlayers == 2) {
            p1 = currentPlayer;
            currentPlayer = p2;
        } else if (currentPlayer == p2 && numberOfPlayers == 2) {
            p2 = currentPlayer;
            currentPlayer = p1;
        } else {
            p1 = currentPlayer;
        }
    }

    /**
     * Method for getting numberOfAnsweredQ.
     *
     * @return
     */
    public int getNumberOfAnsweredQ() {
        return numberOfAnsweredQ;
    }

    /**
     * Method for increasing numberOfAnsweredQ.
     * If one player is playing the method increases numberOfAnsweredQ.
     * If two players are playing the method increases numberOfAnsweredQ
     * when player 2 is currentPlaying.
     */
    public void increaseNrOfAnsweredQuestion() {
        if (!(numberOfPlayers == 2 && currentPlayer == p1)) {
            numberOfAnsweredQ++;
        }
    }

    /**
     * Method for getting the currentPlayer.
     *
     * @return
     */
    public Profile getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method for getting player one's Profile instance.
     *
     * @return
     */
    public Profile getP1() {
        return p1;
    }


    /**
     * Method for getting player two's Profile instance.
     *
     * @return
     */
    public Profile getP2() {
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
