package com.src.Module.Register.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.R;
import com.google.android.material.textfield.TextInputEditText;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import es.dmoral.toasty.Toasty;

import com.src.Model.User;
import com.src.Module.Register.presenter.PresenterRegister;
import com.src.Utils.DialogLoading;

public class RegisterActivity extends AppCompatActivity implements IViewRegister {

    private TextInputEditText edtEmailRegister, edtUserNameRegister, edtPhoneRegister,
            edtPasswordRegister,edtAddress;
    private Button btnRegister;
    private PresenterRegister presenterRegister;
    private GoogleProgressBar progress_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        btnRegister.setOnClickListener(v -> {
            DialogLoading.LoadingGoogle(true, progress_register);
            registerUser();

        });
    }

    private void registerUser() {
        if (validation()) {
            User user = new User();
            user.setEmail(edtEmailRegister.getText().toString().trim());
            user.setUsername(edtUserNameRegister.getText().toString().trim());
            user.setPassword(edtPasswordRegister.getText().toString().trim());
            user.setPhone(edtPhoneRegister.getText().toString().trim());
            user.setAddress(edtAddress.getText().toString().trim());
            presenterRegister.handlerRegister(user);
        }

    }

    private void initView() {
        androidx.appcompat.widget.Toolbar toolbarRegister = findViewById(R.id.toolbarRegister);
        progress_register = findViewById(R.id.progress_register);
        toolbarRegister.setNavigationOnClickListener(v -> finish());
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtPhoneRegister = findViewById(R.id.edtPhoneRegister);
        edtUserNameRegister = findViewById(R.id.edtUserNameRegister);
        edtAddress = findViewById(R.id.edtAddress);
        btnRegister = findViewById(R.id.btnRegister);
        presenterRegister = new PresenterRegister(this);
    }
    @Override
    public void onSuccess() {
        DialogLoading.LoadingGoogle(false, progress_register);
        Toasty.success(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT, true).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toasty.warning(RegisterActivity.this, "Vui lòng đăng nhập lại!", Toast.LENGTH_SHORT, true).show();
                finish();
            }
        }, 3000);
    }
    @Override
    public void onFailed(String msg) {
        DialogLoading.LoadingGoogle(false, progress_register);
        Toasty.error(RegisterActivity.this, msg, Toast.LENGTH_SHORT, true).show();
    }
    private boolean validation() {
        String email = edtEmailRegister.getText().toString().trim();
        String password = edtPasswordRegister.getText().toString().trim();
        String phone = edtPhoneRegister.getText().toString().trim();
        String username = edtUserNameRegister.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        if (email.length() == 0) {
            edtEmailRegister.setError("Email Invalid");
            edtEmailRegister.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmailRegister.setError("Email Invalid");
            edtEmailRegister.requestFocus();
            return false;
        } else if (username.length() == 0) {
            edtUserNameRegister.setError("Username Invalid");
            edtUserNameRegister.requestFocus();
            return false;
        } else if (phone.length() == 0) {
            edtPhoneRegister.setError("Phone Invalid");
            edtPhoneRegister.requestFocus();
            return false;
        } else if (phone.length() != 10) {
            edtPhoneRegister.setError("Phone 10");
            edtPhoneRegister.requestFocus();
            return false;
        } else if (password.length() == 0) {
            edtPasswordRegister.setError("Password Invalid");
            edtPasswordRegister.requestFocus();
            return false;
        } else if (password.length() < 6) {
            edtPasswordRegister.setError("Password < 6");
            edtPasswordRegister.requestFocus();
            return false;
        }
        return true;
    }
}
