package com.demo;

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

import com.adapters.ContactAdapter;
import com.entities.Contact;
import com.helpers.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listViewContact;
    private EditText etKeyword;
    private Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        LoadData();
    }

    private void InitView(){
        etKeyword = findViewById(R.id.etKeyword);
        btnSearch = findViewById(R.id.buttonSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch_onClick(v);
            }
        });
        listViewContact = findViewById(R.id.listViewContact);
        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewContact_onItemClick(parent,view,position,id);
            }
        });
    }
    public void btnSearch_onClick(View v) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Contact> contacts = databaseHelper.search(etKeyword.getText().toString());
        if(contacts!=null){
            listViewContact.setAdapter(new ContactAdapter(getApplicationContext(),R.layout.contact_item_layout, contacts));
        }else{
            listViewContact.setAdapter(null);
        }
    }
    public void listViewContact_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent  = new Intent(MainActivity.this,ContactDetailActivity.class);
        Contact contact = (Contact) parent.getItemAtPosition(position);
        intent.putExtra("contact",contact);
        startActivity(intent);
    }
    private void LoadData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        List<Contact> contacts = databaseHelper.findAll();
        if(contacts!=null){
        listViewContact.setAdapter(new ContactAdapter(getApplicationContext(),R.layout.contact_item_layout, contacts));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
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
        Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
        startActivity(intent);
    }
}
