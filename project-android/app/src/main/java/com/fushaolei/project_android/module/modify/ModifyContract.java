package com.fushaolei.project_android.module.modify;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.User;

import java.io.File;

public interface ModifyContract {
    interface View extends BaseView {
        void initUI(User user);

        User getEditUser();

        void editSuccess();

        void editFail();

        void shotTast(String text);
    }

    interface Presenter {
        void initPresenter();
        void submtChange();
        void editAvator(File file);
    }
}
