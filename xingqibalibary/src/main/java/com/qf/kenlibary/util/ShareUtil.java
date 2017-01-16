package com.qf.kenlibary.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ken on 2016/12/28.
 * 共享参数的封装类
 */
public class ShareUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    /**
     * 初始化共享参数
     * @param context
     */
    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("configer", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 写入int的数据
     */
    public static void setInt(String name, int value){
        editor.putInt(name, value);
        editor.commit();
    }

    /**
     * 读出int类型
     * @param name
     * @return
     */
    public static int getInt(String name){
       return sharedPreferences.getInt(name, -1);
    }

    /**
     * 写入String的数据
     */
    public static void setString(String name, String value){
        editor.putString(name, value);
        editor.commit();
    }

    /**
     * 读出String类型
     * @param name
     * @return
     */
    public static String getString(String name){
        return sharedPreferences.getString(name, null);
    }
}
