package com.fushaolei.project_android.module.vedio;

import android.content.Context;

import androidx.viewpager.widget.ViewPager;

import com.fushaolei.project_android.adapter.NormalPagerAdapter;
import com.fushaolei.project_android.base.BaseFragment;
import com.fushaolei.project_android.base.BasePresenter;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.constant.TypeConstant;
import com.fushaolei.project_android.data.bean.Tree;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class VedioFragment extends BaseFragment<VedioPresenter> implements VedioContract.View {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> mTitles = new ArrayList<>();
    private List<Integer> mNums = new ArrayList<>();

    private NormalPagerAdapter mAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTabLayout = rootView.findViewById(R.id.tab_layout);
        mViewPager = rootView.findViewById(R.id.view_pager);

        basePresenter.transfer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.vedio_fragment;
    }

    @Override
    protected Context getBasisContext() {
        return getContext();
    }

    @Override
    protected VedioPresenter createPresenter() {
        return new VedioPresenter(this);
    }


    @Override
    public void updateUI(List<Tree> list) {
        mNums.clear();
        mTitles.clear();
        for (Tree t : list) {
            mNums.add(t.getId());
            mTitles.add(t.getChapter_name());
            mTabLayout.addTab(mTabLayout.newTab().setText(t.getChapter_name()));
        }
        if (isAdded()) {
            mAdapter = new NormalPagerAdapter(getChildFragmentManager(), mTitles, mNums, TypeConstant.TYPE_VEDIO);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabIndicatorFullWidth(false);
        if (isAdded()) {
            mAdapter.notifyDataSetChanged();
        }
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
