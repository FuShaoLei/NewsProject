package com.fushaolei.server.controller;

import com.alibaba.fastjson.JSON;
import com.fushaolei.server.bean.BaseResponse;
import com.fushaolei.server.bean.Comment;
import com.fushaolei.server.bean.InsertComment;
import com.fushaolei.server.constant.HttpConstant;
import com.fushaolei.server.dao.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论controller
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentsDao commentsDao;

    /**
     * 根据新闻id来获取评论信息
     */
    @GetMapping("/{id}")
    public String getComments(@PathVariable(name = "id") int id) {
        BaseResponse<List<Comment>> baseResponse = new BaseResponse<>();
        List<Comment> comments = commentsDao.getList(id);
        if (comments != null && comments.size() > 0) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setData(comments);
        }
        return JSON.toJSONString(baseResponse);
    }

    /**
     * 插入评论
     */
    @PostMapping("/")
    public String insertComment(@RequestBody InsertComment insertComment) {
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        int i = commentsDao.insertComment(insertComment);
        if (i != 0) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setData(insertComment.getId());
        }
        return JSON.toJSONString(baseResponse);
    }
}
