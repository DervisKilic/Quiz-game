package com.example.gualbertoscolari.grupp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
//Lagra skriva och läsa frågor, profiler och kategorier till och från databasen.
//
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "quiz_db";
    // tasks table name for questions
    private static final String TABLE_QUESTION = "quest";
    // tasks table name for profiles
    private static final String TABLE_PROFILE = "profile";
    //
    private static final String TABLE_CATEGORY = "categorys";

    // tasks Table Columns names for questions
    private static final String KEY_ID = "id";
    private static final String KEY_QUEST = "question";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private static final String KEY_OPTD = "optd";
    private static final String KEY_CAT = "category";
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

        String sqlQuestions = "CREATE TABLE " + TABLE_QUESTION + " (";
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

        String sqlProfiles = "CREATE TABLE " + TABLE_PROFILE + " (";
        sqlProfiles += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlProfiles += "name VARCHAR(255) NOT NULL,";
        sqlProfiles += "score INTEGER NOT NULL";
        //sqlProfiles += KEY_IMG+ "BLOB NOT NULL,";
        sqlProfiles += ");";

        Log.d("profiles", "Database created");

        db.execSQL(sqlProfiles);

        String sqlCategorys = "CREATE TABLE " + TABLE_CATEGORY + " (";
        sqlCategorys += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlCategorys += "category VARCHAR(255) NOT NULL";
        sqlCategorys += ");";

        Log.d("categorys", "Database created");


        db.execSQL(sqlCategorys);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION + TABLE_CATEGORY + TABLE_PROFILE);
        onCreate(db);


    }

    public void addQuestion(Question q) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put(KEY_QUEST, q.getQUESTION());
        cvs.put(KEY_OPTA, q.getOPTA());
        cvs.put(KEY_OPTB, q.getOPTB());
        cvs.put(KEY_OPTC, q.getOPTC());
        cvs.put(KEY_OPTD, q.getOPTD());
        cvs.put(KEY_CAT, q.getCATEGORY());
        cvs.put(KEY_ANSWER, q.getANSWER());

        long id = db.insert(TABLE_QUESTION, null, cvs);

        Log.d("Hej", "row id: " + id);

        db.close();

    }

    public void addProfile(Profile p) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put(KEY_NAME, p.getName());
        cvs.put(KEY_SCORE, p.getScore());
        //cvs.put(KEY_IMG, p.getProfileImg);

        long id = db.insert(TABLE_PROFILE, null, cvs);

        Log.d("Hejprofile", "row id: " + id);

        db.close();
    }

    public void addCategorys(String category) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cvs = new ContentValues();
        cvs.put(KEY_CATEGORY, category);

        long id = db.insert(TABLE_CATEGORY, null, cvs);

        Log.d("Hejcategory", "row id: " + id);

        db.close();
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION + " ORDER BY RANDOM()";
        dbase = this.getReadableDatabase();
        //Cursor cursor = dbase.rawQuery(selectQuery, null);
        Cursor cursor = dbase.query(true, TABLE_QUESTION, null, null, null, null, null, "Random()", "10");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(0);
                quest.setQUESTION(cursor.getString(1));
                quest.setOPTA(cursor.getString(2));
                quest.setOPTB(cursor.getString(3));
                quest.setOPTC(cursor.getString(4));
                quest.setOPTD(cursor.getString(5));
                quest.setCATEGORY(cursor.getString(6));
                quest.setANSWER(cursor.getString(7));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        cursor.close();
        return quesList;
    }

    public List<Profile> getAllProfiles() {
        List<Profile> profList = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PROFILE;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile p = new Profile();
                p.setName(cursor.getString(1));
                p.setScore(cursor.getInt(2));


                profList.add(p);
            } while (cursor.moveToNext());
        }
        // return quest list
        cursor.close();
        return profList;
    }

    // This is to be removed. Used for testing app with questions. This method is called in
    // MainGameActivity.java (in onCreate)
    public void addStandardQuestionsSQL() {
        List<Question> quesList = getAllQuestions();
        if(quesList.size() < 9) {
            addQuestion(StandardQuestions.q1);
            addQuestion(StandardQuestions.q2);
            addQuestion(StandardQuestions.q3);
            addQuestion(StandardQuestions.q4);
            addQuestion(StandardQuestions.q5);
            addQuestion(StandardQuestions.q6);
            addQuestion(StandardQuestions.q7);
            addQuestion(StandardQuestions.q8);
            addQuestion(StandardQuestions.q9);
            addQuestion(StandardQuestions.q10);
        }

        Log.d("Kiss", "Questions added yeah");
    }
}
