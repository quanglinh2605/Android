package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.adapters.ProductAdapter;
import com.apis.APIClient;
import com.apis.ProductAPI;
import com.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listViewProduct;
    private Button btnSearch, btnFind;
    private EditText etKeyword,etMin,etMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }
    private void initView(){
        listViewProduct = findViewById(R.id.listViewProduct);
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detail_onItemClick(parent,view,position,id);
            }
        });
        etKeyword = findViewById(R.id.etkeyword);
        btnSearch = findViewById(R.id.buttonSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch_onClick(v);
            }
        });
        etMax = findViewById(R.id.etMax);
        etMin = findViewById(R.id.etMin);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFind_onClick(v);
            }
        });
    }

    public void detail_onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this,ProductDetailActivity.class);
        Product product = (Product) parent.getItemAtPosition(position);
        intent.putExtra("product",product);
        startActivity(intent);
    }

    public void btnFind_onClick(View v) {
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.find(Double.parseDouble(etMax.getText().toString()),Double.parseDouble(etMin.getText().toString())).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                try {
                    List<Product> products = response.body();
                    if(products!=null) {
                        listViewProduct.setAdapter(new ProductAdapter(getApplicationContext(), R.layout.product_item_layout, products));
                    }else{
                        listViewProduct.setAdapter(null);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void btnSearch_onClick(View v) {
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.search(etKeyword.getText().toString()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                try {
                    List<Product> products = response.body();
                    if(products!=null) {
                        listViewProduct.setAdapter(new ProductAdapter(getApplicationContext(), R.layout.product_item_layout, products));
                    }else{
                        listViewProduct.setAdapter(null);
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadData(){
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.findAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                try {
                    List<Product> products = response.body();
                    listViewProduct.setAdapter(new ProductAdapter(getApplicationContext(), R.layout.product_item_layout, products));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.product_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_add){
            menuAddSelected();
        }
        return super.onOptionsItemSelected(item);
    }
    private void menuAddSelected(){
        Intent intent = new Intent(MainActivity.this,AddProductActivity.class);
        startActivity(intent);
    }
}
