package com.fushaolei.project_android.base;

import com.fushaolei.project_android.data.ApiService;
import com.fushaolei.project_android.data.DataManager;

public abstract class BasePresenter<V extends BaseView> {
    protected V baseView;
    protected ApiService apiService = DataManager.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    protected V getBaseView() {
        return baseView;
    }

    protected boolean isExistView() {
        return baseView != null;
    }

    public void detachView() {
        if (baseView != null) {
            baseView = null;
        }
    }
}
