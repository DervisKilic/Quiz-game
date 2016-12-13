package com.example.gualbertoscolari.grupp3.Logic;
//Den tomma konstruktorn ska tas bort.
//Ett question objekt innehåller 1 fråga 4 svar 1 rätt svar och 1 kategori.

public class Question {

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String CATEGORY;
    private String ANSWER;

    Question() {
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        OPTD = "";
        CATEGORY = "";
        ANSWER = "";
    }

    public Question(String qUESTION, String oPTA, String oPTB, String oPTC, String oPTD, String cATEGORY, String aNSWER) {
        QUESTION = qUESTION;
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        OPTD = oPTD;
        CATEGORY = cATEGORY;
        ANSWER = aNSWER;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public String getOPTD() {
        return OPTD;
    }

    void setOPTD(String OPTD) {
        this.OPTD = OPTD;
    }

    String getCATEGORY() {
        return CATEGORY;
    }

    void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    String getANSWER() {
        return ANSWER;
    }

    void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
