package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private GridView gridViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
        LoadData();
    }
    private void initView(){
       gridViewCategory = findViewById(R.id.gridViewCategory);
       gridViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               gridViewCategory_onItemClick(parent,view, position, id);
           }
       });
    }

    private void  gridViewCategory_onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
        Category.add("Category 1");
        Category.add("Category 2");
        Category.add("Category 3");
        Category.add("Category 4");
        Category.add("Category 5");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,Category);
        gridViewCategory.setAdapter(arrayAdapter);
    }
}
