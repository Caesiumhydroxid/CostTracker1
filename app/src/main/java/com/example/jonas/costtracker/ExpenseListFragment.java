package com.example.jonas.costtracker;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonas.costtracker.database.Expense;
import com.example.jonas.costtracker.database.ExpenseListAdapter;
import com.example.jonas.costtracker.database.ExpenseViewModel;

import java.util.List;

public class ExpenseListFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private ExpenseViewModel expenseViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expense_list,container,false);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);

        final ExpenseListAdapter adapter = new ExpenseListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable final List<Expense> expenses) {
                // Update the cached copy of the words in the adapter.
                adapter.setExpenses(expenses);
            }
        });
        return v;
    }




}
