package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.persistence.PropertyPersistence;

public class CommandAddProperty implements ICommand{
    private Property property;
    private PropertyPersistence propertyPersistence;

    public CommandAddProperty() {
        this.propertyPersistence = new PropertyPersistence();
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        propertyPersistence.addProperty(property, new ResponseCallback() {
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
}
