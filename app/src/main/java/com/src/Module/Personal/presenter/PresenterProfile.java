package com.src.Module.Personal.presenter;

import okhttp3.MultipartBody;
import com.src.Model.User;
import com.src.Module.Personal.model.ModelProfile;
import com.src.Module.Personal.view.IViewProfile;

public class PresenterProfile implements IPresenterProfile{
    private IViewProfile iViewProfile;
    private ModelProfile modelProfile;

    public PresenterProfile(IViewProfile iViewProfile) {
        this.iViewProfile = iViewProfile;
        modelProfile = new ModelProfile();
    }

    @Override
    public void getProfile() {
        modelProfile.profile(this);
    }

    @Override
    public void resultGetProfile(boolean success, User user, String msg) {
        if(success){
            iViewProfile.onSuccess(user);
        }else{
            iViewProfile.onFailed(msg);
        }
    }

    @Override
    public void uploadImage(MultipartBody.Part value) {
        modelProfile.uploadImageToServer(value);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        modelProfile.changePassword(oldPassword,newPassword,this);
    }

    @Override
    public void resultChangePassword(boolean success, String msg) {
        if(success){
            iViewProfile.onChangePasswordSuccess(msg);
        }else {
            iViewProfile.onChangePasswordFail(msg);
        }
    }


}
