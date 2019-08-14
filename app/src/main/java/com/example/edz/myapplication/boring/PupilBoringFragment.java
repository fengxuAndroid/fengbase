package com.example.edz.myapplication.boring;

import com.example.edz.myapplication.R;
import com.example.edz.myapplication.base.BaseFragment;
import com.example.edz.myapplication.base.BasePresenter;

/**
 * @Author: FX
 * @CreateDate: 2019/4/14 下午6:39
 * @Description: 瞳趣
 */
public class PupilBoringFragment extends BaseFragment {


    @Override
    protected int getViewResId() {
        return R.layout.frag_main_pupil_boring;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void initView() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void stopLoading() {

    }
}
