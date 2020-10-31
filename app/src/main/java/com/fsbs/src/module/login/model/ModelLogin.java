package com.fsbs.src.module.login.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.fsbs.src.model.BaseResponse;
import com.fsbs.src.model.ErrorResponse;
import com.fsbs.src.model.User;
import com.fsbs.src.module.login.presenter.PresenterLogin;
import com.fsbs.src.network.APIFsBs;
import com.fsbs.src.network.IApiFsBs;
import com.fsbs.src.utils.ErrorUtils;

public class ModelLogin {
    public void loginUser(String email, String password, final PresenterLogin presenterLogin) {
        Log.d("FsBs", "loginUser: " + email);
        IApiFsBs apiService = APIFsBs.getAPIVnProduct().create(IApiFsBs.class);
        Call<BaseResponse<User>> call = apiService.handlerLogin(email, password);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<User>> call, @NotNull Response<BaseResponse<User>> response) {
                //success code >= 200 <= 300
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    presenterLogin.resultLogin(true, response.body().getData().getToken());
                    Log.d("FsBs", "onResponse: " + response.body().getData().getToken());
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterLogin.resultLogin(false, err.getStatusCode() + " - " + err.getErr());
                }
            }
            @Override
            public void onFailure(@NotNull Call<BaseResponse<User>> call, @NotNull Throwable t) {
                presenterLogin.resultLogin(false,t.getMessage());
            }
        });
    }
}
