package com.example.gualbertoscolari.grupp3.mFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.gualbertoscolari.grupp3.DbHelper;
import com.example.gualbertoscolari.grupp3.GameSettingsActivity;
import com.example.gualbertoscolari.grupp3.Question;
import com.example.gualbertoscolari.grupp3.R;

import java.util.List;

public class AddQuestionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_create_question, container, false);

        DbHelper db = new DbHelper(getActivity());
        List<String> category = db.getAllCatagories();
        Spinner dropdownCategory = (Spinner) rootView.findViewById(R.id.spinner_create_question);
        ArrayAdapter<String> chosenCategory = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, category);
        dropdownCategory.setAdapter(chosenCategory);

        return rootView;

    }

    /**
     *
     * @param v         adds the input questions if conditions are met.
     */

    /**
     *
     * @param view     takes the player back to game settings
     */

    /*
    public void backToMain(View view){
        Intent GameStettingsActivity = new Intent(this, GameSettingsActivity.class);
        startActivity(GameStettingsActivity);
        finish();
    }*/

}
