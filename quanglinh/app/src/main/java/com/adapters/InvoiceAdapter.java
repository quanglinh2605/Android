package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.demo.R;
import com.entities.Invoice;

import java.text.SimpleDateFormat;
import java.util.List;

public class InvoiceAdapter extends ArrayAdapter<Invoice> {

    private Context context;
    private int layout;
    private List<Invoice> invoices;

    public InvoiceAdapter(Context context, int resource, List<Invoice> objects) {
        super(context, resource,objects);
        this.context = context;
        this.layout = resource;
        this.invoices = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        View view = LayoutInflater.from(this.context).inflate(this.layout,null);
        TextView tvid = view.findViewById(R.id.tvId);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPayment = view.findViewById(R.id.tvPayment);
        TextView tvCreated = view.findViewById(R.id.tvCreated);
        Invoice invoice = invoices.get(position);
        tvCreated.setText(simpleDateFormat.format(invoice.getCreated()));
        tvid.setText(invoice.getId());
        tvName.setText(invoice.getName());
        tvPayment.setText(invoice.getPayment());
        return view;
    }
}
