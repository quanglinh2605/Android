package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
    }

    public void show(View v){
        EditText tv1 = (EditText)findViewById(R.id.tv1);
        TextView result1 = (TextView)findViewById(R.id.result1);
        result1.setText(tv1.getText());
    }
}
