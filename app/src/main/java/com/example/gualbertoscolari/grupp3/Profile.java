package com.example.gualbertoscolari.grupp3;
//setters behövs inte då värdena sätts i konstruktorn.

public class Profile {

    private int ID;
    private String name;
    private int score;


    //For creating new profile
    public Profile(){
        ID = 0;
        name = "";
    }

    //For creating profile from databas
    public Profile(String nAme, int sCore){
        name = nAme;
        score = sCore;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
