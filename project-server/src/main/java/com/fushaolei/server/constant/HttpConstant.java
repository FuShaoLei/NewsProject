package com.fushaolei.server.constant;

public interface HttpConstant {
    String HOST = "http://192.168.0.105:8080";
    String FILE = "/img/";

    // status code
    int FAIL_CODE = 301;
    int SUCCESS_CODE = 200;

    // msg
    String LOGIN_SUCCESS = "登录成功！";
    String LOGIN_FAIL = "登录失败，请检查你的密码是否正确！";
    String REGISTER_SUCCESS = "注册成功！";
    String UPDATE_SUCCESS = "修改成功！";

    String BASE_ERROR = "请求资源失败(╯°口°)╯(┴—┴";
    String BASE_SUCCESS = "请求资源成功✿ヽ(°▽°)ノ✿";

    String TEST_SUCCESS = "测试成功！";
}
