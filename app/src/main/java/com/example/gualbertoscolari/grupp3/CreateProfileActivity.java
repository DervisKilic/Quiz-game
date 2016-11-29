package com.example.gualbertoscolari.grupp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//Klassen låter användaren skapa en profil att spela med.
//Klassen innehåller 2 knappar 1 för att spara sin profil och 1 för att gå tillbaka till GameSettings
//Save knappen sparar ner profilnamnet i en variabel och skickar till databasen.
//Toasters och if satser för felhantering.
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
        name = inputName.getText().toString();

        Profile p = new Profile(name, 0);
        DbHelper db = new DbHelper(getApplicationContext());

        if(name.length() > 12){
            Toast.makeText(this, "Max 12 letters", Toast.LENGTH_SHORT).show();
            inputName.setText("");
            inputName.setHint(this.getString(R.string.create_profile));

        } else {
            Toast.makeText(this, "You added a new profile", Toast.LENGTH_SHORT).show();
            inputName.setText("");
            inputName.setHint(this.getString(R.string.create_profile));
            db.addProfile(p);
            db.addPlaceholderHSProfile(p.getName());
        }




    }

    public void goToGameSettings(View view) {
        profileName = (EditText) findViewById(R.id.profile_name);
        name = profileName.getText().toString();

        Intent playAgainIntent = new Intent(this, GameSettingsActivity.class);
        startActivity(playAgainIntent);


    }
}
