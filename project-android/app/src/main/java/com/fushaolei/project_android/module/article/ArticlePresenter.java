package com.fushaolei.project_android.module.article;

import android.util.Log;

import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.constant.HttpConstant;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.Comment;
import com.fushaolei.project_android.data.bean.InsertComment;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.helper.RxJavaHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ArticlePresenter extends BasePresenter<ArticleContract.View> implements ArticleContract.Presenter {
    public ArticlePresenter(ArticleContract.View baseView) {
        super(baseView);
    }

    @Override
    public void transfer() {
        Observable<BaseResponse<News>> observable = apiService.getNewsById(baseView.getNewsId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<News>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<News> newsBaseResponse) {
                if (newsBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    baseView.updateUI(newsBaseResponse.getData());
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

    @Override
    public void updateComments() {
        Observable<BaseResponse<List<Comment>>> observable = apiService.getCommentList(baseView.getNewsId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<List<Comment>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<List<Comment>> listBaseResponse) {
                if (listBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    baseView.updateComment(listBaseResponse.getData());
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

    @Override
    public void insertComment(String text) {
        Observable<BaseResponse<Integer>> observable = apiService.insertComment(
                new InsertComment(baseView.getNewsId(), MMKVHelper.getUserId(), BaseHelper.getDateTime(), text));
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<Integer> integerBaseResponse) {
                if (integerBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
//                    BaseHelper.Toast(integerBaseResponse.getData() + "");
                    baseView.insertCommentSuccess();
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

    @Override
    public void updateCollect() {
        if (!MMKVHelper.isUser()) return;
        Observable<BaseResponse<Boolean>> observable = apiService.getCollect(baseView.getNewsId(), MMKVHelper.getUserId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Boolean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<Boolean> booleanBaseResponse) {
                if (booleanBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    baseView.updateCollect(booleanBaseResponse.getData());
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

    @Override
    public void clickOrCancelCollect() {
        if (!MMKVHelper.isUser()) return;
        Observable<BaseResponse<Boolean>> observable = apiService.clickOrCancelCollect(baseView.getNewsId(), MMKVHelper.getUserId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Boolean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<Boolean> booleanBaseResponse) {
                if (booleanBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    baseView.updateCollect(booleanBaseResponse.getData());
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
