package com.gabys.frontend.controller.commands;

import android.content.Context;
import android.view.LayoutInflater;

import com.gabys.frontend.controller.AdminController;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.view.FilterUsersDialog;

import java.util.ArrayList;

public class CommandFilterUsers implements ICommand {
    private final LayoutInflater layoutInflater;
    private final Context context;
    private final AdminController controller;
    private ArrayList<User> usersList;

    public CommandFilterUsers(AdminController controller, ArrayList<User> usersList, LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.controller = controller;
        this.usersList = usersList;
    }

    public void setUsersList(ArrayList<User> usersList){
        this.usersList = usersList;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        new FilterUsersDialog(usersList, layoutInflater, context, controller);
    }
}
