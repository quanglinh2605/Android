package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adapters.ProductAdapter;
import com.apis.APIClient;
import com.apis.ProductAPI;
import com.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    private EditText editTextName,editTextPrice,editTextDescription;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
    }
    private void initView(){
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_onClick(v);
            }
        });
    }
    public void btnSave_onClick(View v) {
        Product product = new Product();
        product.setDescription(editTextDescription.getText().toString());
        product.setName(editTextName.getText().toString());
        product.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.save(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(AddProductActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),R.string.fail,Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
