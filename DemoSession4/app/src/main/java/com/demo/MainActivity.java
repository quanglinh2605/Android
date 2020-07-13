package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LoadData();
    }

    private void initView(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_onItemSelected(parent,view,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void btnSelected_onClick(View v){
        String category = spinner.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),category,Toast.LENGTH_LONG).show();
    }
    private void spinner_onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String category = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),category,Toast.LENGTH_LONG).show();
    }

    private void LoadData(){
        List<String> Category = new ArrayList<String>();
        Category.add("Category 1");
        Category.add("Category 2");
        Category.add("Category 3");
        Category.add("Category 4");
        Category.add("Category 5");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,Category);
        spinner.setAdapter(arrayAdapter);
    }
}
