package com.hzp.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by hzp on 2019/6/4 12:26
 * 作者：codehan
 * 描述：
 */
public class SharedPreferencesTool {
    private static SharedPreferences sp;

    /**
     * 保存boolean信息的操作
     *@param context
     *@param key
     *@param value
     * 2016-11-1 上午10:44:07
     */
    public static void saveBoolean(Context context,String key,boolean value){
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取boolean信息的值
     *@param context
     *@param key
     *@param defvalue
     *@return
     * 2016-11-1 上午10:46:14
     */
    public static boolean getBoolean(Context context,String key,boolean defvalue){
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defvalue);
    }

    /**
     * 保存String信息的操作
     *@param context
     *@param key
     *@param value
     * 2016-11-1 上午10:44:07
     */
    public static void saveString(Context context,String key,String value){
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取String信息的值
     *@param context
     *@param key
     *@param defvalue
     *@return
     * 2016-11-1 上午10:46:14
     */
    public static String getString(Context context,String key,String defvalue){
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defvalue);
    }

}
