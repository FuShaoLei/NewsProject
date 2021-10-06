package com.fushaolei.project_android.module.modify;

import com.fushaolei.project_android.constant.HttpConstant;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.helper.RxJavaHelper;
import com.fushaolei.project_android.data.bean.BaseResponse;
import com.fushaolei.project_android.data.bean.User;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ModifyPresenter extends BasePresenter<ModifyContract.View> implements ModifyContract.Presenter {

    public ModifyPresenter(ModifyContract.View baseView) {
        super(baseView);
    }

    @Override
    public void initPresenter() {
        Observable<BaseResponse<User>> observable = apiService.getUser(MMKVHelper.getUserId());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<User>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<User> userBaseResponse) {
                if (userBaseResponse.getCode() == 200) {
                    baseView.initUI(userBaseResponse.getData());
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
    public void submtChange() {
        BaseHelper.Log(baseView.getEditUser().toString());
        baseView.showLoading();
        Observable<BaseResponse<Integer>> observable = apiService.updateUser(baseView.getEditUser());
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<Integer> integerBaseResponse) {
                try {
                    Thread.sleep(HttpConstant.NETWORK_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (integerBaseResponse.getCode() == HttpConstant.SUCCESS_CODE) {
                    baseView.hideLoading();
                    baseView.editSuccess();
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
    public void editAvator(File file) {
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        Observable<BaseResponse<Integer>> observable = apiService.uploadAvator(MMKVHelper.getUserId(), body);
        observable = RxJavaHelper.toSubsribe(observable);
        observable.subscribe(new Observer<BaseResponse<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseResponse<Integer> integerBaseResponse) {
                if (integerBaseResponse.getCode() == 200) {
                    BaseHelper.Log("修改头像成功！");
                }
                baseView.shotTast(integerBaseResponse.getMsg());
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
