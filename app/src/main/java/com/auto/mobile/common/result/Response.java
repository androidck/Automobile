package com.auto.mobile.common.result;

/**
 * 同一数据格式处理
 * @param <T>
 */
public class Response<T> {

    private int code;//错误码

    private String msg;//提示信息

    private T Data;//返回数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
