package com.demo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.adapters.WorkAdapter;
import com.entities.Work;
import com.fragments.DatePickerFragment;
import com.helpers.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkActivity extends AppCompatActivity {
    private Button btnSearch, btnFind;
    private EditText etKeyword, etMax, etMin;
    private ListView listViewWork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        setTitle(R.string.work_app);
        initView();
        loadData();
    }
    private void initView(){
        etKeyword = findViewById(R.id.etKeyword);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch_onClick(v);
            }
        });
        etMax = findViewById(R.id.editTextMax);
        etMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMax_onClick(v);
            }
        });
        etMin = findViewById(R.id.editTextMin);
        etMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMin_onClick(v);
            }

        });
        btnFind = findViewById(R.id.buttonFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFind_onClick(v);
            }
        });
        listViewWork = findViewById(R.id.listViewWork);
        listViewWork.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewWork_onItemClick(parent,view,position,id);
            }
        });
    }

    public void etMin_onClick(View v){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                etMin.setText(simpleDateFormat.format(c.getTime()));
            }
        }, year, month, date );
        datePickerDialog.show();
    }

    public void etMax_onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                etMax.setText(simpleDateFormat.format(c.getTime()));
            }
        }, year, month, date );
        datePickerDialog.show();
    }

    public void btnFind_onClick(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Work> works = databaseHelper.find(etMin.getText().toString(),etMax.getText().toString());
        if(works!=null){
            listViewWork.setAdapter(new WorkAdapter(getApplicationContext(),R.layout.work_item_layout,works));
        }else {
            listViewWork.setAdapter(null);
        }
    }

    public void btnSearch_onClick(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Work> works = databaseHelper.Search(etKeyword.getText().toString());
        if(works!=null){
            listViewWork.setAdapter(new WorkAdapter(getApplicationContext(),R.layout.work_item_layout,works));
        }else {
            listViewWork.setAdapter(null);
        }
    }
    public void listViewWork_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(WorkActivity.this, WorkDetailActivity.class);
        Work work = (Work) parent.getItemAtPosition(position);
        intent.putExtra("work",  work);
        startActivity(intent);
    }
    private void loadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Work> works = databaseHelper.listAll();
        if(works!=null){
            listViewWork.setAdapter(new WorkAdapter(getApplicationContext(),R.layout.work_item_layout,works));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.work_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            menuAddSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void menuAddSelected(MenuItem item){
        Intent intent = new Intent(WorkActivity.this, AddWorkActivity.class);
        startActivity(intent);
    }

}
