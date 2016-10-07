package com.huake.huakemedia.domain;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/5 18:56
 * @描述	      
 */

public class Lyric {

    private String content;//内容
    private long sleepTime;//显示时间
    private long timePoint;//时间点(时刻）

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public long getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(long timePoint) {
        this.timePoint = timePoint;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "content='" + content + '\'' +
                ", sleepTime=" + sleepTime +
                ", timePoint=" + timePoint +
                '}';
    }
}
