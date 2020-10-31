package com.fsbs.src.module.profile.view;

import com.fsbs.src.model.User;

public interface IViewProfile {
    void onSuccess(User user);
    void onFailed(String msg);

    void onChangePasswordSuccess(String msg);
    void onChangePasswordFail(String msg);
}
