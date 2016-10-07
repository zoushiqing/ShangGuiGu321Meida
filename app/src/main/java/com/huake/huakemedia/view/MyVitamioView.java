package com.huake.huakemedia.view;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/29 16:04
 * @描述	   自定义控件为了控制大小
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import io.vov.vitamio.widget.VideoView;

public class MyVitamioView extends VideoView {


    public MyVitamioView(Context context) {
        this(context,null);
    }

    public MyVitamioView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVitamioView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
    /**
     * 设置视频的宽和高 \n
     * by 杨光福
     * @param videoWidth 指定视频的宽
     * @param videoHeight 指定视频的高
     */
    public void setVideoSize(int videoWidth,int videoHeight){
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = videoWidth;
        params.height = videoHeight;
        setLayoutParams(params);
    }



}
