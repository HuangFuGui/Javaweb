package org.huangfugui.dto;

import java.util.List;

/**
 * Created by huangfugui on 2017/4/28.
 */
public class Result<T> {

    /**
     * 1代表操作成功，0代表操作失败
     * -1表示经拦截，用户session在服务端失效，客户端要重定向到登录界面
     */
    private int code;
    private String message;
    private List<T> data;//这样才会转换成JSONArray对象[{...}]，方便客户端解析，否则只有jQ能够完成解析

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, List<T> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
