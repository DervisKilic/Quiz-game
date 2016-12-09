package com.example.gualbertoscolari.grupp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private static final String KEY_ID = "_id";
    private static final String KEY_QUEST = "question";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private static final String KEY_OPTD = "optd";
    private static final String KEY_CAT = "category";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_HSCAT = "hscategory";
    private static final String KEY_HSNAME = "hsname";
    private static final String KEY_HSSCORE = "hsscore";

    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    private static final String KEY_CATEGORY = "category";
    private static final String TABLE_HIGHSCORE = "highscore";
    private static final String TAG = "dbhelper.java";

    private SQLiteDatabase dbaseRead;
    private SQLiteDatabase dbaseWrite;

    private int topTen = 0;
    //private boolean close;

    /**
     *
     * @param context context of the app
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbase) {

        String sqlQuestions = "CREATE TABLE " + TABLE_QUESTION + " (";
        sqlQuestions += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlQuestions += "question VARCHAR(255) NOT NULL,";
        sqlQuestions += "opta VARCHAR(255) NOT NULL,";
        sqlQuestions += "optb VARCHAR(255) NOT NULL,";
        sqlQuestions += "optc VARCHAR(255) NOT NULL,";
        sqlQuestions += "optd VARCHAR(255) NOT NULL,";
        sqlQuestions += "category VARCHAR(255) NOT NULL,";
        sqlQuestions += "answer VARCHAR(255) NOT NULL";
        sqlQuestions += ");";
        Log.d("question", "Table created");

        dbase.execSQL(sqlQuestions);

        String sqlProfiles = "CREATE TABLE " + TABLE_PROFILE + " (";
        sqlProfiles += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlProfiles += "name VARCHAR(255) NOT NULL,";
        sqlProfiles += "score INTEGER NOT NULL";
        //sqlProfiles += KEY_IMG+ "BLOB NOT NULL,";
        sqlProfiles += ");";
        Log.d("profiles", "Table created");

        dbase.execSQL(sqlProfiles);

        String sqlCategorys = "CREATE TABLE " + TABLE_CATEGORY + " (";
        sqlCategorys += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlCategorys += "category VARCHAR(255) NOT NULL";
        sqlCategorys += ");";
        Log.d("categorys", "Table created");

        dbase.execSQL(sqlCategorys);

        String sqlHighScores = "CREATE TABLE " + TABLE_HIGHSCORE + " (";
        sqlHighScores += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sqlHighScores += "hsname VARCHAR(255) NOT NULL,";
        sqlHighScores += "hscategory VARCHAR(255) NOT NULL,";
        sqlHighScores += "hsscore INTEGER NOT NULL";
        sqlHighScores += ");";
        Log.d("highscores", "Table created");

        dbase.execSQL(sqlHighScores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int oldVersion, int newVersion) {
        dbase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION + TABLE_CATEGORY + TABLE_PROFILE + TABLE_HIGHSCORE);
        onCreate(dbase);
    }

    /**
     *
     * @param q     adds new question data to questions table database
     */
    public void addQuestion(Question q) {
        dbaseWrite = getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(KEY_QUEST, q.getQUESTION());
        cvs.put(KEY_OPTA, q.getOPTA());
        cvs.put(KEY_OPTB, q.getOPTB());
        cvs.put(KEY_OPTC, q.getOPTC());
        cvs.put(KEY_OPTD, q.getOPTD());
        cvs.put(KEY_CAT, q.getCATEGORY());
        cvs.put(KEY_ANSWER, q.getANSWER());
        long id = dbaseWrite.insert(TABLE_QUESTION, null, cvs);
        Log.d("Hej", "row id: " + id);

    }

    /**
     *
     * @param p     adds new profile data to profiles table database
     */
    public void addProfile(Profile p) {
        dbaseWrite = getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(KEY_NAME, p.getName());
        cvs.put(KEY_SCORE, p.getScore());

        long id = dbaseWrite.insert(TABLE_PROFILE, null, cvs);
        Log.d(TAG, " addProfile: row id: " + id);

        dbaseWrite.close();
    }

    /**
     *
     * @param profile  checks if input profile data already exists in profile table database
     * @return returns true or false
     */
    public boolean checkIfNameExists(String profile){
        dbaseRead = getReadableDatabase();

        Cursor cursor = dbaseRead.query(true, TABLE_PROFILE, null, KEY_NAME + "=?", new String[]{profile}, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param category         checks if input category data already exists in table category database
     * @return returns true or false
     */
    public boolean checkIfCatExists(String category){
        dbaseRead = getReadableDatabase();

        Cursor cursor = dbaseRead.query(true, TABLE_CATEGORY, null, KEY_CATEGORY + "=?", new String[]{category}, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0){
            return true;
        }else{
            return false;
        }

    }


    /**
     *
     * @param category         adds new category data to category table database
     */
    public void addCategorys(String category) {
        dbaseWrite = getWritableDatabase();
        ContentValues cvs = new ContentValues();
        cvs.put(KEY_CATEGORY, category);
        long id = dbaseWrite.insert(TABLE_CATEGORY, null, cvs);
        Log.d("Hejcategory", "row id: " + id);
        dbaseWrite.close();
    }

    /**
     *
     * @param category     gets a list with all standard questions from table
     * @return returns 10 random questions witch chosen category
     */
    public List<Question> getAllQuestions(String category) {
        List<Question> quesList = new ArrayList<>();
        // Select All Query
        dbaseRead = getReadableDatabase();
        Cursor cursor;
        if (category.equals("Alla kategorier")) {
            cursor = dbaseRead.query(true, TABLE_QUESTION, null, null, null, null, null, "Random()", "10");
        } else {
            cursor = dbaseRead.query(true, TABLE_QUESTION, null, KEY_CAT + "=?", new String[]{category}, null, null, "Random()", "10");
        }
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

    /**
     *
     * @return         gets all created questions from table and returns them
     */
    public Cursor getCreatedQuestions() {
        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.query(true, TABLE_QUESTION, null, KEY_ID + ">?", new String[]{"50"}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // return quest list
        return cursor;
    }

    /**
     *
     * @return         gets all created profiles from table and returns them
     */
    public Cursor getCreatedProfiles() {
        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.query(true, TABLE_PROFILE, null, KEY_ID + ">?", new String[]{"4"}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile prof = new Profile();
                prof.setID(cursor.getInt(0));
                prof.setName(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // return quest list
        return cursor;
    }

    /**
     *
     * @return         gets all created categories from table and returns them
     */
    public Cursor getCreatedCategories() {
        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.query(true, TABLE_CATEGORY, null, KEY_ID + ">?", new String[]{"6"}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile prof = new Profile();
                prof.setID(cursor.getInt(0));
                prof.setName(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // return quest list
        return cursor;
    }

    /**
     *
     * @param id     method for deleting created questions
     */
    public void deleteCreatedQuestion(int id){
        dbaseWrite = getReadableDatabase();
        dbaseWrite.delete(TABLE_QUESTION, KEY_ID+"=?", new String[]{""+id});

    }

    /**
     *
     * @param id         method for deleting created profiles
     */
    public void deleteCreatedProfiles(int id){
        dbaseWrite = getReadableDatabase();
        dbaseWrite.delete(TABLE_PROFILE , KEY_ID+"=?", new String[]{""+id});
    }

    /**
     *
     * @param name         method for deleting created highscore profiles
     */
    public void deleteCreatedHSProfiles(String name){
        dbaseWrite = getReadableDatabase();
        dbaseWrite.delete(TABLE_HIGHSCORE , KEY_HSNAME+"=?", new String[]{name});
    }

    /**
     *
     * @param id         method for deleting created categories
     */
    public void deleteCreatedCategory(int id){
        dbaseWrite = getReadableDatabase();
        dbaseWrite.delete(TABLE_CATEGORY , KEY_ID+"=?", new String[]{""+id});
    }

    /**
     *
     * @return     gets all profiles from table and returns them
     */
    public List<Profile> getAllProfiles() {
        List<Profile> profList = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_PROFILE;
        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile p = new Profile(cursor.getString(1), (cursor.getInt(2)));
                profList.add(p);
            } while (cursor.moveToNext());
        }
        // return quest list
        cursor.close();
        return profList;

    }

    /**
     *     adds all standard questions, categories and profiles to the game
     */
    public void addStandardItemsSQL(Context context) {
        List<Question> quesList = getAllQuestions("Natur");
        if (quesList.size() < 10) {
            addQFromTxtFile(context);
        }
        List<String> catList = getAllCatagories();
        if (catList.size() < 6) {
            addCategorys("Sport");
            addCategorys("Natur");
            addCategorys("Kultur/Nöje");
            addCategorys("Historia");
            addCategorys("Samhälle");
            addCategorys("Alla kategorier");
        }

        List<Profile> profileList = getAllProfiles();
        if (profileList.size() < 4) {
            addProfile(new Profile("Dervis", 0));
            addProfile(new Profile("Fredrik", 0));
            addProfile(new Profile("Gualberto", 0));
            addProfile(new Profile("Simon", 0));

        }
        Log.d("Standard content added", "OMG!");



    }

    /**
     *
     * @return     gets all categories from table and returns a list
     */
    public List<String> getAllCatagories() {
        List<String> catList = new ArrayList<String>();
        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.query(TABLE_CATEGORY, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                catList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return catList;

    }


    /**
     *
     * @param category     gets all high score data and
     * @return returns a list
     */
    public List<String> getHighScoredata(String category) {
        List<String> highScoreData = new ArrayList<>();
        dbaseRead = getReadableDatabase();

        Cursor c = dbaseRead.query(true,TABLE_HIGHSCORE, null, KEY_HSCAT + "=?", new String[]{category}, null, null, KEY_HSSCORE + " DESC", null);

        if (c.moveToFirst()) {
            do {
                highScoreData.add(c.getString(1));
                highScoreData.add(c.getString(3));
                topTen++;

            } while (c.moveToNext() && topTen < 10);
        }
        c.close();
        return highScoreData;
    }

    /**
     *     updates the highscore data
     * @param player
     * @param category
     * @return
     */
    public void updateHighScore(Profile player, String category) {

        dbaseRead = getReadableDatabase();
        Cursor cursor = dbaseRead.query(true,TABLE_HIGHSCORE, null, KEY_HSCAT + "=?", new String[]{category}, null, null, KEY_HSSCORE + " DESC", null);
        cursor.moveToFirst();
        Log.d("Curser check :", ""+ cursor.getCount());
        if(cursor.getCount() == 0){
            addNewHighscore(player, category);
        }else{
            cursor.moveToLast();
            if(player.getScore() > cursor.getInt(2)){
                addNewHighscore(player, category);
            }
        }
        if(cursor.getCount() == 5){
                deleteFromHighscore(cursor.getInt(0));
        }
    }

    public void addNewHighscore(Profile player, String category){
        Log.d("Inside addNewHighScore", "Hej");
        dbaseWrite = getWritableDatabase();
        ContentValues cvs = new ContentValues();

        cvs.put(KEY_HSNAME, player.getName());
        cvs.put(KEY_HSCAT, category);
        cvs.put(KEY_HSSCORE, player.getScore());

        dbaseWrite.insert(TABLE_HIGHSCORE, null, cvs);

    }

    public void deleteFromHighscore(int id){
        dbaseWrite = getWritableDatabase();
        dbaseWrite.delete(TABLE_HIGHSCORE, KEY_ID + "=?", new String[]{""+id});
    }

    public void addQFromTxtFile(Context context){
        dbaseWrite = getWritableDatabase();
        String questionTxt = "";

        try {
                InputStream fis = context.getResources().openRawResource(R.raw.quetions);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                while(questionTxt != null){
                    questionTxt = reader.readLine();
                    dbaseWrite.execSQL(questionTxt);
                    Log.d("Read txt-file: ", questionTxt);
            }
        }catch (Exception e){
            Log.d("Loading questions", "Read questions failed or list-file ended");
        }
        Log.d("Outside exeption", "jajjemän");

    }
}