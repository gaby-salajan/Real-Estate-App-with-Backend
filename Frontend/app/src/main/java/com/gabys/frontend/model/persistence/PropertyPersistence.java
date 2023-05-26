package com.gabys.frontend.model.persistence;

import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.db.PropertyMethods;
import com.gabys.frontend.model.db.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PropertyPersistence {

    private Retrofit retrofit;

    private PropertyMethods methods;

    public PropertyPersistence() {
        this.retrofit = RetrofitClient.getRetrofitInstance();
        this.methods = retrofit.create(PropertyMethods.class);
    }

    public void getAvailableProperties(ResponseCallback responseCallback) {
        Call<List<Property>> call = methods.getAvailableProperties();
        call.enqueue(new Callback<List<Property>>() {

            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                responseCallback.onError();
            }

        });

    }

    public void getProperties(ResponseCallback responseCallback) {
        Call<List<Property>> call = methods.getProperties();
        call.enqueue(new Callback<List<Property>>() {

            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void getProperty(int id, ResponseCallback responseCallback) {
        Call<Property> call = methods.getPropertyById(id);
        call.enqueue(new Callback<Property>() {

            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void addProperty(Property property, ResponseCallback responseCallback) {
        Call<Property> call = methods.addProperty(
                property.getTitle(),
                property.getLocation(),
                property.getRoomsNo(),
                property.getType(),
                property.getPrice(),
                property.isAvailable() ? 1 : 0,
                property.getImageURL()
                );

        call.enqueue(new Callback<Property>() {

            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void updateProperty(Property property, ResponseCallback responseCallback) {
        Call<Property> call = methods.updateProperty(property);

        call.enqueue(new Callback<Property>() {

            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
                responseCallback.onError();
            }

        });
    }

    public void deleteProperty(int id, ResponseCallback responseCallback) {
        Call<Property> call = methods.deleteProperty(id);

        call.enqueue(new Callback<Property>() {
            @Override
            public void onResponse(Call<Property> call, Response<Property> response) {
                if(response.isSuccessful()){
                    responseCallback.onSuccessfulResponse(response.body());
                }
                else
                    responseCallback.onError();
            }

            @Override
            public void onFailure(Call<Property> call, Throwable t) {
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

        this.addProperty(new Property("Apartament cu 2 camere", "Cluj",2,0,400f, 1,"https://empireimobiliare.com/oferte/170/big__spatiu-birou-18-camere-de-inchiriat-central-cluj-napoca_6408718b714cd6.jpg"), pp);
        this.addProperty(new Property("Casa cu etaj","Baciu",7,1,1200f,1, "https://img.staticmb.com/mbcontent//images/uploads/2022/12/Most-Beautiful-House-in-the-World.jpg"), pp);
        this.addProperty(new Property("Apartament cu 3 camere","Floresti",3,0,600f,1,"https://www.bhg.com/thmb/dgy0b4w_W0oUJUxc7M4w3H4AyDo=/1866x0/filters:no_upscale():strip_icc()/living-room-gallery-shelves-l-shaped-couch-ELeyNpyyqpZ8hosOG3EG1X-b5a39646574544e8a75f2961332cd89a.jpg"), pp);
    }
}
