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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_tracker);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
// Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText("Test1"));
        tabLayout.addTab(tabLayout.newTab().setText("Test2"));
        tabLayout.addTab(tabLayout.newTab().setText("Test3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



    private void startScanningDialog()
    {
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
