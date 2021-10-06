package com.fushaolei.project_android.base;

public interface BaseView {
    // 显示loading
    void showLoading();

    // 隐藏loading
    void hideLoading();

    // 当网络出现了问题时
    void showError();

    // 重新加载
    void reLoading();

}
