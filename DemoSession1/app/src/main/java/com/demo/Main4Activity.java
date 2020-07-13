package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
    public void Login(View v){
        EditText etUsername =(EditText)findViewById(R.id.etUsername);
        EditText etPassword =(EditText)findViewById(R.id.etPassword);
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(username.equalsIgnoreCase("linh")& password.equalsIgnoreCase("123")){
            Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
        }
    }
}
