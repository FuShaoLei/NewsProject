package com.fushaolei.project_android.module.common;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.data.bean.Pager;
import com.fushaolei.project_android.helper.RxJavaHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CommonPresenter extends BasePresenter<CommonContract.View> implements CommonContract.Presenter {

    public CommonPresenter(CommonContract.View baseView) {
        super(baseView);
    }


    @Override
    public void transfer(int page, int cid) {
        Observable<BaseResponse<Pager<News>>> observable = apiService.getNewsList(page, cid);
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Pager<News>>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(BaseResponse<Pager<News>> pagerBaseResponse) {
                if (pagerBaseResponse.getCode() == 200 && isExistView()) {
                    baseView.updateUI(pagerBaseResponse.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
