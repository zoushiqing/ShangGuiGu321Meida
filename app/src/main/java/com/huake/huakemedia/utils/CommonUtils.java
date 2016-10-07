package com.huake.huakemedia.utils;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/28 17:37
 * @描述	      
 */


import android.content.Context;
import android.net.TrafficStats;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class CommonUtils {

    private static long lastTimeStamp=0;
    private static long lastTotalRxBytes=0;
    public static String timeToString(int millions){
        Formatter formatter = new Formatter();
        int totalSeconds = millions / 1000;
        int seconds=totalSeconds%60;
        int minutes=(totalSeconds/60)%60;
        int hours=totalSeconds/3600;
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", hours, minutes, seconds)
                    .toString();
        } else {
            return formatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    /**
     * 得到系统时间
     * @return
     */
    public static String getSystemTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return  simpleDateFormat.format(new Date());
    }
    /**
     * 判断是不是网路地址
     * @return
     */
    public static boolean isNetUrl(String url) {
        boolean reault = false;
        if (url != null) {
            if (url.toLowerCase().startsWith("http") || url.toLowerCase().startsWith("rtsp") || url.toLowerCase().startsWith("mms")) {
                reault = true;
            }
        }
        return reault;
    }


    /**
     * 得到网络速度
     * @param context  实践证明算法有问题，我把网断掉之后还能显示网速，也是没谁了
     * @return
     */
    public static String  getNetSpeed(Context context) {


        String netSpeed = "0 kb/s";
        long nowTotalRxBytes = TrafficStats.getUidRxBytes(context.getApplicationInfo().uid)==TrafficStats.UNSUPPORTED ? 0 :(TrafficStats.getTotalRxBytes()/1024);//转为KB;
        long nowTimeStamp = System.currentTimeMillis();
        long speed = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换

        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
        netSpeed  = String.valueOf(speed) + " kb/s";
        return  netSpeed;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
