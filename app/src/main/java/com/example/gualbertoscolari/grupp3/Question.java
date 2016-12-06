package com.example.gualbertoscolari.grupp3;
//Den tomma konstruktorn ska tas bort.
//Ett question objekt innehåller 1 fråga 4 svar 1 rätt svar och 1 kategori.

public class Question{

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private String CATEGORY;
    private String ANSWER;

    public Question(){
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        OPTD = "";
        CATEGORY = "";
        ANSWER = "";
    }
    public Question(String qUESTION, String oPTA, String oPTB, String oPTC, String oPTD, String cATEGORY, String aNSWER){
        QUESTION = qUESTION;
        OPTA = oPTA;
        OPTB = oPTB;
        OPTC = oPTC;
        OPTD = oPTD;
        CATEGORY = cATEGORY;
        ANSWER = aNSWER;
    }

    public int getID() {
        return ID;
    }


    public String getQUESTION() {
        return QUESTION;
    }

    public String getOPTA() {
        return OPTA;
    }

    public String getOPTB() {
        return OPTB;
    }

    public String getOPTC() {
        return OPTC;
    }

    public String getOPTD() {
        return OPTD;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public void setOPTA(String OPTA) {
        this.OPTA = OPTA;
    }

    public void setOPTB(String OPTB) {
        this.OPTB = OPTB;
    }

    public void setOPTC(String OPTC) {
        this.OPTC = OPTC;
    }

    public void setOPTD(String OPTD) {
        this.OPTD = OPTD;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
