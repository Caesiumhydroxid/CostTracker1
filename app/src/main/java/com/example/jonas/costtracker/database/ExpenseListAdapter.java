package com.example.jonas.costtracker.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.jonas.costtracker.R;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder>
{
    class ExpenseViewHolder extends RecyclerView.ViewHolder{
        private final TextView expenseItemShopName;
        private final ImageView expenseItemImageCategory;
        private final TextView expenseItemMoneyText;
        private final TextView expenseItemDateText;
        private ExpenseViewHolder(View itemView)
        {
            super(itemView);
            expenseItemShopName = itemView.findViewById(R.id.itemShopText);
            expenseItemImageCategory = itemView.findViewById(R.id.itemCategoryImage);
            expenseItemMoneyText = itemView.findViewById(R.id.itemMoneyText);
            expenseItemDateText = itemView.findViewById(R.id.itemDateText);
        }
    }
    private final LayoutInflater mInflater;
    private List<Expense> expenses; // Cached copy of words


    public ExpenseListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ExpenseViewHolder(itemView);
    }


    public void setExpenses(List<Expense> expenses){
        this.expenses = expenses;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder expenseViewHolder, int i) {
        if (expenses != null) {
            Expense current = expenses.get(i);
            if(current.getSeller()!=null)
                expenseViewHolder.expenseItemShopName.setText(current.getSeller());
            else
                expenseViewHolder.expenseItemShopName.setText("NULL");

            expenseViewHolder.expenseItemMoneyText.setText(Integer.toString(current.getAmount()));

            expenseViewHolder.expenseItemDateText.setText(Integer.toString(current.getTime()));
        } else {
            // Covers the case of data not being ready yet.
            expenseViewHolder.expenseItemShopName.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (expenses != null)
            return expenses.size();
        else return 0;
    }
}
