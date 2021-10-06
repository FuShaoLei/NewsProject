package com.fushaolei.project_android.helper;

import com.alibaba.android.arouter.facade.Postcard;
import com.fushaolei.project_android.constant.ARouterConstant;

public class ARouterHelper {
    public static Postcard gotoLogin() {
        return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.LOGIN_URL);
    }

    public static Postcard gotoAricle(int id) {
        return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.ARTICLE_URL).withInt(ARouterConstant.ID, id);
    }

    /**
     * 跳转前判断是否有登录，有的话直接跳转到用户修改页面
     * @return
     */
    public static Postcard gotoModify() {
        if (!MMKVHelper.isUser()) {
            return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.LOGIN_URL);
        }
        return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.MODIFY_URL);
    }

    public static Postcard gotoMain() {
        return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.MAIN_URL);
    }

    /**
     * 跳转前判断是否有登录，有的话直接跳转到用户收藏页面
     * @return
     */
    public static Postcard gotoCollect() {
        if (!MMKVHelper.isUser()) {
            return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.LOGIN_URL);
        }
        return com.alibaba.android.arouter.launcher.ARouter.getInstance().build(ARouterConstant.COLLECT_URL);
    }

}
