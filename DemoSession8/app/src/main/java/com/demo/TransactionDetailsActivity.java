package com.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adapters.TransDetailAdapter;
import com.apis.APIClient;
import com.apis.TransDetailAPI;
import com.entities.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionDetailsActivity extends AppCompatActivity {

    private TextView textViewResult, textViewStart, textViewEnd;
    private Spinner spinnerAcc, spinnerType, spinnerTotalAcc, spinnerTotalType;
    private Button btnDate,btnAcc,btnType, btnResult;
    private ListView listViewTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        initView();
        loadData();
    }
    private void initView(){
        textViewEnd = findViewById(R.id.textViewEnd);
        textViewEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textViewEnd_onClick(v);
            }
        });
        textViewStart = findViewById(R.id.textViewStart);
        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStart_onClick(v);
            }
        });
        textViewResult = findViewById(R.id.textViewResult);
        btnAcc = findViewById(R.id.buttonAcc);
        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAcc_onClick(v);
            }
        });
        btnDate = findViewById(R.id.buttonDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate_onClick(v);
            }
        });
        btnType = findViewById(R.id.buttonType);
        btnType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnType_onClick(v);
            }
        });
        btnResult = findViewById(R.id.buttonResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnResult_onClick(v);
            }
        });
        spinnerAcc = findViewById(R.id.spinnerAcc);
        spinnerTotalAcc = findViewById(R.id.spinnerTotalAcc);
        spinnerTotalType = findViewById(R.id.spinnerTotalType);
        spinnerType = findViewById(R.id.spinnerType);
        listViewTrans = findViewById(R.id.listViewTrans);
}
    public void btnAcc_onClick(View v) {
        TransDetailAPI transDetailAPI = APIClient.getClient().create(TransDetailAPI.class);
        transDetailAPI.listByAccId(Integer.parseInt(spinnerAcc.getSelectedItem().toString())).enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {
                if(response.isSuccessful()){
                    List<TransactionDetails> details = response.body();
                    listViewTrans.setAdapter(new TransDetailAdapter(getApplicationContext(),R.layout.transdetails_item_layout,details));}
                else {
                    listViewTrans.setAdapter(null);
                }
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void btnDate_onClick(View v) {
        TransDetailAPI transDetailAPI = APIClient.getClient().create(TransDetailAPI.class);
        transDetailAPI.listByDate(textViewStart.getText().toString(), textViewEnd.getText().toString()).enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {
                if(response.isSuccessful()){
                    List<TransactionDetails> details = response.body();
                    listViewTrans.setAdapter(new TransDetailAdapter(getApplicationContext(),R.layout.transdetails_item_layout,details));}
                else {
                    listViewTrans.setAdapter(null);
                }
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void btnType_onClick(View v) {
        TransDetailAPI transDetailAPI = APIClient.getClient().create(TransDetailAPI.class);
        transDetailAPI.listByType(Integer.parseInt(spinnerType.getSelectedItem().toString().equalsIgnoreCase("Rut tien")?"1":"2")).enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {
                if(response.isSuccessful()){
                    List<TransactionDetails> details = response.body();
                    listViewTrans.setAdapter(new TransDetailAdapter(getApplicationContext(),R.layout.transdetails_item_layout,details));
                }
                else {
                    listViewTrans.setAdapter(null);
                }
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void btnResult_onClick(View v) {
        TransDetailAPI transDetailAPI = APIClient.getClient().create(TransDetailAPI.class);
        transDetailAPI.total(Integer.parseInt(spinnerTotalAcc.getSelectedItem().toString()),Integer.parseInt(spinnerTotalType.getSelectedItem().toString().equalsIgnoreCase("Rut tien")?"1":"2")).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if(response.isSuccessful()){
                    textViewResult.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

            }
        });
    }
    public void textViewStart_onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                textViewStart.setText(simpleDateFormat.format(c.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }
    public void textViewEnd_onClick(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                textViewEnd.setText(simpleDateFormat.format(c.getTime()));
            }
        }, year, month, date);
        datePickerDialog.show();
    }
    private void loadData(){
        TransDetailAPI transDetailAPI = APIClient.getClient().create(TransDetailAPI.class);
        transDetailAPI.findall().enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {
                if(response.isSuccessful()){
                    List<TransactionDetails> details = response.body();
                    ArrayList<String> types = new ArrayList<String>();
                    types.add("Rut tien");
                    types.add("Gui tien");
                    ArrayList<String> listAcc = new ArrayList<String>();
                    listViewTrans.setAdapter(new TransDetailAdapter(getApplicationContext(),R.layout.transdetails_item_layout,details));
                    if(details!=null){
                        for (TransactionDetails item: details) {
                            int j = 0;
                            for (String i : listAcc) {
                                if(i.equalsIgnoreCase(String.valueOf(item.getAccid()))) j=1;
                            }
                            if(j==0) listAcc.add(String.valueOf(item.getAccid()));
                        }
                    }
                    spinnerAcc.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listAcc));
                    spinnerTotalAcc.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listAcc));
                    spinnerTotalType.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,types));
                    spinnerType.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,types));
                }else {
                    listViewTrans.setAdapter(null);
                }
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
