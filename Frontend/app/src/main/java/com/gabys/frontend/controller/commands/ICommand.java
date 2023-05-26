package com.gabys.frontend.controller.commands;

import com.gabys.frontend.model.db.ResponseCallback;

public interface ICommand {
    void Execute(ResponseCallback responseCallback);
}

