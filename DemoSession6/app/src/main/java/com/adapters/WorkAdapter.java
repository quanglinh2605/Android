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
import com.entities.Contact;
import com.entities.Work;

import java.util.List;

public class WorkAdapter extends ArrayAdapter<Work> {
    private Context context;
    private int layout;
    private List<Work> works;
    public WorkAdapter(Context context, int resource, List<Work> objects) {
        super(context, resource, objects);
        this.context = context;
        this.works = objects;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(this.layout,null);
        TextView tvName = view.findViewById(R.id.tvName);
        Work work = works.get(position);
        tvName.setText(work.getName());
        return view;
    }
}
