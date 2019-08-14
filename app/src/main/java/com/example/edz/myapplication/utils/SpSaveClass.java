package com.example.edz.myapplication.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.edz.myapplication.home.bean.LoginBean;
import com.google.gson.Gson;

public class SpSaveClass
{

	public static SpSaveClass instance;

	private SpUtil spUtil = null;

	private Gson mGson;

	private SpSaveClass(Context context)
	{
		mGson = new Gson();
		spUtil = SpUtil.init(context);
	}

	public static SpSaveClass getInstance(Context context)
	{
		if (instance == null) {
			instance = new SpSaveClass(context);
		}
		return instance;
	}

	/**
	 * 
	 * @param obj
	 */
	public <T> void saveClass(T obj)
	{
		if (obj != null) {
			spUtil.commit(obj.getClass().getSimpleName(), mGson.toJson(obj) );
		}
	}
	/**
	 *
	 * @param key
	 * @return
	 */
	public <T> T readClassTwo(Class<T> key)
	{
		try
		{
			String saveClassStr = spUtil.readString(key.getSimpleName());

			if (!TextUtils.isEmpty(saveClassStr))
			{
				return mGson.fromJson(saveClassStr, key);
			}

			return null;

		} catch (Exception e)
		{
			Log.e(getClass().getSimpleName(), "decode " + key.getSimpleName() + "\n" + e.toString());
			return null;
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public LoginBean readClass(Class<LoginBean> key)
	{
		try
		{
			String saveClassStr = spUtil.readString(key.getSimpleName());

			if (!TextUtils.isEmpty(saveClassStr))
			{
				return mGson.fromJson(saveClassStr, key);
			}

			return new LoginBean();

		} catch (Exception e)
		{
			Log.e(getClass().getSimpleName(), "decode " + key.getSimpleName() + "\n" + e.toString());
			return new LoginBean();
		}
	}

}
