package com.apis;

import com.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductAPI {
    @GET("product/findall")
    Call<List<Product>> findAll();

    @GET("product/search/{search}")
    Call<List<Product>> search(@Path("search") String keyword);

    @GET("product/search/{min}/{max}")
    Call<List<Product>> find(@Path("max") double max,@Path("min") double min);

    @POST("product/save")
    Call<Product> save(@Body Product product);

    @PUT("product/update")
    Call<Product> update(@Body Product product);

    @DELETE("product/delete/{id}")
    Call<Void> delete(@Path("id") int id);
}
