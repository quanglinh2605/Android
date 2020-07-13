package com.models;

import com.demo.R;
import com.entities.Logo;

import java.util.ArrayList;
import java.util.List;

public class LogoModel {
    public List<Logo> findAll(){
        List<Logo> logos = new ArrayList<Logo>();
        logos.add( new Logo("Apple", R.drawable.c1,"1000k$"));
        logos.add( new Logo("Samsung", R.drawable.c2,"1000k$"));
        logos.add( new Logo("Sony", R.drawable.c3,"1000k$"));
        logos.add( new Logo("Redmi", R.drawable.c4,"1000k$"));
        logos.add( new Logo("Dell", R.drawable.c5,"1000k$"));
        logos.add( new Logo("Nikon", R.drawable.c6,"1000k$"));
        return logos;
    }
}
