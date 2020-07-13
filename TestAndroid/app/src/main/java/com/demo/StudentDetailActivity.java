package com.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.entities.Student;
import com.helpers.DatabaseHelper;

public class StudentDetailActivity extends AppCompatActivity {
    private TextView textViewTenSV, textViewMaSV,textViewDiem,textViewFalcuty, textViewGender;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        initView();
        LoadData();
    }
    private void initView(){
        textViewDiem = findViewById(R.id.textViewDiem);
        textViewFalcuty = findViewById(R.id.textViewFalcuty);
        textViewGender = findViewById(R.id.textViewGender);
        textViewTenSV = findViewById(R.id.textViewTenSV);
        textViewMaSV = findViewById(R.id.textViewMaSV);
    }
    private void LoadData(){
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        textViewTenSV.setText(student.getTenSV());
        textViewGender.setText(student.getGioitinh());
        textViewFalcuty.setText(student.getChuyennganh());
        textViewDiem.setText(String.valueOf(student.getDiem()));
        textViewMaSV.setText(student.getMaSV());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.student_detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_delete){
            deleteSelectedMenu(item);
        }
        if(item.getItemId() == R.id.menu_edit){
            editSelectedItem(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteSelectedMenu(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage(R.string.are_you_sure);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSelectedMenu_AlertDialog_OK_onClick(dialog,which);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void deleteSelectedMenu_AlertDialog_OK_onClick(DialogInterface dialog, int which) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            if(databaseHelper.delete(student.getMaSV())){
                Intent intent = new Intent(StudentDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error);
                builder.setMessage("Can't Delete");
                builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void editSelectedItem(MenuItem item){
        Intent intent = new Intent(StudentDetailActivity.this, EditStudentActivity.class);
        intent.putExtra("student", student);
        startActivity(intent);
    }
}
