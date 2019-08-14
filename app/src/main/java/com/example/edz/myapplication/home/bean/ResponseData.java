package com.example.edz.myapplication.home.bean;


import com.example.edz.myapplication.utils.networkutil.entry.StatusBean;

import java.io.Serializable;

/**
 * @param <T>
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    //返回的数据
    private T data;
    //返回状态
    private String error_code;

    private String error_msg;

    public String getMsg() {
        return error_msg == null ? "" : error_msg;
    }

    public void setMsg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getCode() {
        return error_code;
    }

    public void setCode(String error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }





}
