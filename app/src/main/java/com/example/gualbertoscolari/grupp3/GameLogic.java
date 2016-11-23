package com.example.gualbertoscolari.grupp3;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//Standard frågor profiler och kategorier ska skapas upp i Gamelogic vid anrop och skickar en lista
//med dessa till MainGameActivity och skriver ut dom.
//Klassen sköter logiken för varje spelrunda.
//En timer metod som sköter värdet på poängen och avslutar rundan.
//
public class GameLogic extends MainGameActivity {

    private DbHelper db;
    private int score = 0;
    private String category;
    private Profile p1;
    private Profile p2;
    private List<Question> tenQuestions;

    public GameLogic(Profile p1, Profile p2, String category, Context context) {
        this.p1 = p1;
        this.p2 = p2;
        this.category = category;
        db = new DbHelper(context);
        tenQuestions = db.getAllQuestions(category);
    }

    public GameLogic(Profile p1, String category, Context context) {
        this.p1 = p1;
        this.category = category;
        db = new DbHelper(context);
        tenQuestions = db.getAllQuestions(category);
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public List<Question> getQuestions() {
        return tenQuestions;
    }
}
