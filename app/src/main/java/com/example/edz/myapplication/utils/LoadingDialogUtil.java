package com.example.edz.myapplication.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edz.myapplication.R;

/**
 * @ Author: FX
 * @ CreateDate: 2019/4/14 下午5:12
 * @ Description: :弹窗浮动加载进度条
 * @author edz
 */
public class LoadingDialogUtil {
    public View mDialogView ;
    public   Dialog mLoadingDialog;

    /*
     * 加载数据对话框
     */

    /**
     * 显示加载对话框
     *
     * @param context    上下文
     * @param msg        对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {

        if (mLoadingDialog==null){
            mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(mDialogView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }
        //提示
        ((TextView) mDialogView.findViewById(R.id.id_tv_loading_dialog_text)).setText(msg);
        mLoadingDialog.show();
        return mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public  void cancelDialogForLoading() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
        }
    }

}
