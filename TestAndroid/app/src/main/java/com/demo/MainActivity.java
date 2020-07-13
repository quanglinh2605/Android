package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.adapters.StudentAdapter;
import com.entities.Student;
import com.helpers.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private EditText editTextKeyword;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }
    private void initView()
    {
        listViewStudents = findViewById(R.id.listviewStudents);
        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewStudents_onItemClick(parent,view,position,id);
            }
        });
        editTextKeyword = findViewById(R.id.editTextKeyword);
        btnSearch = findViewById(R.id.buttonSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch_onClick(v);
            }
        });
    }

    public void listViewStudents_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
        Student student = (Student) parent.getItemAtPosition(position);
        intent.putExtra("student", student);
        startActivity(intent);
    }

    public void btnSearch_onClick(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Student> students = databaseHelper.search(editTextKeyword.getText().toString());
        if(students!=null) {
            StudentAdapter studentAdapter = new StudentAdapter(getApplicationContext(), R.layout.student_item_layout, students);
            listViewStudents.setAdapter(studentAdapter);
        }else{
            listViewStudents.setAdapter(null);
        }
    }

    private void loadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Student> students = databaseHelper.listAll();
        if(students!=null) {
            StudentAdapter studentAdapter = new StudentAdapter(getApplicationContext(), R.layout.student_item_layout, students);
            listViewStudents.setAdapter(studentAdapter);
        }else{
            listViewStudents.setAdapter(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            menuAdd_Selected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    private void menuAdd_Selected(MenuItem item){
        Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
        startActivity(intent);
    }
}
