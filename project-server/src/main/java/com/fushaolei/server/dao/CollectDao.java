package com.fushaolei.server.dao;

import com.fushaolei.server.bean.Collect;
import com.fushaolei.server.bean.News;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectDao {
    // 通过新闻id来获取收藏数据
    Collect getCollect(@Param("news_id") int news_id, @Param("user_id") int user_id);

    // 插入收藏
    int insertCollect(@Param("news_id") int news_id, @Param("user_id") int user_id);

    // 取消收藏
    int updateCollect(@Param("news_id") int news_id, @Param("user_id") int user_id, @Param("status") boolean status);

    // 根据用户id来获取收藏数据
    List<News> getCollectByUserId(@Param("id") int id);
}
