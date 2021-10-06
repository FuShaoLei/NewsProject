package com.fushaolei.server.controller;

import com.alibaba.fastjson.JSON;
import com.fushaolei.server.bean.BaseResponse;
import com.fushaolei.server.bean.Collect;
import com.fushaolei.server.bean.News;
import com.fushaolei.server.constant.HttpConstant;
import com.fushaolei.server.dao.CollectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectDao collectDao;

    /**
     * 根据新闻id和用户id来判断这个用户有无收藏这个新闻
     * 调用示例：http://localhost:8080/collect/?news_id=1&user_id=1
     */
    @GetMapping("/")
    public String getCollect(@RequestParam("news_id") int news_id, @RequestParam("user_id") int user_id) {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        Collect collect = collectDao.getCollect(news_id, user_id);
        if (collect != null) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setMsg(HttpConstant.BASE_SUCCESS);
            baseResponse.setData(collect.getStatus());
        }
        return JSON.toJSONString(baseResponse);
    }


    /**
     * 如果数据库之前没有数据，插入数据
     * 如果数据库之前有数据，修改数据，令status为其相反数
     */
    @PostMapping("/")
    public String updateCollect(@RequestParam("news_id") int news_id, @RequestParam("user_id") int user_id) {
        BaseResponse<Boolean> baseResponse = new BaseResponse<>();
        baseResponse.setData(true);
        Collect collect = collectDao.getCollect(news_id, user_id);
        // 如果数据库之前有数据，修改数据，令status为其相反数
        if (collect != null) {
            System.out.println(collect.toString());
            baseResponse.setData(!collect.getStatus());
            collectDao.updateCollect(collect.getNews_id(), collect.getUser_id(), !collect.getStatus());
        }
        // 如果数据库之前没有数据，插入数据
        else {
            collectDao.insertCollect(news_id, user_id);
        }
        baseResponse.setCode(HttpConstant.SUCCESS_CODE);
        baseResponse.setMsg("操作成功！");

        return JSON.toJSONString(baseResponse);
    }

    /**
     * 获取用户的收藏列表
     *
     * @return
     */
    @GetMapping("/list/{id}")
    public String getUserCollectList(@PathVariable(name = "id") int id) {
        BaseResponse<List<News>> baseResponse = new BaseResponse<>();
        List<News> list = collectDao.getCollectByUserId(id);
        if (list != null) {
            baseResponse.setData(list);
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setMsg("操作成功");
        }
        return JSON.toJSONString(baseResponse);


    }
}
