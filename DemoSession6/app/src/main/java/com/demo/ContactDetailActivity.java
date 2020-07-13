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

import com.entities.Contact;
import com.helpers.DatabaseHelper;

public class ContactDetailActivity extends AppCompatActivity {
    private TextView textViewName, textViewAddress, textViewEmail, textViewPhone, textViewDes;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        initView();
        loadData();
    }
    private void initView(){
        textViewAddress = findViewById(R.id.textViewAdress);
        textViewDes = findViewById(R.id.textViewDescription);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewName = findViewById(R.id.textViewName);
        textViewPhone = findViewById(R.id.textViewPhone);
    }
    private void loadData(){
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");
        textViewPhone.setText(contact.getPhone());
        textViewName.setText(contact.getName());
        textViewEmail.setText(contact.getEmail());
        textViewDes.setText(contact.getDescription());
        textViewAddress.setText(contact.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contact_detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.menu_delete==item.getItemId()){
            MenuDeleteSelected(item);
        }
        else if(item.getItemId()==R.id.menu_edit){
            EditDeleteSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    private void MenuDeleteSelected(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage(R.string.are_you_sure);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMenuSelected_AlertDialog_OK_onClick(dialog,which);
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
    public void deleteMenuSelected_AlertDialog_OK_onClick(DialogInterface dialog, int which) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            if(databaseHelper.delete(contact.getId())){
                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.fail);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
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
    private void EditDeleteSelected(MenuItem item){
        Intent intent = new Intent(ContactDetailActivity.this,EditContactActivity.class);
        intent.putExtra("contact",contact);
        startActivity(intent);
    }
}
