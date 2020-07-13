package com.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.entities.Student;
import com.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextMaSV, editTextTenSV, editTextDiem;
    private Spinner spinnerGender, spinnerFalcuty;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
        loadData();
    }
    private void initView(){
        editTextDiem = findViewById(R.id.editTextDiem);
        editTextMaSV = findViewById(R.id.editTextMaSV);
        editTextTenSV = findViewById(R.id.editTextTenSV);
        spinnerFalcuty = findViewById(R.id.spinnerFalcuty);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_onClick(v);
            }
        });
    }
    public void btnSave_onClick(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
        Student student = new Student();
        student.setMaSV(editTextMaSV.getText().toString());
        student.setTenSV(editTextTenSV.getText().toString());
        student.setGioitinh(spinnerGender.getSelectedItem().toString());
        student.setDiem(Double.parseDouble(editTextDiem.getText().toString()));
        student.setChuyennganh(spinnerFalcuty.getSelectedItem().toString());
        if(databaseHelper.create(student)){
            Intent intent = new Intent(AddStudentActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.fail);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
    }
    private void loadData(){
        List<String> genders = new ArrayList<String>();
        genders.add("Nam");
        genders.add("Nu");
        spinnerGender.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, genders));
        List<String> falcuties = new ArrayList<String>();
        falcuties.add("IT");
        falcuties.add("Vat ly");
        falcuties.add("Sinh hoc");
        spinnerFalcuty.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, falcuties));
    }
}
