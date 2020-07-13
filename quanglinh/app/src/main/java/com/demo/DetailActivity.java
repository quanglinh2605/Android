package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.adapters.ProductAdapter;
import com.entities.Invoice;

public class DetailActivity extends AppCompatActivity {
    private TextView tvName, tvId, tvAddress;
    private GridView gridViewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        loadData();
    }

    private void initView(){
        tvName = findViewById(R.id.editTextName);
        tvId = findViewById(R.id.editTextId);
        tvAddress = findViewById(R.id.editTextAddress);
        gridViewProduct = findViewById(R.id.gridViewProduct);
    }
    private void loadData(){
        Intent intent = getIntent();
        Invoice invoice = (Invoice) intent.getSerializableExtra("invoice");
        tvName.setText(invoice.getCustomer().getName());
        tvAddress.setText(invoice.getCustomer().getAddress());
        tvId.setText(invoice.getId());
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),R.layout.product_item_layout,invoice.getProducts());
        gridViewProduct.setAdapter(productAdapter);
    }
}
