package com.gabys.frontend.model.persistence;

import com.gabys.frontend.model.Rent;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.db.RentMethods;
import com.gabys.frontend.model.db.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RentPersistence {

    private Retrofit retrofit;
    private RentMethods methods;

    public RentPersistence() {
        this.retrofit = RetrofitClient.getRetrofitInstance();
        this.methods = retrofit.create(RentMethods.class);
    }

    public void getRents(ResponseCallback responseCallback) {
        Call<List<Rent>> call = methods.getRents();

        call.enqueue(new Callback<List<Rent>>() {

            @Override
            public void onResponse(Call<List<Rent>> call, Response<List<Rent>> response) {
                if (response.isSuccessful())
                    responseCallback.onSuccessfulResponse(response.body());
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<List<Rent>> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }

    public void getRent(int id, ResponseCallback responseCallback) {
        Call<Rent> call = methods.getRentById(id);

        call.enqueue(new Callback<Rent>() {

            @Override
            public void onResponse(Call<Rent> call, Response<Rent> response) {
                if (response.isSuccessful())
                    responseCallback.onSuccessfulResponse(response.body());
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Rent> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }

    public void addRent(Rent rent, ResponseCallback responseCallback) {;
        Call<Rent> call = methods.addRent(rent.getClientUsername(), rent.getPropertyId());

        call.enqueue(new Callback<Rent>() {

            @Override
            public void onResponse(Call<Rent> call, Response<Rent> response) {
                if (response.isSuccessful())
                    responseCallback.onSuccessfulResponse(response.body());
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Rent> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }


    public void deleteRent(int id, ResponseCallback responseCallback) {
        Call<Rent> call = methods.deleteRent(id);

        call.enqueue(new Callback<Rent>() {

            @Override
            public void onResponse(Call<Rent> call, Response<Rent> response) {
                if (response.isSuccessful())
                    responseCallback.onSuccessfulResponse(response.body());
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Rent> call, Throwable t) {
                responseCallback.onError();
            }
        });
    }
}
