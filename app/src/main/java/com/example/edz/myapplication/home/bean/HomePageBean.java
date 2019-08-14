package com.example.edz.myapplication.home.bean;

import java.io.Serializable;

/**
 * Author:     FX
 * Time:       2019/6/19 下午5:09
 * Description:java类作用描述
 */
public class HomePageBean implements Serializable {


    private String store_address;
    private String store_id;
    private String store_name;
    private String tel;
    private String token;
    private String username;

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
