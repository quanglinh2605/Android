package com.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adapters.WeatherAdapter;
import com.entities.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private ListView ListViewWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        loadData();
    }
    private void initView(){
        ListViewWeather = findViewById(R.id.listViewWeather);
        ListViewWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewWeather_onItemClick(parent,view,position,id);
            }
        });
    }
    public void ListViewWeather_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(WeatherActivity.this,WeatherDetailActivity.class);
        Weather weather = (Weather) parent.getItemAtPosition(position);
        intent.putExtra("weather",weather);
        startActivity(intent);
    }
    private void loadData(){
        List<Weather> weathers = new ArrayList<Weather>();
        weathers.add(new Weather(R.drawable.snow,"Snowing","Berlin",0));
        weathers.add(new Weather(R.drawable.thunderstorm,"Thunderstorm","Bangalore",23));
        weathers.add(new Weather(R.drawable.rainy,"Rainy","London",5));
        weathers.add(new Weather(R.drawable.clouds,"Cloudy","New York",18));
        weathers.add(new Weather(R.drawable.sun,"Sunny","Sydney",32));
        WeatherAdapter weatherAdapter = new WeatherAdapter(getApplicationContext(),R.layout.weather_item_layout,weathers);
        ListViewWeather.setAdapter(weatherAdapter);
    }
}
