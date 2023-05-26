package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.UserPersistence;

public class CommandAddUser implements ICommand {
    private User user;
    private final UserPersistence userPersistence;

    public CommandAddUser() {
        this.userPersistence = new UserPersistence();
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        userPersistence.addUser(user, new ResponseCallback() {
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

    public void setUser(User user) {
        this.user = user;
    }
}
