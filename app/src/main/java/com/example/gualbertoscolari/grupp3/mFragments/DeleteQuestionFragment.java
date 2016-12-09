package com.example.gualbertoscolari.grupp3.mFragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.gualbertoscolari.grupp3.DbHelper;
import com.example.gualbertoscolari.grupp3.R;
import java.util.ArrayList;

public class DeleteQuestionFragment extends Fragment {

    private ArrayAdapter<String> arrayAdapterStrings;
    private Cursor cursor;
    private DbHelper db;
    private ListView list;
    private ArrayList<String> allStrings;
    private ArrayList<Integer> allints;
    private Spinner options;
    private ArrayAdapter optAdapter;
    private ArrayList<String> quesProfCat;
    private boolean quest;
    private boolean prof;
    private boolean cat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_delete,container,false);

        list = (ListView) rootView.findViewById(R.id.question_list);
        options = (Spinner) rootView.findViewById(R.id.option_spinner);
        list = (ListView) rootView.findViewById(R.id.question_list);
        quesProfCat = new ArrayList<>();

        quesProfCat.add("Questions");
        quesProfCat.add("Profiles");
        quesProfCat.add("Categories");

        optAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, quesProfCat);

        options.setAdapter(optAdapter);

        db = new DbHelper(getActivity());

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(optAdapter.getItem(position).equals("Questions")) {
                    cursor = db.getCreatedQuestions();
                    quest = true;
                    prof = false;
                    cat = false;
                }else if(optAdapter.getItem(position).equals("Profiles")){
                    cursor = db.getCreatedProfiles();
                    prof = true;
                    quest = false;
                    cat = false;
                }else if(optAdapter.getItem(position).equals("Categories")){
                    cursor = db.getCreatedCategories();
                    cat = true;
                    prof = false;
                    quest = false;
                }else{
                    Toast.makeText(getActivity(), "No items to display", Toast.LENGTH_SHORT).show();
                }

                allStrings = new ArrayList<>();
                allints = new ArrayList<>();

                //istället för en cursoradapter använder vi en vanlig adapter, då dettta är mindre kod och fungerar.
                for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    allints.add(cursor.getInt(0));
                    allStrings.add(cursor.getString(1));
                }

                if(allStrings.size()>0) // check if list contains items.
                {
                    arrayAdapterStrings = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,allStrings);
                    list.setAdapter(arrayAdapterStrings);
                }else{
                    arrayAdapterStrings = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,allStrings);
                    list.setAdapter(arrayAdapterStrings);
                    Toast.makeText(getActivity(), "No items to display", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog diaBox = AskOption(position);
                diaBox.show();
            }
        });
        return rootView;
    }

    private AlertDialog AskOption(final int position)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(getActivity())

                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.natur)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        if(quest) {
                            Log.d("ta bort", "tog bort quest" + allints.get(position));
                            db.deleteCreatedQuestion(allints.get(position));
                            allints.remove(position);
                            arrayAdapterStrings.remove(arrayAdapterStrings.getItem(position));
                            prof = false;
                            cat = false;

                        }else if(prof){
                            list.setAdapter(arrayAdapterStrings);
                            Log.d("ta bort", "tog bort prof" + allints.get(position));
                            db.deleteCreatedProfiles(allints.get(position));
                            allints.remove(position);
                            db.deleteCreatedHSProfiles(arrayAdapterStrings.getItem(position));
                            arrayAdapterStrings.remove(arrayAdapterStrings.getItem(position));
                            quest = false;
                            cat = false;

                        }else if(cat){
                            Log.d("ta bort", "tog bort cat " + allints.get(position));
                            db.deleteCreatedCategory(allints.get(position));
                            allints.remove(position);
                            arrayAdapterStrings.remove(arrayAdapterStrings.getItem(position));
                            quest = false;
                            prof = false;
                        }
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