package com.gabys.frontend.controller;


import com.gabys.frontend.controller.commands.CommandAddUser;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.AddClientActivity;


public class AddClientController {

    private final AddClientActivity activity;

    private final ICommand commandAddClient;

    public AddClientController(AddClientActivity activity) {
        this.activity = activity;

        setYesButtonListener();
        setNoButtonListener();

        commandAddClient = new CommandAddUser();
    }


    private void setYesButtonListener() {
        activity.getYesButton().setOnClickListener(v -> {

            ((CommandAddUser)commandAddClient).setUser(new User(
                    activity.getUserName(),
                    null,
                    activity.getEmail(),
                    activity.getPhone(),
                    0
            ));

            commandAddClient.Execute(new ResponseCallback() {
                @Override
                public void onSuccessfulResponse(Object object) {
                    activity.makeToast("User succesfully created");
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
