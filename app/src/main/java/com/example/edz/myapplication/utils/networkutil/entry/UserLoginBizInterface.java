package com.example.edz.myapplication.utils.networkutil.entry;


import com.example.edz.myapplication.home.bean.LoginBean;

public interface UserLoginBizInterface {

    /**
     * 登录
     */
    void login();

    /**
     * 登录成功
     */
    void loginSuccess(LoginBean data);


    /**
     * 登录成功
     */
    void updataSuccess(LoginBean data);

    /**
     * 登录失败
     *
     * @param error
     */
    void loginFailed(String error);

    /**
     * 加载用户信息
     *
     * @return
     */
    LoginBean readUserInfo();

    /**
     * 保存用户信息
     *
     * @param info
     */
    void saveUserInfo(LoginBean info);

}
