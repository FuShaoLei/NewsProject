package com.fushaolei.project_android.module.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fushaolei.project_android.adapter.CommentRecyclerAdapter;
import com.fushaolei.project_android.base.BaseActivity;
import com.fushaolei.project_android.cutom.CommentDialog;
import com.fushaolei.project_android.data.bean.Comment;
import com.fushaolei.project_android.constant.ARouterConstant;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.helper.GlideHelper;
import com.fushaolei.project_android.helper.ImageGetterHelper;
import com.fushaolei.project_android.helper.MMKVHelper;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

@Route(path = ARouterConstant.ARTICLE_URL)
public class ArticleActivity extends BaseActivity<ArticlePresenter> implements ArticleContract.View, View.OnClickListener {
    private TextView title;
    private TextView meta;
    private TextView content;
    private ImageView back_img;

    private JzvdStd jzvdStd;

    private RecyclerView recyclerView;
    private CommentRecyclerAdapter adapter;

    List<Comment> comments;

    private TextView comment_wrrper;

    private CommentDialog commentDialog;

    private ImageView collect;
    private ImageView share;
    private News mNews;

    @Autowired(name = "id")
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        com.alibaba.android.arouter.launcher.ARouter.getInstance().inject(this);
        BaseHelper.Log("传过来的 id = " + id);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        comments = new ArrayList<>();
        adapter = new CommentRecyclerAdapter(R.layout.item_comment, comments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBasisContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        basePresenter.transfer();
        basePresenter.updateComments();
        basePresenter.updateCollect();
    }

    @Override
    protected void initView() {
        jzvdStd = findViewById(R.id.jz_video);
        title = findViewById(R.id.article_title);
        meta = findViewById(R.id.article_meta);
        content = findViewById(R.id.article_content);
        back_img = findViewById(R.id.widget_title_bar_back_img);
        recyclerView = findViewById(R.id.commment_recycler_view);

        comment_wrrper = findViewById(R.id.tv_comment_wrapper);

        collect = findViewById(R.id.iv_collect);
        share = findViewById(R.id.iv_share);

        back_img.setOnClickListener(this);
        comment_wrrper.setOnClickListener(this);
        collect.setOnClickListener(this);
        share.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.article_activity;
    }

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter(this);
    }


    @Override
    protected Context getBasisContext() {
        return ArticleActivity.this;
    }

    @Override
    public void updateUI(News news) {
        mNews = news;
        if (news.getVedio() != null && news.getVedio().length() > 0) {
            jzvdStd.setVisibility(View.VISIBLE);
            jzvdStd.setUp(BaseHelper.getLocalResource(news.getVedio()), news.getTitle());
            GlideHelper.loadNormalImgByServer(news.getCover(), jzvdStd.posterImageView);
            // 进入后就加载视频
            jzvdStd.startPreloading();
            jzvdStd.startVideoAfterPreloading();
            title.setVisibility(View.GONE);
        } else {
            jzvdStd.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);
        }

        title.setText(news.getTitle());
        meta.setText(news.getAuthor() + "    " + news.getDate());

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int max_width = wm.getDefaultDisplay().getWidth();
        int max_height = wm.getDefaultDisplay().getHeight();
        BaseHelper.Log("max_width = " + max_width + " max_height = " + max_height);

        if (news.getContent() != null && news.getContent().length() > 0) {
            content.setText(Html.fromHtml(news.getContent(), new ImageGetterHelper(content, max_width, max_height), null));
        }
    }

    @Override
    public void updateComment(List<Comment> list) {
        adapter.replaceData(list);
//        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getNewsId() {
        BaseHelper.Log("id = " + id);
        return id;
    }

    @Override
    public void insertCommentSuccess() {
        basePresenter.updateComments();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.widget_title_bar_back_img:
                finish();
                break;
            case R.id.tv_comment_wrapper:
                commentDialog = new CommentDialog(new CommentListner());
                commentDialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.iv_collect:
                collectArticle();
                break;
            case R.id.iv_share:
                shareSomething();
                break;
        }
    }

    /**
     * 更新点赞图片，触发前提：用户已登录
     */
    @Override
    public void updateCollect(boolean status) {
        if (status) {
            collect.setImageResource(R.drawable.ic_star_already);
            BaseHelper.Log("已收藏");
        } else {
            collect.setImageResource(R.drawable.ic_star);
            BaseHelper.Log("该用户没有收藏");
        }
    }

    /**
     * 收藏文章，如果未登录，则提示登录
     * 如果登录了 就执行操作，并 updateCollect
     */
    private void collectArticle() {
        if (MMKVHelper.isUser()) {
            basePresenter.clickOrCancelCollect();
        } else {
            // 或者直接拉起登录页面
            BaseHelper.Toast("请登录！");
        }
    }

    public void shareSomething() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mNews.getTitle());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "分享到..."));
    }


    public class CommentListner implements CommentDialog.SendBackListner {
        @Override
        public void sendBack(String text) {
            basePresenter.insertComment(text);
            commentDialog.dismiss();
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
