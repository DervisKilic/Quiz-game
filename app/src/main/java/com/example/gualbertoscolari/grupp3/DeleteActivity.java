package com.example.gualbertoscolari.grupp3;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;

    private DbHelper db;
    ListView quiestions;
    private Question quest;
    private DeleteCursorAdapter deleteAdapter;
    private int deleteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        //quiestions = (ListView) findViewById(R.id.question_list);

        db = new DbHelper(this);

        //db.open();
        //List<String> all = db.getCreatedQuestions();



        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("quest", null, "_id>?", new String[]{"50"}, null, null, null);
        quiestions = (ListView) findViewById(R.id.question_list);
        deleteAdapter = new DeleteCursorAdapter(this, cursor);
        quiestions.setAdapter(deleteAdapter);
        if(deleteAdapter.isEmpty()) // check if list contains items.
        {
            Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
        }


        quiestions.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //quest = deleteAdapter.getItem(position);
                Log.d("Hämtat id", "id: " + deleteId);
                AlertDialog diaBox = AskOption(position);
                diaBox.show();
            }
        });





    }

    private AlertDialog AskOption(final int postion)
    {

        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)



                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.natur)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        Log.d("ta bort", "tog bort" + quest.getID());

                        db.deleteCreatedQuestion(quest.getID());
                        deleteAdapter.notifyDataSetChanged();

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

    private class DeleteCursorAdapter extends CursorAdapter{

        public DeleteCursorAdapter(Context context, Cursor cursor){
            super(context, cursor, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            return LayoutInflater.from(context).inflate(R.layout.layou_created_question, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView listQuestion = (TextView) view.findViewById(R.id.list_item);
            Question question = new Question();
            // findViewById(R.id.list_item)
            question.setID(cursor.getInt(0));
            question.setQUESTION(cursor.getString(1));
            Log.d("delete", "Strängen är hämtat: " + question.getQUESTION());

            listQuestion.setText(question.getQUESTION());




        }

    }



}
