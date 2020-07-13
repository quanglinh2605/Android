package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {

    private CheckBox checkbox;
    private Switch switch_wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
    }

    public void selectStatus(View v){
        CheckBox cbStatus = (CheckBox)findViewById(R.id.cbStatus);
        boolean status = cbStatus.isChecked();
        Toast.makeText(getApplicationContext(),String.valueOf(status),Toast.LENGTH_LONG).show();
    }
    public void initView(){
        checkbox = (CheckBox)findViewById(R.id.cbStatus);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton btnView, boolean isChecked) {
                cbStatus_onchecked(btnView,isChecked);
            }
        });
        switch_wifi = (Switch)findViewById(R.id.switch_wifi);
        switch_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton btnView, boolean isChecked) {
                switch_wifi_onChecked(btnView,isChecked);
            }
        });
    }

    public void selectGender(View v){
        RadioGroup rgGender = (RadioGroup)findViewById(R.id.rgGender);
        int radioIdSelected = rgGender.getCheckedRadioButtonId();
        RadioButton rbGender = (RadioButton)findViewById(radioIdSelected);
        String value = rbGender.getText().toString();
        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
    }

    public void switch_wifi_onChecked(CompoundButton btnView, boolean isChecked) {
        if(isChecked){
            Toast.makeText(getApplicationContext(),"Wifi On",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Wifi Off",Toast.LENGTH_LONG).show();
        }
    }
    public void cbStatus_onchecked(CompoundButton btnView,boolean isChecked){
        Button btnStatus = (Button)findViewById(R.id.btnStatus);
        if(isChecked){
            btnStatus.setEnabled(true);
        }else{
            btnStatus.setEnabled(false);
        }
    }
}

