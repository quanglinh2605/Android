package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void btnClickMe_onClick(View v){
        EditText etName = (EditText)findViewById(R.id.etName);
        Toast.makeText(getApplicationContext(),etName.getText().toString(),Toast.LENGTH_LONG).show();
    }
}
