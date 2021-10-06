package com.fushaolei.server.controller;

import com.alibaba.fastjson.JSON;
import com.fushaolei.server.bean.BaseResponse;
import com.fushaolei.server.bean.News;
import com.fushaolei.server.bean.Pager;
import com.fushaolei.server.bean.Tree;
import com.fushaolei.server.dao.NewsDao;
import com.fushaolei.server.dao.TreeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class ListController {

    @Autowired
    NewsDao mNewsDao;

    @Autowired
    TreeDao mTreeDao;

    @GetMapping("/list")
    public String getLists(@RequestParam("type") int type) {
        BaseResponse<List<Tree>> baseResponse = new BaseResponse<>(301, null);
        List<Tree> mList = mTreeDao.findTree(type);
        if (mList != null && mList.size() > 0) {
            baseResponse.setCode(200);
            baseResponse.setData(mList);
        }
        return JSON.toJSONString(baseResponse);
    }

    @GetMapping("/list/{page}/json/{cid}")
    public String getNews(@PathVariable(name = "page") int page, @PathVariable(name = "cid") int cid) {
        int total = mNewsDao.count(cid);
        int size = 10;

        int pageCount = total / size;
//        System.out.println("pageCount = " + pageCount);
        if (total % size > 0) {
            pageCount += 1;
        }

        BaseResponse<Pager<News>> baseResponse = new BaseResponse<>(301, null);
        Pager<News> pager = new Pager<>();

        Map<String, Object> params = new HashMap<>();
        params.put("page", (page - 1) * size);
        params.put("size", size);
        params.put("cid", cid);

        List<News> list = mNewsDao.findByPager(params);
        if (cid > 0 && pageCount >= page && list != null && list.size() > 0) {
            baseResponse.setCode(200);

            pager.setPage(page);
            pager.setSize(size);
            pager.setRows(list);
            pager.setPageCount(pageCount);
            pager.setTotal(total);

            baseResponse.setData(pager);
        }

        return JSON.toJSONString(baseResponse);
    }


    @GetMapping("/{id}")
    public String getNewsById(@PathVariable("id") int id) {
        BaseResponse<News> baseResponse = new BaseResponse<>(301, null);
        News news = mNewsDao.findNewsById(id);
        if (news != null) {
            baseResponse.setCode(200);
            baseResponse.setData(news);
        }
        return JSON.toJSONString(baseResponse);
    }
}
