package com.huake.huakemedia.utils;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/4 19:58
 * @描述	      
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.huake.huakemedia.service.MusicPlayerService;

public class CacheUtils {

    //保存int数值
    public static void putInt(Context context,String key,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences("hauke",Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key,mode).commit();
    }
    //读取int数值
    public static int getInt(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("hauke",Context.MODE_PRIVATE);
        int anInt = sharedPreferences.getInt(key, MusicPlayerService.REPEAT_NORMAL);
        return anInt;
    }
}
