package com.example.gualbertoscolari.grupp3.Logic;
//setters behövs inte då värdena sätts i konstruktorn.

public class Profile {

    private int ID;
    private String name;
    private int score;

    /**
     * profile id and name
     */
    public Profile() {
        ID = 0;
        name = "";
    }

    /**
     * @param nAme  input name
     * @param sCore score is 0 at start
     */
    public Profile(String nAme, int sCore) {
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

    void setScore(int score) {
        this.score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
