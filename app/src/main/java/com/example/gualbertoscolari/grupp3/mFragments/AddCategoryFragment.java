package com.example.gualbertoscolari.grupp3.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gualbertoscolari.grupp3.R;

/**
 * Created by Siniom on 08/12/16.
 */

public class AddCategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_create_category,container,false);

        return rootView;
    }


}
