package com.fushaolei.project_android.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected View rootView;
    protected P basePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        basePresenter = createPresenter();
        initView();
        initData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (basePresenter != null) {
            basePresenter.detachView();
        }
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected abstract Context getBasisContext();

    protected abstract P createPresenter();
}
