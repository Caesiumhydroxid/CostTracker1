package com.example.jonas.costtracker.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepository repository;
    private LiveData<List<Expense>> allExpenses;
    public ExpenseViewModel(Application application){
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();

    }

    LiveData<List<Expense>> getAllExpenses() { return allExpenses; }

    public void insert(Expense exp) { repository.insert(exp); }


}
