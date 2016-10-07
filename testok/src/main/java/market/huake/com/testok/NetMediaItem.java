package market.huake.com.testok;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/2 18:53
 * @描述	      
 */

import java.util.List;

public class NetMediaItem {

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

    public static class TrailersBean {
        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public int getId() {
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

        public int getMovieId() {
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

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public int getRating() {
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
