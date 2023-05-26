package com.gabys.frontend.controller;

import android.content.Context;

import com.gabys.frontend.controller.commands.CommandAddProperty;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.view.AddPropertyActivity;

public class AddPropertyController {
    private final Context context;

    private final AddPropertyActivity activity;

    private final ICommand commandAddProperty;

    public AddPropertyController(AddPropertyActivity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();

        setYesButtonListener();
        setNoButtonListener();

        commandAddProperty = new CommandAddProperty();
    }


    private void setYesButtonListener() {
        activity.getYesButton().setOnClickListener(v -> {

            ((CommandAddProperty)commandAddProperty).setProperty(new Property(
                    activity.getTitleEt().getText().toString(),
                    activity.getLocationEt().getText().toString(),
                    Integer.parseInt(activity.getRoomsNoEt().getText().toString()),
                    activity.getTypeSpinner().getSelectedItemPosition(),
                    Float.parseFloat(activity.getPriceEt().getText().toString()),
                    activity.getIsAvailableSwitch().isChecked() ? 1 : 0,
                    activity.getImageURLEt().getText().toString()));

            commandAddProperty.Execute(new ResponseCallback() {
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
}
