package com.fsbs.src.module.login.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.fsbs.R;
import com.google.android.material.textfield.TextInputEditText;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import es.dmoral.toasty.Toasty;
import com.fsbs.NavigationActivity;
import com.fsbs.src.model.StoreKey;
import com.fsbs.src.module.login.presenter.PresenterLogin;
import com.fsbs.src.module.register.view.RegisterActivity;
import com.fsbs.src.utils.DialogLoading;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IViewLogin {
    private SlidingSplashView splashLogin;
    private LinearLayout bodyLogin,signUpNew;
    private Button btnLogin;
    private PresenterLogin presenterLogin;
    private TextInputEditText edtEmailLogin,edtPasswordLogin;
    private GoogleProgressBar googleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        init();
        getSizeDevice();
    }

    public void getSizeDevice() {
        ViewGroup.LayoutParams paramsBody = bodyLogin.getLayoutParams();
        ViewGroup.LayoutParams paramsSplash = splashLogin.getLayoutParams();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        paramsBody.height = (int) (size.y * 0.7);
        paramsSplash.height = (int) (size.y * 0.35);
        splashLogin.setLayoutParams(paramsSplash);
        bodyLogin.setLayoutParams(paramsBody);
        StoreKey.setSize(size);
        splashLogin.setImageResources(new String[]{"http://osales.vn/wp-content/uploads/2017/04/chup-anh-quan-ao-online-768x768.jpg",
                "http://anhvienpiano.com/wp-content/uploads/2018/08/dich-vu-chup-anh-thoi-trang-cho-shop-quan-ao-dep-gia-re-768x615.jpg",
                "http://anhvienpiano.com/wp-content/uploads/2018/08/ch%E1%BB%A5p-%E1%BA%A3nh-s%E1%BA%A3n-ph%E1%BA%A9m-768x768.jpg"});
        splashLogin.setPaddingBottom(0, 50, 0, 0);
        splashLogin.setAnimPage();
    }

    @SuppressLint("CutPasteId")
    private void init() {
        splashLogin = findViewById(R.id.splashLogin);
        bodyLogin = findViewById(R.id.bodyLogin);
        signUpNew = findViewById(R.id.signUpNew);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        googleProgressBar = findViewById(R.id.google_progress);
        presenterLogin = new PresenterLogin(this,getApplicationContext());

        edtEmailLogin.setText("abc@gmail.com");
        edtPasswordLogin.setText("1234567");
        btnLogin.setOnClickListener(this);
        signUpNew.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpNew:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btnLogin:
                if(checkValid()){
                    DialogLoading.LoadingGoogle(true,googleProgressBar);
                    presenterLogin.handlerLogin(edtEmailLogin.getText().toString().trim(),edtPasswordLogin.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void onSuccess() {
        DialogLoading.LoadingGoogle(false,googleProgressBar);
        Toasty.success(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT, true).show();
        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
        finish();
    }

    @Override
    public void onFailed(String msg) {
        DialogLoading.LoadingGoogle(false,googleProgressBar);
        Toasty.error(LoginActivity.this, msg, Toast.LENGTH_SHORT, true).show();

    }

    private boolean checkValid(){
        String email = edtEmailLogin.getText().toString().trim();
        String password = edtPasswordLogin.getText().toString().trim();
        if(email.length() == 0){
            edtEmailLogin.setError("Email Invalid");
            edtEmailLogin.requestFocus();
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmailLogin.setError("Email Invalid");
            edtEmailLogin.requestFocus();
            return false;

        }else if (password.length() == 0) {
            edtPasswordLogin.setError("Password Invalid");
            edtPasswordLogin.requestFocus();
            return false;


        }
        return  true;
    }

}
