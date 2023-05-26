package com.gabys.frontend.model.persistence;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.db.UserMethods;
import com.gabys.frontend.model.db.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserPersistence {

    private Retrofit retrofit;

    private UserMethods methods;

    public UserPersistence() {
        this.retrofit = RetrofitClient.getRetrofitInstance();
        this.methods = retrofit.create(UserMethods.class);
    }

    public void getAuthUsers(ResponseCallback responseCallback) {
        Call<List<User>> call = methods.getAuthUsers();

        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful())
                    responseCallback.onSuccessfulResponse(response.body());
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }

    public void getClients(ResponseCallback responseCallback) {
        Call<List<User>> call = methods.getAllClients();

        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }

    public void getUser(String username, ResponseCallback responseCallback){
        Call<User> call = methods.getUser(username);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void addUser(User user, ResponseCallback responseCallback) {
        Call<User> call = methods.addUser(user);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void updateUser(String username, User user, ResponseCallback responseCallback) {
        Call<User> call = methods.updateUser(username, user);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }
    
    public void deleteUser(String username, ResponseCallback responseCallback) {
        Call<User> call = methods.deleteUser(username);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void sendEmail(User user, ResponseCallback responseCallback){
        Call<User> call = methods.sendEmail(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                } else {
                    responseCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }

    public void insertDummyValues(){
        ResponseCallback pp = new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {}

            @Override
            public void onError() {}
        };

        this.addUser(new User("gabriel", "", 0), pp);
        this.addUser(new User("john", "1234", 1), pp);
        this.addUser(new User("alex", "1234", 2), pp);

    }
}
