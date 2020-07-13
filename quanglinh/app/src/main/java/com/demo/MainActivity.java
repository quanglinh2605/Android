package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapters.InvoiceAdapter;
import com.entities.Invoice;
import com.models.InvoiceModel;

public class MainActivity extends AppCompatActivity {
    private ListView listViewInvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }
    private void initView(){
        listViewInvoice = findViewById(R.id.listViewInvoice);
        listViewInvoice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewInvoice_onItemClick(parent,view,position,id);
            }
        });
    }
    public void listViewInvoice_onItemClick(AdapterView<?> parent, View view, int position, long id){
        Invoice invoice = (Invoice) parent.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("invoice", invoice);
        startActivity(intent);
    }
    private  void loadData(){
        InvoiceAdapter invoiceAdapter = new InvoiceAdapter(getApplicationContext(),R.layout.invoice_item_layout,new InvoiceModel().findAll());
        listViewInvoice.setAdapter(invoiceAdapter);
    }
}
