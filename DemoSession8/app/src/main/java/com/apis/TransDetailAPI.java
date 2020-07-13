package com.apis;

import com.entities.TransactionDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TransDetailAPI {
    @GET("transdetails/findall")
    Call<List<TransactionDetails>> findall();
    @GET("transdetails/listbytype/{type}")
    Call<List<TransactionDetails>> listByType(@Path("type") int type);
    @GET("transdetails/listbydate/{start}/{end}")
    Call<List<TransactionDetails>> listByDate(@Path("start") String start, @Path("end") String end);
    @GET("transdetails/listbyaccid/{accid}")
    Call<List<TransactionDetails>> listByAccId(@Path("accid") int accid);
    @GET("transdetails/total/{accid}/{type}")
    Call<Double> total(@Path("accid") int accid,@Path("type") int type);
}
