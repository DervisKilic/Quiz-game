package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.gualbertoscolari.grupp3.mFragments.AddCategoryFragment;
import com.example.gualbertoscolari.grupp3.mFragments.AddQuestionFragment;
import com.example.gualbertoscolari.grupp3.mFragments.DeleteQuestionFragment;

public class ManageContentActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    private static final String TAG = "MANAGECONTENTACTIVTY";
    AHBottomNavigation bottomNavigation;

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
        AHBottomNavigationItem addQuestionItem = new AHBottomNavigationItem("+ Question", R.drawable.add_question_icon);
        AHBottomNavigationItem addCategoryItem = new AHBottomNavigationItem("+ Category", R.drawable.add_question_icon);
        AHBottomNavigationItem deleteQuestionItem = new AHBottomNavigationItem("Delete Question", R.drawable.add_question_icon);

        //Add them to bar
        bottomNavigation.addItem(addQuestionItem);
        bottomNavigation.addItem(addCategoryItem);
        bottomNavigation.addItem(deleteQuestionItem);

        //Set properties of navigationbar
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

        //Set default item
        bottomNavigation.setCurrentItem(0);

    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {

        //Show fragments
        if (position == 0) {
            Log.d(TAG, "onTabSelected: Position: " + position);

            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new AddQuestionFragment()).commit();

        } else if (position == 1) {
            Log.d(TAG, "onTabSelected: Position: " + position);

            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new AddCategoryFragment()).commit();
        } else if (position == 2) {
            Log.d(TAG, "onTabSelected: Position: " + position);
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_manage_content, new DeleteQuestionFragment()).commit();
        }

        return false;
    }
}
