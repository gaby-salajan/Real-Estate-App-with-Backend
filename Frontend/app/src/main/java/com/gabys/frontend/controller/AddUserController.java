package com.gabys.frontend.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.gabys.frontend.R;
import com.gabys.frontend.controller.commands.CommandAddUser;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.AddUserActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddUserController {
    private final Context context;

    private final AddUserActivity activity;

    private ICommand commandAddUser;

    public AddUserController(AddUserActivity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();

        ArrayList<String> roles = Arrays.stream(context.getResources().getStringArray(R.array.user_roles)).collect(Collectors.toCollection(ArrayList::new));
        roles.remove("Client");
        ArrayAdapter<String> rolesAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, roles);
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.getRoleSpinner().setAdapter(rolesAdapter);

        setYesButtonListener();
        setNoButtonListener();

        commandAddUser = new CommandAddUser();
    }


    private void setYesButtonListener() {
        activity.getYesButton().setOnClickListener(v -> {

            ((CommandAddUser)commandAddUser).setUser(new User(
                    activity.getUsername(),
                    activity.getPassword(),
                    activity.getRoleSpinner().getSelectedItemPosition()));

            commandAddUser.Execute(new ResponseCallback() {
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
