package com.models;

import com.entities.Invoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceModel {
    public List<Invoice> findAll(){
        List<Invoice> invoices = new ArrayList<Invoice>();
        invoices.add(new Invoice("iv01","invoice 1", new Date(),"Cash",new ProductModel().findAll(),new CustomModel().findbyid("cs01")));
        invoices.add(new Invoice("iv02","invoice 2", new Date(),"Payment",new ProductModel().findAll(),new CustomModel().findbyid("cs02")));
        invoices.add(new Invoice("iv03","invoice 3", new Date(),"Card",new ProductModel().findAll(),new CustomModel().findbyid("cs03")));
        return invoices;
    }
}
