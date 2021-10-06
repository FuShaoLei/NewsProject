package com.fushaolei.project_android.base;

import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.constant.HttpConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T extends BaseResponse> implements Observer<T> {
    protected BaseView view;

    protected BaseObserver(BaseView view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (view != null) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
//        try {
//            Thread.sleep(GlobalConstant.NETWORK_DELAY);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (view != null) {
            view.hideLoading();
        }
        if (view != null && t.getCode() == HttpConstant.SUCCESS_CODE) {
            onSuccess(t);
        } else {
            onFail();
        }
    }


    @Override
    public void onError(Throwable e) {
        onFail();
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);

    protected abstract void onFail();
}
