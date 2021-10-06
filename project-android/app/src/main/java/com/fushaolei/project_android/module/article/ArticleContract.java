package com.fushaolei.project_android.module.article;

import com.fushaolei.project_android.base.BaseView;
import com.fushaolei.project_android.data.bean.Comment;
import com.fushaolei.project_android.data.bean.News;

import java.util.List;

public interface ArticleContract {
    interface View extends BaseView {
        void updateUI(News news);

        void updateComment(List<Comment> list);

        int getNewsId();

        void insertCommentSuccess();

        // 初始化点赞
        void updateCollect(boolean status);
    }

    interface Presenter {
        void transfer();

        void updateComments();

        void insertComment(String text);

        // 初始化点赞
        void updateCollect();
        // 用户取消点赞
        void clickOrCancelCollect();
    }
}
