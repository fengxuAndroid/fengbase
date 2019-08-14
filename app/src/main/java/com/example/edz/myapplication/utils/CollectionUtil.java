package com.example.edz.myapplication.utils;

import java.util.List;

/**
 * @Author: FX
 * @CreateDate: 2019/4/14 下午6:07
 * @Description: java类作用描述
 */
public class CollectionUtil {

    /**
     * 判断集合是否为空
     * @param list 集合
     * @param <T> 类型
     * @return 是/否
     */
    public  static <T>boolean isEmpty(List<T> list){
        if (null!=list&&list.size()>0){
            return false;
        }
        return true;
    }
}
