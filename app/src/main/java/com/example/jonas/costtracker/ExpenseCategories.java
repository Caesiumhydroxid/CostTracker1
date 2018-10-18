package com.example.jonas.costtracker;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategories {

    private static List<ExpenseCategory> categories;
    private static ExpenseCategories instance;
    private ExpenseCategories() {
        categories = new ArrayList<>();
        categories.add(new ExpenseCategory("Groceries"));
        categories.add(new ExpenseCategory("Food"));
        categories.add(new ExpenseCategory("Hobby"));
        categories.add(new ExpenseCategory("Clothing"));
        categories.add(new ExpenseCategory("Drinking"));
        categories.add(new ExpenseCategory("Transportation"));
    }

    public static List<ExpenseCategory> getCategories()
    {
        return categories;
    }

    public static ExpenseCategories getInstance() {
        if(instance==null)
            instance = new ExpenseCategories();
        return instance;
    }
}
