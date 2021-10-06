package com.fushaolei.server.dao;

import com.fushaolei.server.bean.News;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsDao {
    List<News> findByPager(Map<String, Object> params);
    News findNewsById(int id);
    int count(int cid);
}
