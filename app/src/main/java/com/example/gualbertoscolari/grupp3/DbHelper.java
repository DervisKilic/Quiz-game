package com.example.gualbertoscolari.grupp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "quiz_db";
    // tasks table name for questions
    private static final String TABLE_QUEST = "quest";
    // tasks table name for profiles
    private static final String TABLE_PROFILE ="profile";

    private static final String TABLE_CATEGORY ="categorys";

    private static final String TABLE_HIGHSCORE ="highscore";

    // tasks Table Columns names for questions
    private static final String KEY_ID = "id";
    private static final String KEY_QUEST = "question";
    private static final String KEY_OPTA= "opta";
    private static final String KEY_OPTB= "optb";
    private static final String KEY_OPTC= "optc";
    private static final String KEY_OPTD= "optd";
    private static final String KEY_CAT= "category";
    private static final String KEY_ANSWER = "answer";

    private static final String KEY_NAME = "name";
    //private static final String KEY_IMG = "profileimg";
    private static final String KEY_SCORE = "score";

    private static final String KEY_CATEGORY = "category";


    private SQLiteDatabase dbase;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;

        String sqlQuestions = "CREATE TABLE " + TABLE_QUEST+ " (";
        sqlQuestions += KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlQuestions += KEY_QUEST + "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_OPTA + "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_OPTB + "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_OPTC + "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_OPTD + "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_CAT+ "VARCHAR(255) NOT NULL,";
        sqlQuestions += KEY_ANSWER +"VARCHAR(255) NOT NULL";
        sqlQuestions += ");";

        Log.d("questions", "Database created");

        db.execSQL(sqlQuestions);

        String sqlProfiles = "CREATE TABLE " + TABLE_PROFILE+ " (";
        sqlProfiles += KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlProfiles += KEY_NAME + "VARCHAR(255) NOT NULL,";
        sqlProfiles += KEY_SCORE + "INTEGER NOT NULL";
        //sqlProfiles += KEY_IMG+ "BLOB NOT NULL,";
        sqlProfiles += ");";

        Log.d("profiles", "Database created");

        db.execSQL(sqlProfiles);

        String sqlCategorys = "CREATE TABLE " + TABLE_CATEGORY+ " (";
        sqlCategorys += KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlCategorys += KEY_CATEGORY+ "VARCHAR(255) NOT NULL";
        sqlCategorys += ");";

        Log.d("categorys", "Database created");

        db.execSQL(sqlCategorys);

        String sqlHighScore = "CREATE TABLE " + TABLE_HIGHSCORE+ " (";
        sqlHighScore += ");";


        Log.d("highscore", "Database created");

        db.execSQL(sqlHighScore);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST + TABLE_PROFILE + TABLE_HIGHSCORE + TABLE_CATEGORY);
        onCreate(db);

    }

    public void addQuestion(Question q) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put("Question", q.getQUESTION());
        cvs.put("opta", q.getOPTA());
        cvs.put("optb", q.getOPTB());
        cvs.put("optc", q.getOPTC());
        cvs.put("optd", q.getOPTD());
        cvs.put("category", q.getCATEGORY());
        cvs.put("answer", q.getANSWER());

        long id = db.insert("quest", null, cvs);

        Log.d("Hej", "row id: "+id);

        db.close();

    }

    public void addProfile(Profiles p) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put("name", p.getName());
        cvs.put("score", p.getScore());
        //cvs.put("profileimg", p.getProfileImg);

        long id = db.insert("profile", null, cvs);

        Log.d("Hejprofile", "row id: "+id);

        db.close();
    }

    public void addCategorys(String category) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put("categorys", category);

        long id = db.insert(TABLE_CATEGORY, null, cvs);

        Log.d("Hejcategory", "row id: "+id);

        db.close();
    }

    public void setHighScore(String category, Profiles name, Profiles score){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();

        long id = db.insert(TABLE_HIGHSCORE, null, cvs);
        Log.d("High Score", "row id: "+id);

        db.close();


    }



}
