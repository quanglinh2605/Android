package com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apis.APIClient;
import com.apis.ProductAPI;
import com.entities.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductActivity extends AppCompatActivity {
    private EditText editTextName,editTextPrice,editTextDescription;
    private Button btnSave;
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
        loadData();
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
        product.setDescription(editTextDescription.getText().toString());
        product.setName(editTextName.getText().toString());
        product.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.update(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(EditProductActivity.this,MainActivity.class);
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
    private void loadData(){
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
        editTextDescription.setText(product.getDescription());
        editTextName.setText(product.getName());
        editTextPrice.setText(product.getPrice()+"");
    }
}
