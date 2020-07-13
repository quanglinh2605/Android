package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.Adapters.ContactSearchAdapter;
import com.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextViewNames;
    private AutoCompleteTextView autoCompleteTextViewContacts;
    private List<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        loadData();
    }
    private void initView(){
        autoCompleteTextViewNames = findViewById(R.id.autoCompleteTextViewNames);
        autoCompleteTextViewNames.setThreshold(1);
        autoCompleteTextViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoCompleteTextViewNames_onItemClick(parent,view,position,id);
            }
        });
        autoCompleteTextViewContacts = findViewById(R.id.autoCompleteTextViewContact);
        autoCompleteTextViewContacts.setThreshold(1);
        autoCompleteTextViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoCompleteTextViewContacts_onItemClick(parent,view,position,id);
            }
        });
    }
    public void autoCompleteTextViewNames_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
    }
    public void autoCompleteTextViewContacts_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) parent.getItemAtPosition(position);
        autoCompleteTextViewContacts.setText(contact.getName());
    }

    private void loadData(){

        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Peter","4564564", R.drawable.image1));
        contacts.add(new Contact("Marry","78976543", R.drawable.image2));
        contacts.add(new Contact("Kelvin","1212121", R.drawable.image1));
        contacts.add(new Contact("David","3313232", R.drawable.image3));
        ContactSearchAdapter contactSearchAdapter = new ContactSearchAdapter(getApplicationContext(),R.layout.contact_search_layout,contacts);
        autoCompleteTextViewContacts.setAdapter(contactSearchAdapter);

        List<String> names = new ArrayList<String>();
        names.add("Marry");
        names.add("Peter");
        names.add("Kelvin");
        names.add("Poster");
        names.add("David");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        autoCompleteTextViewNames.setAdapter(arrayAdapter);
    }
}
