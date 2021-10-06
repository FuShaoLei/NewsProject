package com.fushaolei.project_android;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.base.BaseActivity;
import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.helper.ARouterHelper;

/**
 * @Auther: fushaolei
 * @datetime: 2021/4/2
 * @desc:
 */
@Route(path = ARouterConstant.SPLASH_URL)
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        // 延迟两秒后跳入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouterHelper.gotoMain().navigation();
            }
        }, 2000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected Context getBasisContext() {
        return null;
    }
}
