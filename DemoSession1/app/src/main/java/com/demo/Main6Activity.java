package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void Change(View v){
        double temp = Double.parseDouble(((EditText)findViewById(R.id.etTemper)).getText().toString());
        int id = ((RadioGroup)findViewById(R.id.rgChoice)).getCheckedRadioButtonId();
        RadioButton rbChoice = (RadioButton)findViewById(id);
        String value = rbChoice.getText().toString();
        double total =0;
        String result = "";
        if(value.equalsIgnoreCase("C to F")){
            total = temp*1.8 +32;
            result = total+"F";
        }else if(value.equalsIgnoreCase("F to C")){
            total = (temp-32)/1.8;
            result = total+"C";
        }
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
    }
}

