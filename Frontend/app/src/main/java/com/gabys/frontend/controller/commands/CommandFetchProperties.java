package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.persistence.PropertyPersistence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandFetchProperties implements ICommand{
    private PropertyPersistence propertyPersistence;

    public CommandFetchProperties() {
        this.propertyPersistence = new PropertyPersistence();
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {

         propertyPersistence.getProperties(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                ArrayList<Property> properties = ((ArrayList<Property>) object).stream()
                        .sorted(Comparator.comparing(Property::getLocation)
                                .thenComparing(Property::getPrice))
                        .collect(Collectors.toCollection(ArrayList::new));

                responseCallback.onSuccessfulResponse(properties);
            }

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });
    }
}
