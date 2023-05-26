package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.persistence.PropertyPersistence;

public class CommandDeleteProperty implements ICommand {

    private PropertyPersistence propertyPersistence;
    private int propertyId;

    public CommandDeleteProperty() {
        this.propertyPersistence = new PropertyPersistence();
    }

    public void setPropertyId(int id) {
        this.propertyId = id;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        propertyPersistence.deleteProperty(propertyId, new ResponseCallback() {
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
