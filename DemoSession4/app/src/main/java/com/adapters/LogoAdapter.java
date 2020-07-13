package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.R;
import com.entities.Logo;

import java.util.List;

public class LogoAdapter extends ArrayAdapter<Logo> {

    private Context context;
    private int layout;
    private List<Logo> logos;

    public LogoAdapter(Context context, int resource, List<Logo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.logos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(this.layout,null);

        ImageView imageViewLogo = view.findViewById(R.id.imageViewLogo);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewMoney = view.findViewById(R.id.textViewMoney);

        Logo logo = logos.get(position);

        imageViewLogo.setImageResource(logo.getPhoto());
        textViewName.setText(logo.getName());
        textViewMoney.setText(logo.getMoney());

        return view;
    }
}
