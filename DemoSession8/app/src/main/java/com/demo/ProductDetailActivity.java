package com.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.apis.APIClient;
import com.apis.ProductAPI;
import com.entities.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView textViewName, textViewPrice, textViewDescription;
    private  Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        loadData();
    }
    private void initView(){
        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPrice = findViewById(R.id.textViewPrice);
    }
    private void loadData(){
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
        textViewName.setText(product.getName());
        textViewPrice.setText(String.valueOf(product.getPrice()));
        textViewDescription.setText(product.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.product_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_delete){
            deleteMenuSelected();
        }

        if(item.getItemId() == R.id.menu_edit){
            EditMenuSelected();
        }
        return super.onOptionsItemSelected(item);
    }

    private void EditMenuSelected(){
        Intent intent = new Intent(ProductDetailActivity.this, EditProductActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
    private void deleteMenuSelected(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage(R.string.are_you_ok);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMenuSelected_AlertDialog_OK_onClick(dialog, which);
            }
        });
        builder.show();
        }
    private void deleteMenuSelected_AlertDialog_OK_onClick(DialogInterface dialog, int which){
        ProductAPI productAPI = APIClient.getClient().create(ProductAPI.class);
        productAPI.delete(product.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(ProductDetailActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setCancelable(false);
                    builder.setTitle(R.string.error);
                    builder.setMessage(R.string.fail);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
