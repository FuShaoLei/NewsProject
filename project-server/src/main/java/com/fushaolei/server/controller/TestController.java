package com.fushaolei.server.controller;

import com.alibaba.fastjson.JSON;
import com.fushaolei.server.bean.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        BaseResponse<String> baseResponse = new BaseResponse<>(200, "测试成功！");
        return JSON.toJSONString(baseResponse);
    }
}
