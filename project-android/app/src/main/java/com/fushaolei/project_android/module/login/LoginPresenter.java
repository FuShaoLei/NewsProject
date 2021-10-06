package com.fushaolei.project_android.module.login;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.constant.HttpConstant;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.helper.RxJavaHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View baseView) {
        super(baseView);
    }

    @Override
    public void login() {
        baseView.showLoading();
        Observable<BaseResponse<Integer>> observable = apiService.loginUser(baseView.getViewUser());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(BaseResponse<Integer> baseResponse) {
                try {
                    Thread.sleep(HttpConstant.NETWORK_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                baseView.hideLoading();
                if (baseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    MMKVHelper.saveUserId(baseResponse.getData());
                    baseView.loginSuccess();
                } else {
                    MMKVHelper.deleteUser();
                }
                baseView.showToast(baseResponse.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                baseView.hideLoading();
            }

            @Override
            public void onComplete() {
                baseView.hideLoading();
            }
        });
    }
}
