package com.example.gualbertoscolari.grupp3;

public class Profiles {

    private String name;
    private int score;

    public Profiles(){
        name = "";
        score = 0;
    }
    public Profiles(String nAme, int sCore){
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
}
