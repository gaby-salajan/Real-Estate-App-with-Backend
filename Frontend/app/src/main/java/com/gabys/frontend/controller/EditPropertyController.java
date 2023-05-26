package com.gabys.frontend.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.gabys.frontend.R;
import com.gabys.frontend.controller.commands.CommandUpdateProperty;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.Property;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.view.EditPropertyActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EditPropertyController {

    private Context context;

    private EditPropertyActivity activity;

    private Property property;

    private ICommand commandUpdateProperty;

    public EditPropertyController(EditPropertyActivity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();

        property = new Gson().fromJson(activity.getIntent().getStringExtra("property"), Property.class);

        ArrayList<String> types = Arrays.stream(context.getResources().getStringArray(R.array.property_types)).skip(1).collect(Collectors.toCollection(ArrayList::new));
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activity.getTypeSpinner().setAdapter(typeAdapter);

        setYesButtonListener();
        setNoButtonListener();
        setupView();

        commandUpdateProperty = new CommandUpdateProperty();
    }

    private void setupView(){
        activity.getIdTv().setText(String.valueOf(property.getId()));
        activity.getTitleEt().setText(property.getTitle());
        activity.getLocationEt().setText(property.getLocation());
        activity.getRoomsNoEt().setText(String.valueOf(property.getRoomsNo()));
        activity.getTypeSpinner().setSelection(property.getType());
        activity.getPriceEt().setText(String.valueOf(property.getPrice()));
        activity.getImageURLEt().setText(property.getImageURL());

        Glide.with(context)
                .load(property.getImageURL())
                .centerCrop()
                .into(activity.getImageView());
    }

    private void setYesButtonListener() {
        activity.getYesButton().setOnClickListener(v -> {

            ((CommandUpdateProperty)commandUpdateProperty).setProperty(new Property(
                    Integer.parseInt(activity.getIdTv().getText().toString()),
                    activity.getTitleEt().getText().toString(),
                    activity.getLocationEt().getText().toString(),
                    Integer.parseInt(activity.getRoomsNoEt().getText().toString()),
                    activity.getTypeSpinner().getSelectedItemPosition(),
                    Float.parseFloat(activity.getPriceEt().getText().toString()),
                    activity.getIsAvailableSwitch().isChecked() ? 1 : 0,
                    activity.getImageURLEt().getText().toString()));

            commandUpdateProperty.Execute(new ResponseCallback() {
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
