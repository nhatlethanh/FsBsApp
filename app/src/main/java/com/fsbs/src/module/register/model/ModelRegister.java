package com.fsbs.src.module.register.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.fsbs.src.model.BaseResponse;
import com.fsbs.src.model.ErrorResponse;
import com.fsbs.src.model.User;
import com.fsbs.src.module.register.presenter.PresenterRegister;
import com.fsbs.src.network.APIFsBs;
import com.fsbs.src.network.IApiFsBs;
import com.fsbs.src.utils.ErrorUtils;

public class ModelRegister {
    public void register(User user, final PresenterRegister presenterRegister) {
        IApiFsBs apiService = APIFsBs.getAPIVnProduct().create(IApiFsBs.class);
        Call<BaseResponse<User>> call = apiService.handlerRegister(user.getEmail(), user.getPassword(), user.getPhone(), "", user.getUsername());
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if (response.isSuccessful()) {
                    presenterRegister.resultRegister(true, "Đăng ký thành công");
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterRegister.resultRegister(false, err.getStatusCode() + " - " + err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                presenterRegister.resultRegister(false, t.getMessage());

            }
        });
    }
}

