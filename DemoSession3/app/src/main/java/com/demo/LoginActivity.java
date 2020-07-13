package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login);
        initView();
    }
    private void initView(){
        btnLogin = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin_onClick(v);
            }
        });
    }
    public void btnLogin_onClick(View v){
        if(username.getText().toString().equalsIgnoreCase("linh")&&password.getText().toString().equalsIgnoreCase("123")){
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            intent.putExtra("username", username.getText());
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
        }
    }
}
