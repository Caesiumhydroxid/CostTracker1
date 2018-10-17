package com.example.jonas.costtracker.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Calendar;

@Database(entities = {Expense.class}, version = 3)
public abstract class ExpenseRoomDatabase extends RoomDatabase {

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ExpenseDao mDao;

        PopulateDbAsync(ExpenseRoomDatabase db) {
            mDao = db.expenseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            Expense expense = new Expense();
            expense.setSeller("Test");
            expense.setTime(Calendar.getInstance());
            expense.setCashierId("1234");
            mDao.insert(expense);
            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    public abstract ExpenseDao expenseDao();
    private static volatile ExpenseRoomDatabase INSTANCE;

    static ExpenseRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExpenseRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
