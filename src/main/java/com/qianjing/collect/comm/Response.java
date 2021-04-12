package com.qianjing.collect.comm;

public class Response<T> {
    private T data;
    private int code;
    private String msg;

    private Response(){}

    public static <T> Response<T> returnSuccess(T data,String msg){
        return new Response<T>().setData(data).setCode(200).setMsg(msg);
    }

    public static <T> Response<T> returnFail(T data,String msg){
        return new Response<T>().setData(data).setCode(500).setMsg(msg);
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Response<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
