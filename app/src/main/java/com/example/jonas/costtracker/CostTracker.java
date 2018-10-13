package com.example.jonas.costtracker;

import android.Manifest;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.costtracker.database.Expense;
import com.example.jonas.costtracker.database.ExpenseListAdapter;
import com.example.jonas.costtracker.database.ExpenseViewModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class CostTracker extends AppCompatActivity implements QrScanFragment.OnCompleteListener{

    private TextView mTextMessage;
    private ImageView mImageView;
    private ExpenseViewModel expenseViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.enter,R.anim.enter);

                    Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    QrScanFragment dialogFragment = new QrScanFragment();
                    dialogFragment.onAttach((Context)CostTracker.this);
                    dialogFragment.show(getSupportFragmentManager(),"dialog");
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_tracker);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        final ExpenseListAdapter adapter = new ExpenseListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable final List<Expense> expenses) {
                // Update the cached copy of the words in the adapter.
                adapter.setExpenses(expenses);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void reactToIncompatibleCode()
    {
        return;
    }
    @Override
    public void onComplete(String dataFromQR) {
        String data[]=dataFromQR.split("_");
        if(data.length!=13)
            reactToIncompatibleCode();

        BigDecimal balance = new BigDecimal(0);
        DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(Locale.GERMAN);
        decimalFormat.setParseBigDecimal(true);
        for(int i=5;i<9;i++)
        {
            BigDecimal bigDecimal = null;
            try {
                bigDecimal = (BigDecimal)decimalFormat.parse(data[i]);
            } catch (ParseException e) {
                reactToIncompatibleCode();
            }
            balance = balance.add(bigDecimal);
        }
        Toast.makeText(getApplicationContext(),balance.toPlainString(),Toast.LENGTH_LONG).show();
    }
}
