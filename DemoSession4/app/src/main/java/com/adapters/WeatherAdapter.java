package com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.demo.R;
import com.entities.Weather;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Weather> {
    private Context context;
    private int layout;
    private List<Weather> weathers;
    public WeatherAdapter(Context context, int resource,List<Weather> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.weathers = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View view = LayoutInflater.from(this.context).inflate(this.layout,null);
        Weather weather = weathers.get(position);
        ImageView imageViewWeather = view.findViewById(R.id.imageViewWeather);
        TextView etCity = view.findViewById(R.id.tvCity);
        TextView tvStatus = view.findViewById(R.id.tvStatus);
        TextView etTemp = view.findViewById(R.id.tvTemp);
        imageViewWeather.setImageResource(weather.getPhoto());
        etCity.setText(weather.getCity());
        etTemp.setText(weather.getTemp()+"");
        tvStatus.setText(weather.getStatus());
        return view;
    }
}
