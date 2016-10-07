package com.huake.huakemedia.base;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/28 15:04
 * @描述	      
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BasePager {

    public View rootView;
    public Context mContext;
    public boolean isInitData;

    public BasePager(Context context) {
        mContext=context;
        rootView=initView();
    }
    //必须实现
    public abstract  View initView();
    //选择实现
    public  void initData(){

    };
}
