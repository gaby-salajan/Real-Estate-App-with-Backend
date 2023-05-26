package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.UserPersistence;

public class CommandUpdateUser implements ICommand {
    private User user;

    private String username;
    private UserPersistence userPersistence;

    private int role;

    public CommandUpdateUser() {
        this.userPersistence = new UserPersistence();
        this.role = 0;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        userPersistence.updateUser(username, user, new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                if(role == 2){
                    userPersistence.sendEmail(user, new ResponseCallback() {
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

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });
    }
}
