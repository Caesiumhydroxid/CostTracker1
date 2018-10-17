package com.example.jonas.costtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class MainScreenFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_screen, container,false);
        PieChart chart = (PieChart) v.findViewById(R.id.chart);
        chart.setHoleRadius(25f);
        chart.setTransparentCircleAlpha(0);
        chart.setCenterText("Super Cool Chart");
        chart.setCenterTextSize(10);

        List<PieEntry> entries = new ArrayList<>();
        List<String> lables = new ArrayList<>();
        for (int i=0;i<10;i++) {

            // turn your data into Entry objects
            PieEntry entry = new PieEntry(10.f,"Test");

            entries.add(entry);

        }
        PieDataSet dataSet = new PieDataSet(entries,"Test");

        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.invalidate();
        return v;
    }
}
