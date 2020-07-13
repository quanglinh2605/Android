package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.entities.Contact;

public class Main2Activity extends AppCompatActivity {
    private Button backButton;
    private TextView textViewStatus, textViewAge, textViewUsername, textViewPrice, tvName, tvPhone,tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle(R.string.main2);
        initView();
        loadData();
    }
    private void initView(){
        textViewAge = findViewById(R.id.textViewAge);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewUsername = findViewById(R.id.textViewUsername);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);

        backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back_onClick(v);
            }
        });
    }
    public void loadData(){
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Integer age = intent.getIntExtra("age",21);
        Double price = intent.getDoubleExtra("price",0);
        boolean status = intent.getBooleanExtra("status",true);
        textViewUsername.setText(username);
        textViewAge.setText(String.valueOf(age));
        textViewPrice.setText(String.valueOf(price));
        textViewStatus.setText(String.valueOf(status));

        Contact contact = (Contact) intent.getSerializableExtra("contact");
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());
        tvAddress.setText(contact.getAddress());
    }
    public void Back_onClick(View v){
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);
    }
}
