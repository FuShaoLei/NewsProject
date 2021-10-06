package com.fushaolei.project_android.module.modify;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.base.BaseActivity;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.data.bean.User;
import com.fushaolei.project_android.helper.GlideHelper;
import com.fushaolei.project_android.R;

import java.io.File;

@Route(path = ARouterConstant.MODIFY_URL)
public class ModifyActivity extends BaseActivity<ModifyPresenter> implements ModifyContract.View, View.OnClickListener {
    private EditText mName;
    private EditText mSign;
    private FrameLayout frameLayout_img;
    private ImageView mAvator;
    private TextView mSubmit;
    private ImageView bar_img;
    private TextView bar_title;
    private User mUser;


    private int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        basePresenter.initPresenter();
        bar_title.setText("修改头像");
        bar_title.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        frameLayout_img = findViewById(R.id.fl_modify_avator);
        mAvator = findViewById(R.id.modify_avator);
        mName = findViewById(R.id.et_name);
        mSign = findViewById(R.id.et_sign);
        mSubmit = findViewById(R.id.tv_submit);
        bar_img = findViewById(R.id.widget_title_bar_back_img);
        bar_title = findViewById(R.id.widget_title_bar_title);

        frameLayout_img.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        bar_img.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.modify_activity;
    }

    @Override
    protected ModifyPresenter createPresenter() {
        return new ModifyPresenter(this);
    }


    @Override
    protected Context getBasisContext() {
        return ModifyActivity.this;
    }

    @Override
    public void initUI(User user) {
        this.mUser = user;
        mName.setText(mUser.getName());
        mSign.setText(mUser.getSign());
        GlideHelper.loadRoundImgByServer(mUser.getAvator(), mAvator);
    }

    @Override
    public User getEditUser() {
        if (mUser != null) {
            mUser.setName(mName.getText().toString());
            mUser.setSign(mSign.getText().toString());
            return mUser;
        }
        return new User();
    }

    @Override
    public void editSuccess() {
        BaseHelper.Toast("修改成功！");
        finish();
    }

    @Override
    public void editFail() {
        BaseHelper.Toast("修改失败");
    }

    @Override
    public void shotTast(String text) {
        BaseHelper.Toast(text);
    }

    @Override
    public void showLoading() {
        BaseHelper.showProgressDiolog(getBasisContext(), "修改中...");
    }

    @Override
    public void hideLoading() {
        BaseHelper.hideProgressDialog();
    }

    @Override
    public void showError() {

    }

    @Override
    public void reLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_modify_avator:
                selectImage();
                break;
            case R.id.tv_submit:
                basePresenter.submtChange();
                break;
            case R.id.widget_title_bar_back_img:
                finish();
                break;
        }
    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            GlideHelper.loadByUri(uri,mAvator);
            String imageAbsolutePath = BaseHelper.getImageAbsolutePath(this, uri);
            Log.e("=>", imageAbsolutePath);
            File file = new File(imageAbsolutePath);
            basePresenter.editAvator(file);
        }
    }
}
