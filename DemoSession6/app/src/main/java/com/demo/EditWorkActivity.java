package com.demo;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.entities.Work;
import com.fragments.DatePickerFragment;
import com.helpers.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditWorkActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText editTextName, editTextDescription, editTextDate;
    private Spinner spinnerPriority;
    private Button btnSave, btnDate;
    private Work work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        initView();
        loadData();
    }
    private void initView(){
        editTextDate = findViewById(R.id.editTextDate);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextName = findViewById(R.id.editTextName);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_onClick(v);
            }
        });
        btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate_onClick(v);
            }
        });
    }

    public void btnSave_onClick(View v) {
        work.setPriority(spinnerPriority.getSelectedItem().toString());
        work.setCreationDate(editTextDate.getText().toString());
        work.setDescription(editTextDescription.getText().toString());
        work.setName(editTextName.getText().toString());
        DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
        if(databaseHelper.update(work)){
            Intent intent = new Intent(EditWorkActivity.this,WorkActivity.class);
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
    public void btnDate_onClick(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(),"Date Picker");
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE,dayOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(c.getTime());
        editTextDate.setText(currentDate);
    }
    private void loadData(){
        Intent intent = getIntent();
        work = (Work) intent.getSerializableExtra("work");
        editTextDate.setText(work.getCreationDate());
        editTextDescription.setText(work.getDescription());
        editTextName.setText(work.getName());
        List<String> priorities = new ArrayList<String>();
        priorities.add("Priority 1");
        priorities.add("Priority 2");
        priorities.add("Priority 3");
        spinnerPriority.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,priorities));
    }
}
