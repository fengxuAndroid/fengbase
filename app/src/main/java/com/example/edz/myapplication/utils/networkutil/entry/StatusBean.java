package com.example.edz.myapplication.utils.networkutil.entry;

import java.io.Serializable;

public class StatusBean implements Serializable {
    private String msgcode;
    private String msg;

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return msgcode;
    }

    public void setCode(String msgcode) {
        this.msgcode = msgcode;
    }
}
