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

public class AddQuestionFragment extends Fragment{

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_create_question,container,false);




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
    public void saveQuestion(View v){

        EditText inputQuestion = (EditText) v.getRootView().findViewById(R.id.question_tv);
        String question = inputQuestion.getText().toString();

        EditText inputcorrectAnswer = (EditText) v.getRootView().findViewById(R.id.opt_a);
        String correctAnswer = inputcorrectAnswer.getText().toString();

        EditText inputOptb = (EditText) v.getRootView().findViewById(R.id.opt_b);
        String optb = inputOptb.getText().toString();

        EditText inputOptc = (EditText) v.getRootView().findViewById(R.id.opt_c);
        String optc = inputOptc.getText().toString();

        EditText inputOptd = (EditText) v.getRootView().findViewById(R.id.opt_d);
        String optd = inputOptd.getText().toString();

        Spinner chosenCat = (Spinner) v.getRootView().findViewById(R.id.spinner_create_question);
        String cat = chosenCat.getSelectedItem().toString();

        if (!question.matches(("^[a-zåäöA-ZÅÄÖ ]{3,150}$"))){
            Toast.makeText(getActivity(), "Max 150 letters or at least 3", Toast.LENGTH_SHORT).show();
            inputQuestion.setText("");
            inputQuestion.setHint(R.string.enter_q_hint);

        } else if (!correctAnswer.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(getActivity(), "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputcorrectAnswer.setText("");
            inputcorrectAnswer.setHint(R.string.correct_answer_et);

        } else if (!optb.matches("^[a-zåäöA-ZÅÄÖ ]{1,20}$")){
            Toast.makeText(getActivity(), "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptb.setText("");
            inputOptb.setHint(R.string.enter_opt_b_hint);

        } else if (!optc.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(getActivity(), "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptc.setText("");
            inputOptc.setHint(R.string.enter_opt_c_hint);

        } else if (!optd.matches(("^[a-zåäöA-ZÅÄÖ ]{1,20}$"))){
            Toast.makeText(getActivity(), "Max 20 letters or at least 1", Toast.LENGTH_SHORT).show();
            inputOptd.setText("");
            inputOptd.setHint(R.string.enter_opt_d_hint);

        } else {
            Toast.makeText(getActivity(), "You added a new question", Toast.LENGTH_SHORT).show();
            Question q = new Question(question, correctAnswer, optb, optc, optd, cat, correctAnswer);
            DbHelper db = new DbHelper(getActivity().getApplicationContext());
            db.addQuestion(q);
            db.close();
        }
    }

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
