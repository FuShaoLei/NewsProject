package com.fushaolei.project_android.helper;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.fushaolei.project_android.app.App;
import com.fushaolei.project_android.constant.HttpConstant;

public class GlideHelper {
    /**
     * 加载圆形图片
     *
     * @param path
     * @param imageView
     */
    public static void loadRoundImgByServer(String path, ImageView imageView) {
        Glide.with(App.getContext())
                .load(BaseHelper.getLocalResource(path))
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    /**
     * 加载默认形状的图片
     *
     * @param path
     * @param imageView
     */
    public static void loadNormalImgByServer(String path, ImageView imageView) {
        Glide.with(App.getContext())
                .load(HttpConstant.SERVICE_HOST + HttpConstant.IMG + path)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                .into(imageView);
    }

    /**
     * 加载uri链接的图片
     *
     * @param uri
     * @param imageView
     */
    public static void loadByUri(Uri uri, ImageView imageView) {
        Glide.with(App.getContext())
                .load(uri)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }
}
