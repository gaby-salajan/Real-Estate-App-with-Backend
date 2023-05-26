package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.Rent;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.PropertyPersistence;
import com.gabys.frontend.model.persistence.RentPersistence;

public class CommandAddRent implements ICommand {

    private RentPersistence rentPersistence;

    private PropertyPersistence propertyPersistence;

    private Property property;
    private User client;

    public CommandAddRent(Property property) {
        rentPersistence = new RentPersistence();
        propertyPersistence = new PropertyPersistence();

        this.property = property;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        property.setAvailable(false);
        System.out.println(client + " " + property);

        Rent rent = new Rent(client, property);

        rentPersistence.addRent(rent, new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                propertyPersistence.updateProperty(property, new ResponseCallback() {
                    @Override
                    public void onSuccessfulResponse(Object object) {
                        responseCallback.onSuccessfulResponse(object);
                    }

                    @Override
                    public void onError() {
                        responseCallback.onError();
                    }
                });
            }

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });


    }
}
