package com.fushaolei.project_android.module.common;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.data.bean.Pager;

public interface CommonContract {
    interface View extends BaseView {
        void updateUI(Pager<News> newsPager);
    }
    interface Presenter{
        void transfer(int page,int cid);
    }
}
