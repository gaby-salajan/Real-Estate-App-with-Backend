package com.gabys.frontend.controller;

import android.content.Context;
import android.view.View;

import com.gabys.frontend.controller.commands.CommandUpdateUser;
import com.gabys.frontend.controller.commands.ICommand;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.EditUserActivity;
import com.google.gson.Gson;

public class EditUserController {

    private Context context;

    private EditUserActivity activity;

    private UserPersistence userPersistence;

    private User loggedUser;

    private String username;

    private ICommand commandUpdateUser;

    public EditUserController(EditUserActivity activity) {
        this.activity = activity;
        this.context = activity.getBaseContext();
        this.userPersistence = new UserPersistence();

        Gson gson = new Gson();
        User user = gson.fromJson(activity.getIntent().getStringExtra("user"), User.class);
        this.loggedUser = gson.fromJson(activity.getIntent().getStringExtra("loggedUser"), User.class);

        username = user.getUsername();
        setUser(user);

        setOnYesButtonClick();
        setOnNoButtonClick();

        commandUpdateUser = new CommandUpdateUser();
    }

    private void setUser(User user) {
        activity.getUserNameEditText().setText(user.getUsername());
        activity.getPasswordEditText().setText(user.getPassword());
        activity.getEmailEditText().setText(user.getEmail());
        activity.getPhoneEditText().setText(user.getPhone());

        if(loggedUser.getRole() == 1){
            activity.getPasswordEditText().setVisibility(View.GONE);

            activity.getRoleSpinner().setSelection(0);
            activity.getRoleSpinner().setVisibility(View.GONE);
            activity.getRoleTextView().setVisibility(View.GONE);
        }
        else
            activity.getRoleSpinner().setSelection(user.getRole());
    }

    public void setOnYesButtonClick(){
        activity.getYesButton().setOnClickListener(v -> {

            ((CommandUpdateUser)commandUpdateUser).setUsername(username);
            ((CommandUpdateUser)commandUpdateUser).setUser(new User(
                    activity.getUserName(),
                    activity.getPassword(),
                    activity.getEmail(),
                    activity.getPhone(),
                    activity.getRole()));
            ((CommandUpdateUser)commandUpdateUser).setRole(loggedUser.getRole());

            commandUpdateUser.Execute(new ResponseCallback() {
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

    public void setOnNoButtonClick(){
        activity.getNoButton().setOnClickListener(v -> {
            activity.finish();
        });
    }
}
