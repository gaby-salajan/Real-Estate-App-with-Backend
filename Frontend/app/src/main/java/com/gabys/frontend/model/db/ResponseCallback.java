package com.gabys.frontend.model.db;

public interface ResponseCallback {
    void onSuccessfulResponse(Object object);
    void onError();
}
