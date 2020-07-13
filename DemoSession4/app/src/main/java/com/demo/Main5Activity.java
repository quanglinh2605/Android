package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.adapters.ProductAdapter;
import com.adapters.ProductGridAdapter;
import com.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {
    private GridView gridViewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
        loadData();
    }
    public void initView(){
        gridViewProduct = findViewById(R.id.gridViewProduct);

    }
    private void  gridViewProduct_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        Intent intent = new Intent(Main5Activity.this,ProductDetailsActivity.class);
        intent.putExtra("product",product);
        startActivity(intent);
    }
    private void loadData(){
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1,"product 1", 5, R.drawable.image1,"description 1"));
        products.add(new Product(2,"product 2", 4.5, R.drawable.image2,"description 2"));
        products.add(new Product(3,"product 3", 7.5, R.drawable.image3,"description 3"));
        products.add(new Product(4,"product 4", 7.5, R.drawable.image3,"description 3"));
        products.add(new Product(5,"product 5", 7.5, R.drawable.image1,"description 3"));
        products.add(new Product(6,"product 6", 7.5, R.drawable.image3,"description 3"));
        products.add(new Product(7,"product 7", 7.5, R.drawable.image2,"description 3"));
        products.add(new Product(8,"product 8", 7.5, R.drawable.image2,"description 3"));
        products.add(new Product(9,"product 9", 7.5, R.drawable.image1,"description 3"));
        ProductGridAdapter productGridAdapter = new ProductGridAdapter(getApplicationContext(),R.layout.product_grid_item_layout,products);
        gridViewProduct.setAdapter(productGridAdapter);
    }
}
