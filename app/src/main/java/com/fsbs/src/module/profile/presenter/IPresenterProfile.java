package com.fsbs.src.module.profile.presenter;

import okhttp3.MultipartBody;
import com.fsbs.src.model.User;

public interface IPresenterProfile {
    void getProfile();
    void resultGetProfile(boolean success, User user,String msg);

    void uploadImage(MultipartBody.Part value);

    void changePassword(String oldPassword, String newPassword);
    void resultChangePassword(boolean success,String msg);
}
