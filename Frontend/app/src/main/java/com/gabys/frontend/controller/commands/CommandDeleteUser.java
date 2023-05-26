package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.persistence.UserPersistence;


public class CommandDeleteUser implements ICommand  {

    private UserPersistence userPersistence;
    private String username;

    public CommandDeleteUser() {
        this.userPersistence = new UserPersistence();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        userPersistence.deleteUser(username, new ResponseCallback() {
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
}
