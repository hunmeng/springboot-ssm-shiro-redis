package com.hunmengwl.projectlove.utils;

import com.hunmengwl.projectlove.commons.CodeAndMsgEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseResult<T> implements Serializable{

    //状态
    private Integer status;

    //状态信息
    private String message;

    //数据信息  ResponseResult<Void>
    private  T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseResult(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseResult(String message, T data) {
        this.message = message;
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
