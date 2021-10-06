package com.fushaolei.server.dao;

import com.fushaolei.server.bean.Comment;
import com.fushaolei.server.bean.InsertComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao {
    List<Comment> getList(int id);
    int insertComment(InsertComment insertComment);
}
