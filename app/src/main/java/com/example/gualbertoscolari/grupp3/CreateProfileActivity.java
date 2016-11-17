package com.example.gualbertoscolari.grupp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }


    public void saveProfile(View view) {

        inputName = (EditText) findViewById(R.id.profileName);
        String name = inputName.getText().toString();

        Profiles p = new Profiles(name, 0);
        DbHelper db = new DbHelper(getApplicationContext());

        db.addProfile(p);
    }
}
