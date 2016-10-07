package com.huake.huakemedia.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.huake.huakemedia.domain.Lyric;
import com.huake.huakemedia.utils.CommonUtils;

import java.util.ArrayList;


/*
 * @创建者     兰昱
 * @创建时间  2016/10/5 18:50
 * @描述	      自定义歌词列表
 */

public class ShowLyricView extends TextView {

    private Paint mPaint;
    private Paint mWhitePaint;
    private ArrayList<Lyric> mLyrics = new ArrayList<Lyric>();
    private int mIndex=0;//当前歌词坐标
    //控件宽高
    private int mWeidth;
    private int mHeight;
    //假定的控件行高
    private int mLineHeight;
    private float mTimePoint;
    private float mSleepTime;
    //当前播放进度
    private float currentPositon;

    public ArrayList<Lyric> getLyrics() {
        return mLyrics;
    }

    public void setLyrics(ArrayList<Lyric> lyrics) {
        mLyrics = lyrics;
    }

    public ShowLyricView(Context context) {
        this(context, null);
    }

    public ShowLyricView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShowLyricView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //歌词当然需要画笔啦
        //高亮画笔画笔
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(CommonUtils.dip2px(context, 16));
        mPaint.setTextAlign(Paint.Align.CENTER);
        //普通文本画笔
        mWhitePaint = new Paint();
        mWhitePaint.setColor(Color.WHITE);
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setTextSize(CommonUtils.dip2px(context, 16));
        mWhitePaint.setTextAlign(Paint.Align.CENTER);
        //定义行高
        mLineHeight=CommonUtils.dip2px(context,18);
       /* //我们先假设模拟一些数据
        for (int i = 0; i < 100; i++) {
            Lyric lyric = new Lyric();
            lyric.setContent(i + "我爱你啦啦啦" + i);
            lyric.setTimePoint(1000 * i);
            lyric.setSleepTime(1500 + i);
            mLyrics.add(lyric);
        }*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mLyrics != null && mLyrics.size() > 0) {

            //往上推移的效果
          float plush = 0;
            if (mSleepTime==0){
                plush=0;
            }else {
                //这一句所花的时间 ：休眠时间 mSleepTime= 移动的距离 plush： 总距离（行高）mLineHeight
                //移动的距离 =  (这一句所花的时间 ：休眠时间)* 总距离（行高）
                //屏幕的的坐标 = 行高 + 移动的距离
                plush=((currentPositon-mTimePoint)/mSleepTime)*mLineHeight;
                canvas.translate(0,-plush);
            }
            String content = mLyrics.get(mIndex).getContent();
            //绘制当前歌词
            canvas.drawText(content, mWeidth / 2, mHeight / 2, mPaint);
            //绘制前面歌词
            float temp=mHeight/2;
            for (int i = mIndex - 1; i >= 0; i--) {
                content = mLyrics.get(i).getContent();
                temp=temp-mLineHeight;
                //防止越界
                if (temp<0){
                    break;
                }
                canvas.drawText(content, mWeidth / 2,temp, mWhitePaint);
            }

            //绘制后面歌词i
            temp=mHeight/2;
            for (int i = mIndex+1; i <mLyrics.size(); i++) {
                content = mLyrics.get(i).getContent();
                temp=temp+mLineHeight;
                //防止越界
                if (temp>mHeight){
                    break;
                }
                canvas.drawText(content, mWeidth / 2,temp, mWhitePaint);
            }
        } else {
            canvas.drawText("没有歌词", mWeidth / 2, mHeight / 2, mPaint);
        }
    }

    //可以测出当前控件的宽高值
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWeidth = w;
        mHeight = h;
    }

    //设置显示的位置其实就是找到那一行，重新执行ondraw
    //找的方法是通过夹逼两个时刻的中间值，注意参数为时刻
    public void setLyricPosition(float currentPositon){
        //没有就不判断啦
        this.currentPositon= currentPositon;
        if (mLyrics == null||mLyrics.size()==0) {
            return;
        }
        for (int i = 1; i < mLyrics.size(); i++) {

            if (currentPositon<mLyrics.get(i).getTimePoint()){
                //既然这里减去1，那就应该从1开始循环
                int preTime=i-1;
                if (currentPositon>=mLyrics.get(preTime).getTimePoint()){
                    //当前增长播放的歌曲，千万不要混为一弹
                    mIndex=preTime;
                    //同时设置时间和时刻
                    mTimePoint=mLyrics.get(preTime).getTimePoint();
                    mSleepTime=mLyrics.get(preTime).getSleepTime();
                }

            }
        }
        invalidate();//主线程重绘
//        postInvalidate();//子线程重绘

    }
}
