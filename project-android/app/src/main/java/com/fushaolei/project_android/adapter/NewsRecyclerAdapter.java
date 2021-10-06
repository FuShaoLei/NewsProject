package com.fushaolei.project_android.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fushaolei.project_android.data.bean.News;
import com.fushaolei.project_android.R;
import com.fushaolei.project_android.helper.BaseHelper;
import com.fushaolei.project_android.helper.GlideHelper;

import java.util.List;

public class NewsRecyclerAdapter extends BaseQuickAdapter<News, NewsRecyclerAdapter.ViewHolder> {

    public NewsRecyclerAdapter(int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, News item) {
        helper.title.setText(item.getTitle());
        helper.author.setText(item.getAuthor());
        helper.date.setText(item.getDate());
        if (item.getCover() != null && item.getCover().length() > 0) {
            GlideHelper.loadNormalImgByServer(item.getCover(), helper.cover);
        }
    }

    public class ViewHolder extends BaseViewHolder {
        private TextView title;
        private TextView author;
        private TextView date;
        private ImageView cover;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.news_title);
            author = view.findViewById(R.id.news_author);
            date = view.findViewById(R.id.news_date);
            cover = view.findViewById(R.id.iv_cover);
        }
    }
}
