package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.adapters.ProductAdapter;
import com.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private ListView listViewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        loadData();
    }
    private void initView(){
        listViewProduct = findViewById(R.id.ListViewProduct);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewProduct_onItemClick(parent, view, position, id);
            }
        });
    }
    public void listViewProduct_onItemClick(AdapterView<?> parent, View view, int position, long id){
        Product product = (Product) parent.getItemAtPosition(position);
        Intent intent = new Intent(Main3Activity.this, ProductDetailsActivity.class);
        intent.putExtra("product",product);
        startActivity(intent);
    }
    private void loadData(){
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(3,"product 3", 7.5, R.drawable.image3,"description 3"));
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),R.layout.product_item_layout,products);
        listViewProduct.setAdapter(productAdapter);
    }
}
