package com.fushaolei.server.bean;

import com.fushaolei.server.constant.HttpConstant;

public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

    public BaseResponse() {
        this.code = HttpConstant.FAIL_CODE;
        this.msg = HttpConstant.BASE_ERROR;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        if (code == HttpConstant.SUCCESS_CODE) {
            this.msg = HttpConstant.BASE_SUCCESS;
        }
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
