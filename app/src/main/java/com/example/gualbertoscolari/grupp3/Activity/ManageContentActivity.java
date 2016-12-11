package com.example.gualbertoscolari.grupp3.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.gualbertoscolari.grupp3.Logic.DbHelper;
import com.example.gualbertoscolari.grupp3.Logic.Profile;
import com.example.gualbertoscolari.grupp3.Logic.Question;
import com.example.gualbertoscolari.grupp3.R;
import com.example.gualbertoscolari.grupp3.mFragments.AddCategoryFragment;
import com.example.gualbertoscolari.grupp3.mFragments.AddProfileFragment;
import com.example.gualbertoscolari.grupp3.mFragments.AddQuestionFragment;
import com.example.gualbertoscolari.grupp3.mFragments.DeleteContentFragment;

public class ManageContentActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private AHBottomNavigation bottomNavigation;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_content);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_nav_bar);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }

    private void createNavItems() {
        //Create items in the bottom bar
        AHBottomNavigationItem addQuestionItem = new AHBottomNavigationItem("Question", R.drawable.add_icon);
        AHBottomNavigationItem addCategoryItem = new AHBottomNavigationItem("Category", R.drawable.add_icon);
        AHBottomNavigationItem addProfile = new AHBottomNavigationItem("Profile", R.drawable.add_icon);
        AHBottomNavigationItem deleteQuestionItem = new AHBottomNavigationItem("Remove", R.drawable.delete_icon);

        //Add them to bar
        bottomNavigation.addItem(addQuestionItem);
        bottomNavigation.addItem(addCategoryItem);
        bottomNavigation.addItem(addProfile);
        bottomNavigation.addItem(deleteQuestionItem);

        //Set properties of navigationbar
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#007991"));

        //Set default item
        bottomNavigation.setCurrentItem(0);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#B2DFDB"));
        bottomNavigation.setInactiveColor(Color.parseColor("#000000"));

        // Manage titles
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {

        //Show fragments
        if (position == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new AddQuestionFragment()).commit();
        } else if (position == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new AddCategoryFragment()).commit();
        } else if (position == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new AddProfileFragment()).commit();
        } else if (position == 3) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new DeleteContentFragment()).commit();

        }

        return true;
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            startActivity(new Intent(this, GameSettingsActivity.class));
            super.onBackPressed();
            finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
    //Saves a question in the AddQuestion fragment
    public void saveQuestion(View v) {
        EditText inputQuestion = (EditText) findViewById(R.id.question_tv);
        String question = inputQuestion.getText().toString();

        EditText inputcorrectAnswer = (EditText) findViewById(R.id.opt_a);
        String correctAnswer = inputcorrectAnswer.getText().toString();

        EditText inputOptb = (EditText) findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        EditText inputOptc = (EditText) findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        EditText inputOptd = (EditText) findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        Spinner chosenCat = (Spinner) findViewById(R.id.spinner_create_question);
        String cat = chosenCat.getSelectedItem().toString();

        if (!question.matches(("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{3,150}$"))) {
            Toast.makeText(this, "Max 150 letters or at least 3", Toast.LENGTH_SHORT).show();
            inputQuestion.setText("");
            inputQuestion.setHint(R.string.enter_q_hint);

        } else if (!correctAnswer.matches(("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{1,20}$"))) {
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputcorrectAnswer.setText("");
            inputcorrectAnswer.setHint(R.string.correct_answer_et);

        } else if (!optb.matches("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{1,20}$")) {
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptb.setText("");
            inputOptb.setHint(R.string.enter_opt_b_hint);

        } else if (!optc.matches(("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{1,20}$"))) {
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptc.setText("");
            inputOptc.setHint(R.string.enter_opt_c_hint);

        } else if (!optd.matches(("^[a-zåäöA-ZÅÄÖ -1-9 -? ]{1,20}$"))) {
            Toast.makeText(this, "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptd.setText("");
            inputOptd.setHint(R.string.enter_opt_d_hint);

        } else {
            Toast.makeText(this, "You added a new question", Toast.LENGTH_SHORT).show();
            inputQuestion.setText("");
            inputcorrectAnswer.setText("");
            inputOptb.setText("");
            inputOptc.setText("");
            inputOptd.setText("");

            Question q = new Question(question, correctAnswer, optb, optc, optd, cat, correctAnswer);
            db = new DbHelper(getApplicationContext());
            db.addQuestion(q);
            db.close();
        }
    }

    public void saveProfile(View view) {

        EditText inputName = (EditText) findViewById(R.id.profile_name);
        String name = inputName.getText().toString();

        Profile p = new Profile(name, 0);
        db = new DbHelper(getApplicationContext());

        if (!name.matches("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{3,12}$")) {
            Toast.makeText(this, "Max 12 letters", Toast.LENGTH_SHORT).show();
            inputName.setText("");
            inputName.setHint(this.getString(R.string.create_profile));

        } else if (!db.checkIfNameExists(name)) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You added a new profile", Toast.LENGTH_SHORT).show();
            inputName.setText("");
            inputName.setHint(this.getString(R.string.create_profile));
            db.addProfile(p);
        }
        db.close();
    }

    public void addCategory(View v){
        db = new DbHelper(getApplicationContext());
        EditText category = (EditText) findViewById(R.id.categorie_name);
        String cat = category.getText().toString();

        if(!cat.matches("^[a-zåäöA-ZÅÄÖ -0-9 -? ]{3,12}$")){
            Toast.makeText(this, "Max 12 letters or at least 1", Toast.LENGTH_SHORT).show();
            category.setText("");
            category.setHint(this.getString(R.string.enter_category_name));

        } else if(!db.checkIfCatExists(cat)) {
            Toast.makeText(this, "Category already exists!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "You added a new category", Toast.LENGTH_SHORT).show();
            category.setText("");
            category.setHint(this.getString(R.string.enter_category_name));
            db.addCategorys(cat);
            db.close();
        }
    }



}
