package com.example.jonas.costtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.vision.text.Line;

public class AddExpenseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_expense,container,false);
        LinearLayout layout = v.findViewById(R.id.linearLayout);
        for(int i=0;i<5;i++) {
            LinearLayout lay = new LinearLayout(getContext());
            lay.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 0.5f;
            Button b = new Button(getContext());
            b.setText("Button" +i );
            b.setLayoutParams(params);
            layout.addView(lay);
            lay.addView(b);
            Button bu = new Button(getContext());
            bu.setLayoutParams(params);
            bu.setText("Button2" +i );
            lay.addView(bu);
        }
        return v;
    }
}
