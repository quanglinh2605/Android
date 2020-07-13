package com.models;

import com.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomModel {
    public List<Customer> findall() {
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("cs01","nguyen van a","nguyen trai"));
        customers.add(new Customer("cs02","nguyen van b","nguyen du"));
        customers.add(new Customer("cs03","nguyen van c","nguyen dinh chieu"));
    return customers;
    }
    public Customer findbyid(String id){
        for (Customer customer:findall()) {
            if(customer.getId().equalsIgnoreCase(id)){
                return customer;
            }
        };
        return null;
    }
}
