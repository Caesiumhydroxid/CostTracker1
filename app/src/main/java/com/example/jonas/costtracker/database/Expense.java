package com.example.jonas.costtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.jonas.costtracker.database.converters.CalenderTypeConverter;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "expense_table")
public class Expense {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @TypeConverters({CalenderTypeConverter.class})
    private Calendar time;

    @NonNull
    private String seller;

    private String cashierId;

    @NonNull
    private long amount;

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public Calendar getTime() {
        return time;
    }


    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    @NonNull
    public String getSeller() {
        return seller;
    }

    @NonNull
    public long getAmount() {
        return amount;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setTime(@NonNull Calendar time) {
        this.time = time;
    }

    public void setSeller(@NonNull String seller) {
        this.seller = seller;
    }

    public void setAmount(@NonNull long amount) {
        this.amount = amount;
    }
}
