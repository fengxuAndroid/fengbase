package com.example.edz.myapplication.home;

import com.example.edz.myapplication.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainModel, IMainView> {


    public MainPresenter(IMainView view) {
        initPresenter(view);
    }




    @Override
    protected MainModel createModel() {
        return new MainModel();
    }

}
