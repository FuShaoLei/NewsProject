package com.fushaolei.project_android.module.home;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.Tree;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void updateUI(List<Tree> list);
    }

    interface Presenter {
        void transfer();
    }

}
