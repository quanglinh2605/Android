package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class Main3Activity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    @Length(min=3, max = 10)
    private EditText editTextUsername;

    @NotEmpty
    @Pattern(regex = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private EditText editTextPassword;

    @Max(60)
    @Min(15)
    private EditText editTextAge;

    private Button btnSave;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }
    private void initView(){
        validator = new Validator(this);
        validator.setValidationListener(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAge = findViewById(R.id.editTextAge);

        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_onClick(v);
            }
        });
    }
    public void btnSave_onClick(View v) {
        validator.validate();
        String username = editTextUsername.getText().toString();
        if(username.equalsIgnoreCase("abc")){
            editTextUsername.setError(getText(R.string.exist));
        }
        //Toast.makeText(getApplicationContext(),editTextUsername.getText().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(getApplicationContext(), R.string.done, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors){
        for (ValidationError error: errors) {
            String message = error.getCollatedErrorMessage(this);
            View view = error.getView();
            if(view instanceof EditText){
                EditText editText = (EditText) view;
                editText.setError(message);
            }else{
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
