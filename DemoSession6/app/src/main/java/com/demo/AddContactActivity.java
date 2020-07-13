package com.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.entities.Contact;
import com.helpers.DatabaseHelper;

public class AddContactActivity extends AppCompatActivity {
    private EditText etName,etPhone,etAddress,etEmail,etDes;
    private Button btnSave, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setTitle(R.string.add_contact);
        initView();

    }
    private void initView(){
        etAddress = findViewById(R.id.etAddress);
        etDes = findViewById(R.id.etDes);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        btnCancel = findViewById(R.id.buttonCancel);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            btnSave_onClick(v);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancel_onClick(v);
            }
        });
    }
    public void btnSave_onClick(View v) {
        Contact contact = new Contact();
        contact.setAddress(etAddress.getText().toString());
        contact.setDescription(etDes.getText().toString());
        contact.setEmail(etEmail.getText().toString());
        contact.setName(etName.getText().toString());
        contact.setPhone(etPhone.getText().toString());
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        if (databaseHelper.create(contact)){
            Intent intent = new Intent(AddContactActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.fail);
            builder.show();
        }
    }
    public void btnCancel_onClick(View v){
        Intent intent = new Intent(AddContactActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
