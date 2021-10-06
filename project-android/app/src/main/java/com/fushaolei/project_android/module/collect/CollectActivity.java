package com.fushaolei.project_android.module.collect;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.adapter.NewsRecyclerAdapter;
import com.fushaolei.project_android.base.BaseActivity;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.helper.ARouterHelper;
import com.fushaolei.project_android.helper.BaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: fushaolei
 * @datetime: 2021/4/14
 * @desc:
 */
@Route(path = ARouterConstant.COLLECT_URL)
public class CollectActivity extends BaseActivity<CollectPresenter> implements CollectContract.View {
    private RecyclerView recyclerView;
    private NewsRecyclerAdapter adapter;
    private List<News> newsList;
    private TextView bar_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        newsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new NewsRecyclerAdapter(R.layout.item_news, newsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        bar_title.setText("我的收藏");
        bar_title.setVisibility(View.VISIBLE);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            newsList = adapter.getData();
            int id = newsList.get(position).getId();
            BaseHelper.Log(" 点击的id = " + id);
            ARouterHelper.gotoAricle(id).navigation();
        });
    }

    @Override
    protected void initView() {
        bar_title = findViewById(R.id.widget_title_bar_title);
        basePresenter.transfer();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collect_activity;
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected Context getBasisContext() {
        return CollectActivity.this;
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

    @Override
    public void updateUI(List<News> newsList) {
        adapter.addData(newsList);
    }
}
