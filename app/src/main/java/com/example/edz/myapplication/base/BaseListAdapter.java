package com.example.edz.myapplication.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter基础类
 * @author 冯旭 2019-07-19
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater inflater;

    public BaseListAdapter(Context context, List<T> list) {
        inflater = LayoutInflater.from(context);
        this.mList = list == null ? new ArrayList<T>() : list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(getItemResource(), parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        getItemView( getItem(position), holder, position, convertView);
        return convertView;
    }

    public static class ViewHolder {
        SparseArray<View> viewArray = new SparseArray<>();
        View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        public <T extends View> T getView(int resId) {
            View v = viewArray.get(resId);
            if (v == null) {
                v = convertView.findViewById(resId);
                viewArray.append(resId, v);
            }
            return (T) v;
        }
    }

    /**
     * 需要返回item布局的resource id
     *
     * @return
     */
    public abstract int getItemResource();

    /**
     * 使用该getItemView方法替换原来的getView方法
     *
     * @param position
     * @param convertView
     * @param holder
     */
    public abstract void getItemView(T t, ViewHolder holder, int position, View convertView);

}
