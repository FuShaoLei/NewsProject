package com.fushaolei.project_android.module.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.base.BaseActivity;
import com.fushaolei.project_android.data.bean.User;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.R;

import androidx.annotation.Nullable;

@Route(path = ARouterConstant.LOGIN_URL)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, android.view.View.OnClickListener {
    private ImageView mImageView;
    private EditText mUsername;
    private EditText mPassword;
    private TextView mLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mImageView = findViewById(R.id.widget_title_bar_back_img);
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        mLogin = findViewById(R.id.tv_login);

        mImageView.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected Context getBasisContext() {
        return LoginActivity.this;
    }

    @Override
    public void onClick(android.view.View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_login:
                if (BaseHelper.isLegal(mUsername, mPassword)) {
                    basePresenter.login();
                } else {
                    BaseHelper.Toast("请重新输入！");
                }
                break;
            case R.id.widget_title_bar_back_img:
                finish();
                break;
        }
    }


    @Override
    public User getViewUser() {
        Log.e("==", mUsername.getText().toString() + mPassword.getText().toString());
        return new User(mUsername.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void showToast(String text) {
        BaseHelper.Toast(text);
    }

    @Override
    public void loginSuccess() {
        finish();
    }


    @Override
    public void showLoading() {
        BaseHelper.showProgressDiolog(getBasisContext(), "登录中...");
    }

    @Override
    public void hideLoading() {
        BaseHelper.hideProgressDialog();
    }

    @Override
    public void showError() {

    }

    @Override
    public void reLoading() {

    }
}
