package com.huake.huakemedia.domain;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/2 18:53
 * @描述	      
 */

import java.io.Serializable;
import java.util.List;

public class NetMediaItem implements Serializable {

    /**
     * id : 62733
     * movieName : 《侠探杰克：永不回头》IMAX版预告片
     * coverImg : http://img5.mtime.cn/mg/2016/09/30/145315.50134099.jpg
     * movieId : 226179
     * url : http://vfx.mtime.cn/Video/2016/09/30/mp4/160930091502602040_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/09/30/mp4/160930091502602040.mp4
     * videoTitle : 侠探杰克：永不回头 IMAX版预告片
     * videoLength : 85
     * rating : -1
     * type : ["动作","冒险","犯罪","悬疑","惊悚"]
     * summary : 阿汤哥贴身肉搏招招毙命
     */

    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public static class TrailersBean implements Serializable {
        private long id;
        private String movieName;
        private String coverImg;
        private long movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private long videoLength;
        private double rating;
        private String summary;
        private List<String> type;

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public long getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public long getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
