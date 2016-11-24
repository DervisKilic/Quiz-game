package com.example.gualbertoscolari.grupp3;
//setters behövs inte då värdena sätts i konstruktorn.

public class Profile {

    private String name;
    private int score;

    public Profile(String nAme){
        name = nAme;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
