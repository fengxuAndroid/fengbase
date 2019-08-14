package com.example.edz.myapplication.base;

/**
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
//基础接口
public interface IBaseView {
    //显示进度
    void showLoading(String msg);

    //隐藏进度条
    void stopLoading();
}
