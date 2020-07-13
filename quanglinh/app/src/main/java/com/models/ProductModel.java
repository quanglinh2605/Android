package com.models;

import com.demo.R;
import com.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    public List<Product> findAll(){
        List<Product> products = new ArrayList<Product>();
        products.add(new Product(1,"product 1", 5, R.drawable.c1,"description 1"));
        products.add(new Product(2,"product 2", 4.5, R.drawable.caravelle,"description 2"));
        products.add(new Product(3,"product 3", 7.5, R.drawable.c3,"description 3"));
        return products;
    }
}
