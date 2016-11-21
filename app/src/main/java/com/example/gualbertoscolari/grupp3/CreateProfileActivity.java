package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProfileActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText profileName;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }


    public void saveProfile(View view) {

        inputName = (EditText) findViewById(R.id.profile_name);
        String name = inputName.getText().toString();

        Profiles p = new Profiles(name, 0);
        DbHelper db = new DbHelper(getApplicationContext());

        inputName.setText("");
        Toast.makeText(this, "You added a new profile", Toast.LENGTH_SHORT).show();
        inputName.setHint(this.getString(R.string.enter_category_name));

        db.addProfile(p);
    }

    public void goToGameSettings(View view) {
        profileName = (EditText) findViewById(R.id.profile_name);
        name = profileName.getText().toString();

        Intent playAgainIntent = new Intent(this, GameSettingsActivity.class);
        startActivity(playAgainIntent);


    }
}
