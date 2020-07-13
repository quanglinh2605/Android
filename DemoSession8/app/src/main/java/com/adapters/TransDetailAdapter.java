package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.R;
import com.entities.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.List;

public class TransDetailAdapter extends ArrayAdapter<TransactionDetails> {

    private Context context;
    private int layout;
    private List<TransactionDetails> details;

    public TransDetailAdapter(@NonNull Context context, int resource, @NonNull List<TransactionDetails> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.details = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(this.layout,null);
        TextView textViewAcc = view.findViewById(R.id.textViewAcc);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TransactionDetails transactionDetails = details.get(position);
        textViewAcc.setText(transactionDetails.getAccid()+"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        textViewDate.setText(simpleDateFormat.format(transactionDetails.getDateoftrans()));
        return view;
    }
}
