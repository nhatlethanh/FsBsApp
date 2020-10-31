package com.fsbs.src.module.register.presenter;


import com.fsbs.src.model.User;

public interface IPresenterRegister {
    void handlerRegister(User user);
    void resultRegister(boolean success, String msg);
}
