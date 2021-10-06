package com.fushaolei.project_android.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fushaolei.project_android.data.bean.Comment;
import com.fushaolei.project_android.helper.GlideHelper;
import com.fushaolei.project_android.R;

import java.util.List;

public class CommentRecyclerAdapter extends BaseQuickAdapter<Comment, CommentRecyclerAdapter.ViewHolder> {


    public CommentRecyclerAdapter(int layoutResId, @Nullable List<Comment> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, Comment item) {
        GlideHelper.loadRoundImgByServer(item.getAvator(), helper.avator);
        helper.name.setText(item.getName());
        helper.date.setText(item.getDate());
        helper.content.setText(item.getContent());
    }

    public class ViewHolder extends BaseViewHolder {
        private ImageView avator;
        private TextView name;
        private TextView date;
        private TextView content;

        public ViewHolder(View view) {
            super(view);
            avator = view.findViewById(R.id.comment_avator);
            name = view.findViewById(R.id.comment_name);
            date = view.findViewById(R.id.comment_date);
            content = view.findViewById(R.id.comment_content);
        }
    }
}
