package com.fushaolei.project_android.module.my;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.User;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.helper.RxJavaHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyPresenter extends BasePresenter<MyContract.View> implements MyContract.Presenter {


    public MyPresenter(MyContract.View baseView) {
        super(baseView);
    }

    @Override
    public void tansfer() {
        if (MMKVHelper.isUser()) {
            Observable<BaseResponse<User>> observable = apiService.getUser(MMKVHelper.getUserId());
            observable = RxJavaHelper.toSubsribe(observable);
            observable.subscribe(new Observer<BaseResponse<User>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(BaseResponse<User> userBaseResponse) {
                    if (userBaseResponse.getCode() == 200 && isExistView()) {
                        baseView.updateUI(userBaseResponse.getData());
                    } else if (isExistView()) {
                        baseView.updateUI();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    baseView.updateUI();
                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            baseView.updateUI();
        }
    }
}
