package com.fushaolei.project_android.module.vedio;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.Tree;

import java.util.List;

/**
 * @Auther: fushaolei
 * @datetime: 2021/2/18
 * @desc:
 */
public interface VedioContract {
    interface View extends BaseView {
        void updateUI(List<Tree> list);
    }

    interface Presenter {
        void transfer();
    }
}
