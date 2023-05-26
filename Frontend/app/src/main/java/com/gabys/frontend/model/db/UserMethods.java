package com.gabys.frontend.model.db;

import com.gabys.frontend.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserMethods {

    @GET("user/find")
    Call<User> getUser(@Query("username") String username);

    @GET("user/clients")
    Call<List<User>> getAllClients();

    @GET("user/auth_users")
    Call<List<User>> getAuthUsers();

    @POST("user/add")
    Call<User> addUser(@Body User user);

    @PUT("user/update")
    Call<User> updateUser(@Query("username") String username, @Body User user);

    @DELETE("user/delete")
    Call<User> deleteUser(@Query("username") String username);

    @PUT("user/send_email")
    Call<User> sendEmail(@Body User user);

}
