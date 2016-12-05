package com.example.gualbertoscolari.grupp3;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;
        import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapterQuest;
    private ArrayAdapter<Integer> arrayAdapterQuestID;
    private Cursor cursor;
    private DbHelper db;
    private ListView list;
    private ArrayList<String> allStrings;
    private ArrayList<Integer> allints;
    private Spinner options;
    private ArrayAdapter optAdapter;
    private ArrayList<String> quesProfCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        list = (ListView) findViewById(R.id.question_list);
        options = (Spinner) findViewById(R.id.option_spinner);
        list = (ListView)findViewById(R.id.question_list);
        quesProfCat = new ArrayList<>();

        quesProfCat.add("Questions");
        quesProfCat.add("Profiles");
        quesProfCat.add("Categories");

        optAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, quesProfCat);

        options.setAdapter(optAdapter);

        db = new DbHelper(this);
        cursor = db.getCreatedQuestions();

        allStrings = new ArrayList<>();
        allints = new ArrayList<>();

        //istället för en cursoradapter använder vi en vanlig adapter, då dettta är mindre kod och fungerar.
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            allints.add(cursor.getInt(0));
            allStrings.add(cursor.getString(1));
        }

        if(allStrings.size()>0) // check if list contains items.
        {
            arrayAdapterQuest = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allStrings);
            arrayAdapterQuestID = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,allints);
            list.setAdapter(arrayAdapterQuest);
        }else{
            Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog diaBox = AskOption(position);
                diaBox.show();
            }
        });
    }

    private AlertDialog AskOption(final int position)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)

                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.natur)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        Log.d("ta bort", "tog bort" + arrayAdapterQuestID.getItem(position));

                        db.deleteCreatedQuestion(arrayAdapterQuestID.getItem(position));
                        arrayAdapterQuest.remove(arrayAdapterQuest.getItem(position));
                        arrayAdapterQuest.notifyDataSetChanged();

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

    public void goToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
