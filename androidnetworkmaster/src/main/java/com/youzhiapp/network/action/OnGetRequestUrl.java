package com.youzhiapp.network.action;

public abstract class OnGetRequestUrl {

    public abstract void onSuccess(String urlString);

    public void onFail(Throwable reason, String e) {
    }

    public void onFinish() {
    }

}