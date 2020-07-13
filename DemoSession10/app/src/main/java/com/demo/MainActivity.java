package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listViewNames;
    private List<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }
    private void initView(){
        listViewNames = findViewById(R.id.listViewNames);
        registerForContextMenu(listViewNames);
    }
    private void loadData(){
        names = new ArrayList<String>();
        names.add("Name 1");
        names.add("Name 2");
        names.add("Name 3");
        names.add("Name 4");
        names.add("Name 5");
        listViewNames.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, contextMenu);
        super.onCreateContextMenu(contextMenu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu1){
            menu1Selected(item);
        }else if(item.getItemId()== R.id.menu2){
            menu2Selected(item);
        }else if(item.getItemId() == R.id.menu3){
            menu3Selected(item);
        }
        return super.onContextItemSelected(item);
    }
    private void menu1Selected(MenuItem menu){
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) menu.getMenuInfo();
        int position = contextMenuInfo.position;
        Toast.makeText(getApplicationContext(),names.get(position),Toast.LENGTH_LONG).show();
    }

    private void menu2Selected(MenuItem menu){
        Toast.makeText(getApplicationContext(),menu.getTitle(),Toast.LENGTH_LONG).show();
    }

    private void menu3Selected(MenuItem menu){
        Toast.makeText(getApplicationContext(),menu.getTitle(),Toast.LENGTH_LONG).show();
    }
}
