package com.example.gualbertoscolari.grupp3;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> listItems = new ArrayList<String>();


    ListView quiestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        quiestions = (ListView) findViewById(R.id.question_list);

        DbHelper db = new DbHelper(this);

        //db.open();
        List<String> all = db.getCreatedQuestions();
        if(all.size()>0) // check if list contains items.
        {
            quiestions = (ListView)findViewById(R.id.question_list);
            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,all);
            quiestions.setAdapter(arrayAdapter);
        }
        else
        {
            Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
        }

        quiestions.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.natur)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }
}
