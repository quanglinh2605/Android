package com.demo;

import androidx.annotation.NonNull;
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

import com.entities.Work;
import com.helpers.DatabaseHelper;

public class WorkDetailActivity extends AppCompatActivity {
    private TextView textViewName,textViewDescription, textViewPriority, textViewDate;
    private Work work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);
        initView();
        loadData();
    }
    private void initView(){
        textViewDate = findViewById(R.id.textViewDate);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewName = findViewById(R.id.textViewName);
        textViewPriority = findViewById(R.id.textViewPriority);
    }
    private void loadData(){
        Intent intent = getIntent();
        work = (Work) intent.getSerializableExtra("work");
            textViewPriority.setText(work.getPriority());
        textViewName.setText(work.getName());
        textViewDescription.setText(work.getDescription());
        textViewDate.setText(work.getCreationDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.work_detail_menu,menu);
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
            if(databaseHelper.erase(work.getId())){
                Intent intent = new Intent(WorkDetailActivity.this,WorkActivity.class);
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
        Intent intent = new Intent(WorkDetailActivity.this, EditWorkActivity.class);
        intent.putExtra("work", work);
        startActivity(intent);
    }
}
