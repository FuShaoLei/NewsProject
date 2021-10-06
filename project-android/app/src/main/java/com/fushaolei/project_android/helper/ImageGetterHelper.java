package com.fushaolei.project_android.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.app.App;

public class ImageGetterHelper implements Html.ImageGetter {
    private TextView textView;
    private int max_width;
    private int max_height;

    public ImageGetterHelper(TextView textView,int max_width,int max_height) {
        this.textView = textView;
        this.max_width = max_width;
        this.max_height = max_height;
    }
     @Override
    public Drawable getDrawable(String source) {
        Log.e("=>", source);
        final LevelListDrawable drawable = new LevelListDrawable();


        Glide.with(App.getContext())
                .load(source)
                .error(R.drawable.ic_error_outline)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        int with = resource.getIntrinsicWidth();
                        int height = resource.getIntrinsicHeight();
                        BaseHelper.Log("with = " + with + " height = " + height);

                        drawable.addLevel(1, 1, resource);
                        drawable.setBounds(0, 0, max_width-110, height);
                        drawable.setLevel(1);
                        textView.invalidate();
                        textView.setText(textView.getText());
                    }
                });
        return drawable;
    }
}