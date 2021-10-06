package com.fushaolei.project_android.module.common;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fushaolei.project_android.adapter.NewsRecyclerAdapter;
import com.fushaolei.project_android.constant.TypeConstant;
import com.fushaolei.project_android.helper.ARouterHelper;
import com.fushaolei.project_android.base.BaseFragment;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.data.bean.Pager;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CommonFragment extends BaseFragment<CommonPresenter> implements CommonContract.View {
    private static final String CID = "chapter_id";
    private static final String TYPE = "chapter_type";
    private int chapterId;
    private int chapterType;
    private int currentPage;
    private int pageCount;

    private List<News> newsList;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private NewsRecyclerAdapter adapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        chapterId = bundle.getInt(CID, 0);
        chapterType = bundle.getInt(TYPE, 0);
        if (chapterId == 0 || chapterType == 0) {
            return;
        }
        BaseHelper.Log("chapterId = " + chapterId + " chapterType = " + chapterType);
        newsList = new ArrayList<>();
        refreshLayout = rootView.findViewById(R.id.swipe_refresh);
        refreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorWidgetBackground));
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recyclerView = rootView.findViewById(R.id.recycler_view);

        // 根据type的不同来选择布局
        if (chapterType == TypeConstant.TYPE_VEDIO) {
            adapter = new NewsRecyclerAdapter(R.layout.item_vedio, newsList);
        } else {
            adapter = new NewsRecyclerAdapter(R.layout.item_news, newsList);
        }

        adapter.setEnableLoadMore(false);
        adapter.setOnLoadMoreListener(() -> {
            BaseHelper.Log("load more listner -_-");
//            if (currentPage + 1 <= pageCount) {
//                callPresenterToGetData(currentPage + 1);
//            } else {
//                adapter.loadMoreEnd();
//            }
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            newsList = adapter.getData();
            int id = newsList.get(position).getId();
            BaseHelper.Log(" 点击的id = " + id);
            ARouterHelper.gotoAricle(id).navigation();
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);

        if (chapterType == TypeConstant.TYPE_VEDIO) {
            recyclerView.setLayoutManager(glm);
        } else {
            recyclerView.setLayoutManager(layoutManager);
        }

        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            currentPage = 0;
            callPresenterToGetData(currentPage);
        });

        callPresenterToGetData(currentPage);
    }

    private void callPresenterToGetData(int page) {
        BaseHelper.Log("newsList.size() = " + newsList.size());
        if (page == 0 && newsList.size() == 0) {
            basePresenter.transfer(1, chapterId);
        } else if (page == 0 && newsList.size() != 0) {
            hideLoading();
        } else if (page != 0) {
            basePresenter.transfer(page, chapterId);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_fragment;
    }

    @Override
    protected Context getBasisContext() {
        return getContext();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this);
    }


    public static CommonFragment getInstance(int cid, int type) {
        CommonFragment fragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CID, cid);
        bundle.putInt(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void updateUI(Pager<News> newsPager) {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        if (pageCount == 0) {
            pageCount = newsPager.getPageCount();
        }
        adapter.addData(newsPager.getRows());
        adapter.loadMoreComplete();
    }

    @Override
    public void showLoading() {
        if (refreshLayout != null && !refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void reLoading() {

    }
}
