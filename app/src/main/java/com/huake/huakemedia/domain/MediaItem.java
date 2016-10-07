package com.huake.huakemedia.domain;

import java.io.Serializable;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/28 16:53
 * @描述	      
 */
//视频信息
public class MediaItem implements Serializable {
    private String name;
    private String artist;
    private String data;//视频绝对地址
    private long size;
    private long duration;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + data + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                '}';
    }
}
