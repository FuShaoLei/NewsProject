package com.fushaolei.project_android.module.login;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.User;

public interface LoginContract {
    interface View extends BaseView {
        User getViewUser();
        void showToast(String text);
        void loginSuccess();
    }
    interface Presenter {
        void login();
    }
}
