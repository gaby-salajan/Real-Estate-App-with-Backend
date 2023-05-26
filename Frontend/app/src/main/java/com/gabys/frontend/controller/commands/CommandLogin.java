package com.gabys.frontend.controller.commands;

import android.content.Context;
import android.content.Intent;

import com.gabys.frontend.model.AppLanguage;
import com.gabys.frontend.model.db.ResponseCallback;
import com.gabys.frontend.model.User;
import com.gabys.frontend.model.persistence.UserPersistence;
import com.gabys.frontend.view.AdminActivity;
import com.gabys.frontend.view.EmployeeActivity;
import com.google.gson.Gson;

public class CommandLogin implements ICommand{

    private Context context;
    private String username;
    private String password;

    private AppLanguage language;

    private UserPersistence userPersistence;

    public CommandLogin(Context context) {
        this.context = context;
        this.userPersistence = new UserPersistence();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLanguage(AppLanguage language) {
        this.language = language;
    }

    @Override
    public void Execute(ResponseCallback responseCallback) {
        userPersistence.getUser(username, new ResponseCallback() {
            @Override
            public void onSuccessfulResponse(Object object) {
                User res = (User) object;
                if (password.equals(res.getPassword())) {
                    Intent intent = null;

                    if (res.getRole() == 1)
                        intent = new Intent(context, EmployeeActivity.class);

                    if (res.getRole() == 2)
                        intent = new Intent(context, AdminActivity.class);

                    if (intent != null) {
                        intent.putExtra("user", new Gson().toJson(res));
                        intent.putExtra("lang", language.getLang());
                        context.startActivity(intent);
                    }
                    responseCallback.onSuccessfulResponse(intent);
                }
                responseCallback.onError();
            }

            @Override
            public void onError() {
                responseCallback.onError();
            }
        });
    }
}
