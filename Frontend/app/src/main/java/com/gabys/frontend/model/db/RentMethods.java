package com.gabys.frontend.model.db;

import com.gabys.frontend.model.Rent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RentMethods {

    @GET("rent/all")
    Call<List<Rent>> getRents();

    @GET("rent/find")
    Call<Rent> getRentById(@Query("id") int id);

    @POST("rent/add")
    Call<Rent> addRent(@Query("clientUsername") String clientUsername,
                       @Query("propertyId") int propertyId);

    @DELETE("rent/delete")
    Call<Rent> deleteRent(@Query("id") int id);
}
