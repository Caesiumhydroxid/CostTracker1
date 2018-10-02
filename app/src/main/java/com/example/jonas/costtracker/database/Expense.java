package com.example.jonas.costtracker.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity(tableName = "expense_table")
public class Expense {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private int time;

    @NonNull
    private String seller;

    @NonNull
    private int amount;

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public int getTime() {
        return time;
    }

    @NonNull
    public String getSeller() {
        return seller;
    }

    @NonNull
    public int getAmount() {
        return amount;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setTime(@NonNull int time) {
        this.time = time;
    }

    public void setSeller(@NonNull String seller) {
        this.seller = seller;
    }

    public void setAmount(@NonNull int amount) {
        this.amount = amount;
    }
}
