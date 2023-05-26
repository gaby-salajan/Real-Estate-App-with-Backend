package com.gabys.frontend.model.db;

import com.gabys.frontend.model.Property;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PropertyMethods {

    @GET("property/all")
    Call<List<Property>> getProperties();

    @GET("property/available")
    Call<List<Property>> getAvailableProperties();
    @GET("property/find")
    Call<Property> getPropertyById(@Query("id") int id);

    @POST("property/add")
    Call<Property> addProperty(@Query("title") String title,
                               @Query("location") String location,
                               @Query("roomsNo") int roomsNo,
                               @Query("type") int type,
                               @Query("price") Float price,
                               @Query("isAvailable") int isAvailable,
                               @Query("imageURL") String imageURL);

    @PUT("property/update")
    Call<Property> updateProperty(@Body Property property);

    @DELETE("property/delete")
    Call<Property> deleteProperty(@Query("id") int id);
}
