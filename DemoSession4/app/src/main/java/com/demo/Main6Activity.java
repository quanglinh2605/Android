package com.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {
    private Button btnInfoDialog, btnConfirmDialog, btnCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        initView();
    }
    public void initView(){
        btnInfoDialog = findViewById(R.id.buttonInfoDialog);
        btnInfoDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInfoDialog_onClick(v);
            }
        });
        btnConfirmDialog = findViewById(R.id.buttonConfirmDialog);
        btnConfirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnConfirmDialog_onClick(v);
            }
        });
        btnCustomDialog = findViewById(R.id.buttonCustomDialog);
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCustomDialog_onClick(v);
            }
        });
    }
    public void btnCustomDialog_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        View view = LayoutInflater.from(v.getContext()).inflate(R.layout.login_dialog_layout,null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.show();
        final EditText etUsername = view.findViewById(R.id.etUsername);
        final EditText etPassword = view.findViewById(R.id.etPassword);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if(username.equalsIgnoreCase("linh")&&password.equalsIgnoreCase("123")){
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                }

               alertDialog.dismiss();
            }
        });
    }
    public void btnConfirmDialog_onClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.are_you_sure);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
    public void btnInfoDialog_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle(R.string.information);
        builder.setMessage(R.string.done);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
