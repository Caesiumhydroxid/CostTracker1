package com.example.jonas.costtracker;

import android.graphics.drawable.Drawable;

public class ExpenseCategory {
    public Drawable icon;
    public String name;

    public ExpenseCategory(String name) {
        this.name = name;
    }

    public ExpenseCategory(String name, Drawable icon) {
        this.icon = icon;
        this.name = name;
    }
}
