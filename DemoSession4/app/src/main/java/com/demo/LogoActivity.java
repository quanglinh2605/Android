package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.adapters.LogoAdapter;
import com.models.LogoModel;

public class LogoActivity extends AppCompatActivity {
    private GridView gridViewLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        InitView();
        LoadData();
    }
    private void InitView(){
        gridViewLogo = findViewById(R.id.gridViewLogo);
    }
    private void LoadData(){

        LogoAdapter logoAdapter = new LogoAdapter(getApplicationContext(),R.layout.logo_item_layout,new LogoModel().findAll());
        gridViewLogo.setAdapter(logoAdapter);
    }
}
