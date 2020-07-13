package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.R;
import com.entities.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private int layout;
    private List<Product> products;

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource,objects);
        this.context = context;
        this.layout = resource;
        this.products = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(this.layout,null);
        ImageView imageViewPhoto = view.findViewById(R.id.imageViewPhoto);
        TextView tvName = view.findViewById(R.id.textViewName);
        TextView tvDescription = view.findViewById(R.id.textViewDescription);
        TextView tvPrice = view.findViewById(R.id.textViewPrice);
        Product product = products.get(position);
        imageViewPhoto.setImageResource(product.getPhoto());
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice()+"");
        tvDescription.setText(product.getDescription());
        return view;
    }
}
