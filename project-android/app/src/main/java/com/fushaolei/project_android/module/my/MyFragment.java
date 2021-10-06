package com.fushaolei.project_android.module.my;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fushaolei.project_android.base.BaseFragment;
import com.fushaolei.project_android.data.bean.User;
import com.fushaolei.project_android.helper.ARouterHelper;
import com.fushaolei.project_android.helper.MMKVHelper;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.helper.GlideHelper;
import com.fushaolei.project_android.MainActivity;
import com.fushaolei.project_android.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View, View.OnClickListener {
    private static final String TAG = "MyFragment";
    private TextView login;
    private TextView unlogin;
    private ImageView avator;
    private TextView mName;
    private TextView mSign;
    private SwitchMaterial mSwitch;

    private FrameLayout fl_edit;
    private FrameLayout fl_collect;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        basePresenter.tansfer();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        login = rootView.findViewById(R.id.tv_login);
        unlogin = rootView.findViewById(R.id.tv_unlogin);
        mName = rootView.findViewById(R.id.tv_name);
        mSign = rootView.findViewById(R.id.tv_desc);
        mSwitch = rootView.findViewById(R.id.switch_night);
        fl_edit = rootView.findViewById(R.id.fl_edit);
        fl_collect = rootView.findViewById(R.id.fl_collect);
        avator = rootView.findViewById(R.id.iv_avator);

        login.setOnClickListener(this);
        unlogin.setOnClickListener(this);
        mSwitch.setOnClickListener(this);
        fl_edit.setOnClickListener(this);
        fl_collect.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.my_fragment;
    }

    @Override
    protected Context getBasisContext() {
        return getContext();
    }

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_login:
                ARouterHelper.gotoLogin().navigation();
                break;
            case R.id.tv_unlogin:
                MMKVHelper.deleteUser();
                basePresenter.tansfer();
                break;
            case R.id.fl_edit:
                ARouterHelper.gotoModify().navigation();
                break;
            case R.id.fl_collect:
                ARouterHelper.gotoCollect().navigation();
                break;
            case R.id.switch_night: {
                if (mSwitch.isChecked()) {
                    BaseHelper.Toast("switch状态：开");
                    MMKVHelper.setNightMod();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    BaseHelper.Toast("switch状态：关");
                    MMKVHelper.setDayMod();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                MainActivity.lastFragment = 2;
                getActivity().recreate();
                break;
            }
        }
    }


    @Override
    public void updateUI(User user) {
        login.setVisibility(View.GONE);
        unlogin.setVisibility(View.VISIBLE);

        mName.setText(user.getName());
        mSign.setText(user.getSign());
        GlideHelper.loadRoundImgByServer(user.getAvator(), avator);

        if (MMKVHelper.isNightMod()) {
            mSwitch.setChecked(true);
        }
    }

    @Override
    public void updateUI() {
        unlogin.setVisibility(View.GONE);
        login.setVisibility(View.VISIBLE);

        avator.setImageResource(R.drawable.ic_face);
        mName.setText("请登录");
        mSign.setText("这个人很懒，什么都没留下");

        if (MMKVHelper.isNightMod()) {
            mSwitch.setChecked(true);
        }
    }

    public static String getTAG() {
        return TAG;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void reLoading() {

    }
}
