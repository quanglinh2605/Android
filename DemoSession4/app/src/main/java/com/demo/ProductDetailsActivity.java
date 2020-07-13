package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.entities.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView textViewName, textViewPrice,textViewDescription;
    private ImageView imageViewPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initView();
    }
    private void initView(){
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewName = findViewById(R.id.textViewName);
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");
        textViewName.setText(product.getName());
        textViewDescription.setText(product.getDescription());
        textViewPrice.setText(String.valueOf(product.getPrice()));
        imageViewPhoto.setImageResource(product.getPhoto());
    }
}
