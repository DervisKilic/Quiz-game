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
        sqlQuestions += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlQuestions += "Question VARCHAR(255) NOT NULL,";
        sqlQuestions += "opta VARCHAR(255) NOT NULL,";
        sqlQuestions += "optb VARCHAR(255) NOT NULL,";
        sqlQuestions += "optc VARCHAR(255) NOT NULL,";
        sqlQuestions += "optd VARCHAR(255) NOT NULL,";
        sqlQuestions += "category VARCHAR(255) NOT NULL,";
        sqlQuestions += "answer VARCHAR(255) NOT NULL";
        sqlQuestions += ");";

        Log.d("bajs", "Database created");

        db.execSQL(sqlQuestions);

        String sqlProfiles = "CREATE TABLE " + TABLE_PROFILE+ " (";
        sqlProfiles += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlProfiles += "name VARCHAR(255) NOT NULL,";
        sqlProfiles += "score INTEGER NOT NULL";
        //sqlProfiles += KEY_IMG+ "BLOB NOT NULL,";
        sqlProfiles += ");";

        Log.d("profiles", "Database created");

        db.execSQL(sqlProfiles);

        String sqlCategorys = "CREATE TABLE " + TABLE_CATEGORY+ " (";
        sqlCategorys += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlCategorys += "category VARCHAR(255) NOT NULL";
        sqlCategorys += ");";

        Log.d("categorys", "Database created");


        db.execSQL(sqlCategorys);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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

        long id = db.insert(TABLE_QUEST, null, cvs);

        Log.d("Hej", "row id: "+id);

        db.close();

    }

    public void addProfile(Profiles p) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put("name", p.getName());
        cvs.put("score", p.getScore());
        //cvs.put("profileimg", p.getProfileImg);

        long id = db.insert(TABLE_PROFILE, null, cvs);

        Log.d("Hejprofile", "row id: "+id);

        db.close();
    }

    public void addCategorys(String category) {

        SQLiteDatabase db = getWritableDatabase();
        //Kolla med dervis angående "Categorys" och "Category"
        ContentValues cvs = new ContentValues();
        cvs.put("category", category);

        long id = db.insert(TABLE_CATEGORY, null, cvs);

        Log.d("Hejcategory", "row id: "+id);

        db.close();
    }
}
