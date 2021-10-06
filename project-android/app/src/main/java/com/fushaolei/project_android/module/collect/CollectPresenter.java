package com.fushaolei.project_android.module.collect;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.data.bean.Pager;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.helper.RxJavaHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Auther: fushaolei
 * @datetime: 2021/4/14
 * @desc:
 */
public class CollectPresenter extends BasePresenter<CollectContract.View> implements CollectContract.Presenter {
    public CollectPresenter(CollectContract.View baseView) {
        super(baseView);
    }

    @Override
    public void transfer() {
        if (!MMKVHelper.isUser()) return;
        Observable<BaseResponse<List<News>>> observable = apiService.getUserCollect(MMKVHelper.getUserId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<List<News>>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(BaseResponse<List<News>> listBaseResponse) {
                if (listBaseResponse.getCode() == 200 && isExistView()) {
                    baseView.updateUI(listBaseResponse.getData());
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
