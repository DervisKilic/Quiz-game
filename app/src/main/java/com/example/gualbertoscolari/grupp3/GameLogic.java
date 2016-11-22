package com.example.gualbertoscolari.grupp3;

import java.util.ArrayList;

//Standard frågor profiler och kategorier ska skapas upp i Gamelogic vid anrop och skickar en lista
//med dessa till MainGameActivity och skriver ut dom.
//Klassen sköter logiken för varje spelrunda.
//En timer metod som sköter värdet på poängen och avslutar rundan.
//
public class GameLogic extends MainGameActivity {

    private DbHelper db = new DbHelper(this);
    private int score = 0;
    private String category;
    private Profile p1;
    private Profile p2;
    private ArrayList<Question> tenQuestions;


    public GameLogic(Profile p1, Profile p2, String category) {
        this.p1 = p1;
        this.p2 = p2;
        this.category = category;
        getTenQuestions(category);
    }

    public GameLogic(Profile p1, String category) {
        this.p1 = p1;
        this.category = category;
        getTenQuestions(category);
    }

    private void getTenQuestions(String category) {
        tenQuestions = db.getAllQuestions(category);
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public ArrayList<Question> getQuestions(){
        return tenQuestions;
    }


}
