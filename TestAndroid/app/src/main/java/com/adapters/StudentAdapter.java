package com.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.R;
import com.entities.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int layout;
    private List<Student> students;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.students = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(this.layout,null);
        TextView textViewMaSV = view.findViewById(R.id.textViewMaSV);
        TextView textViewTenSV = view.findViewById(R.id.textViewTenSV);
        Student student = students.get(position);
        textViewMaSV.setText(student.getMaSV());
        textViewTenSV.setText(student.getTenSV());
        return view;
    }
}
