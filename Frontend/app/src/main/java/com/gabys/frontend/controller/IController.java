package com.gabys.frontend.controller;

import com.gabys.frontend.model.db.ResponseCallback;

public interface IController {
    void updateProperties();

    void setupLanguageSpinner();

    void deleteUser(String username);

    void getLoginUser(String username, ResponseCallback responseCallback);
}
