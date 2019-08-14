package com.example.edz.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.edz.myapplication.utils.CollectionUtil;
import com.example.edz.myapplication.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private LayoutInflater layoutInflater;

    protected List<T> mList;

    protected int layoutId;
    private Context mContext;

    protected String toast  = "暂无数据";

    public BaseRecycleAdapter(Context context, List<T> mList, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mList = mList == null ? new ArrayList<>() : mList;
        this.layoutId = layoutId;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindData(holder, mList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract void bindData(BaseViewHolder holder, T data,int position);


    /**
     * 添加没数据时的提示
     */
    public void setNoDataToast(String toast){
        this.toast= toast;
    }

    /**
     * 刷新数据
     * @param data
     */
    public void notifyChangeData(List<T> data) {
        if (CollectionUtil.isEmpty(data)){
            if (!StringUtils.isEmpty(toast)) {
                Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
            }
            mList.clear();
            notifyDataSetChanged();
            return;
        }
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param data
     */
    public void addChangeData(List<T> data) {
     if (CollectionUtil.isEmpty(data)){
//         Toast.makeText(mContext, Constant.TOAST_NO_MORE_DATA, Toast.LENGTH_SHORT).show();
         return;
     }
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getList(){
        return mList;
    }
}