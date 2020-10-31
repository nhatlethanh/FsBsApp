package com.fsbs.src.module.login.presenter;


public interface IPresenterLogin {
    void handlerLogin(String email,String password);
    void resultLogin(boolean success, String msg);
}
