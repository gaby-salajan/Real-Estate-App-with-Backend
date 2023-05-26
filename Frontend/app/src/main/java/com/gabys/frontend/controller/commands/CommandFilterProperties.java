package com.gabys.frontend.controller.commands;

import android.content.Context;
import android.view.LayoutInflater;

import com.gabys.frontend.controller.IController;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.view.FilterPropertiesDialog;

import java.util.ArrayList;

public class CommandFilterProperties implements ICommand {
    private LayoutInflater layoutInflater;
    private Context context;
    private IController controller;
    private ArrayList<Property> propertiesList;

    public CommandFilterProperties(IController controller, ArrayList<Property> propertiesList, LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.controller = controller;
        this.propertiesList = propertiesList;
    }

    public void setPropertiesList(ArrayList<Property> propertiesList){
        this.propertiesList = propertiesList;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        new FilterPropertiesDialog(propertiesList, layoutInflater, context, controller);
    }
}
