package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.entities.Contact;

public class MainActivity extends AppCompatActivity {
    private Button ButtonChangePhoto;
    private ImageView imageViewPhoto;
    private Button ButtonOpenMain2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button_ChangePhoto(View v){
        imageViewPhoto = findViewById(R.id.imageViewphoto1);
        ButtonChangePhoto = findViewById(R.id.buttonChangePhoto);
        ButtonChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePhoto_onClick(v);
            }
        });
    }
    public void ChangePhoto_onClick(View v) {
        imageViewPhoto.setImageResource(R.drawable.image3);
    }

    public void button_OpenMain2(View v){
        ButtonOpenMain2 = findViewById(R.id.buttonopenmain2);
        ButtonOpenMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenMain2_onClick(v);
            }
        });
    }
    public void OpenMain2_onClick(View v) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("username","Linh");
        intent.putExtra("age",21);
        intent.putExtra("price",14.3);
        intent.putExtra("status",true);
        Contact contact = new Contact("name 1","0963756348","Nguyen Trai");
        intent.putExtra("contact",contact);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu1){
            menu1SelectedItem();
        }
        else if(item.getItemId() == R.id.menu2){
            menu2SelectedItem();
        }
        else if(item.getItemId() == R.id.menu3_1){
            menu3_1SelectedItem();
        }
        else if(item.getItemId() == R.id.menu3_2){
            menu3_2SelectedItem();
        }
        else if(item.getItemId()==R.id.login){
            loginSelectedItem();
        }
        return super.onOptionsItemSelected(item);
    }
    private void menu1SelectedItem(){
        Toast.makeText(getApplicationContext(),"Menu 1 is selected",Toast.LENGTH_LONG).show();
    }
    private void menu2SelectedItem(){
        Toast.makeText(getApplicationContext(),"Menu 2 is selected",Toast.LENGTH_LONG).show();
    }
    private void menu3_1SelectedItem(){
        Toast.makeText(getApplicationContext(),"Menu 3_1 is selected",Toast.LENGTH_LONG).show();
    }
    private void menu3_2SelectedItem(){
        Toast.makeText(getApplicationContext(),"Menu 3_2 is selected",Toast.LENGTH_LONG).show();
    }
    private void loginSelectedItem(){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
