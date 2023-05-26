package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CommandFetchClients implements ICommand {
    private UserPersistence userPersistence;

    public CommandFetchClients() {
        this.userPersistence = new UserPersistence();
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        userPersistence.getClients(new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                ArrayList<User> users = ((ArrayList<User>) object).stream()
                        .sorted(Comparator.comparing(User::getUsername))
                        .collect(Collectors.toCollection(ArrayList::new));
                responseCallback.onSuccessfulResponse(users);
            }

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });
    }
}
