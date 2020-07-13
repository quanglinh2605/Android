package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.entities.Weather;

public class WeatherDetailActivity extends AppCompatActivity {
    private EditText etStatus,etTemp;
    private TextView tvCity;
    private ImageView imageViewPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        initView();
    }
    private void initView(){
        etStatus = findViewById(R.id.editTextStatus);
        etTemp = findViewById(R.id.editTextTemp);
        tvCity = findViewById(R.id.textViewCity);
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        Intent intent = getIntent();
        Weather weather = (Weather) intent.getSerializableExtra("weather");
        etTemp.setText(weather.getTemp()+"");
        etStatus.setText(weather.getStatus());
        tvCity.setText(weather.getCity());
        imageViewPhoto.setImageResource(weather.getPhoto());
    }
}
