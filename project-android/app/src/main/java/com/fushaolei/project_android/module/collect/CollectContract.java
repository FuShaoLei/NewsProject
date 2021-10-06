package com.fushaolei.project_android.module.collect;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.News;

import java.util.List;

/**
 * @Auther: fushaolei
 * @datetime: 2021/4/14
 * @desc:
 */
public interface CollectContract {
    interface View extends BaseView {
        void updateUI(List<News> newsList);
    }

    interface Presenter {
        void transfer();
    }
}
