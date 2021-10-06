package com.fushaolei.project_android.module.home;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.constant.HttpConstant;
import com.fushaolei.project_android.constant.TypeConstant;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.Tree;
import com.fushaolei.project_android.helper.RxJavaHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {


    public HomePresenter(HomeContract.View baseView) {
        super(baseView);
    }

    @Override
    public void transfer() {
        Observable<BaseResponse<List<Tree>>> observable = apiService.getTree(TypeConstant.TYPE_NEWS);
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<List<Tree>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<List<Tree>> listBaseResponse) {
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
