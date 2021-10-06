package com.fushaolei.project_android.module.my;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.User;

import io.reactivex.Observable;

public interface MyContract {
    interface View extends BaseView {
        // 有缓存时更新UI
        void updateUI(User user);
        // 无缓存时也更新UI
        void updateUI();
    }
    interface Presenter {
        void tansfer();
    }
    interface Model {
        Observable<BaseResponse<User>> getUserById(int id);
    }
}
