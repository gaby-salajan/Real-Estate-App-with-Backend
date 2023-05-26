package com.gabys.frontend.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.gabys.frontend.controller.commands.CommandAddRent;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.PropertyPersistence;
import com.gabys.frontend.model.persistence.RentPersistence;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.AddRentActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AddRentController {

    private RentPersistence rentPersistence;
    private UserPersistence userPersistence;
    private PropertyPersistence propertyPersistence;
    private Context context;
    private AddRentActivity activity;

    private Property property;

    private ArrayList<User> clientsList;

    private final ICommand commandAddRent;

    public AddRentController(AddRentActivity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();

        this.rentPersistence = new RentPersistence();
        this.userPersistence = new UserPersistence();
        this.propertyPersistence = new PropertyPersistence();

        fetchClients();

        property = new Gson().fromJson(activity.getIntent().getStringExtra("property"), Property.class);
        activity.setPropertyImage(property.getImageURL());

        setYesButtonListener();
        setNoButtonListener();

        commandAddRent = new CommandAddRent(property);
    }

    private void fetchClients(){
        userPersistence.getClients(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                clientsList = (ArrayList<User>) object;
                setupSpinner();
            }

            @Override
            public void onError() {

            }
        });
    }

    private void setYesButtonListener() {
        activity.getYesButton().setOnClickListener(v -> {
            User client = activity.getSelectedClient();

            ((CommandAddRent)commandAddRent).setClient(client);

            commandAddRent.Execute(new ResponseCallback() {
                @Override
                public void onSuccessfulResponse(Object object) {
                    activity.finish();
                }

                @Override
                public void onError() {
                    activity.finish();
                }
            });
        });
    }

    private void setNoButtonListener() {
        activity.getNoButton().setOnClickListener(v -> {
            activity.finish();
        });
    }

    private void setupSpinner(){
        ArrayAdapter<User> clientsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, clientsList);
        clientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.getClientSpinner().setAdapter(clientsAdapter);
    }
}
