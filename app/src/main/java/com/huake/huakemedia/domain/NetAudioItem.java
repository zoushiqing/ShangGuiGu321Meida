package com.huake.huakemedia.domain;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/7 17:18
 * @描述	      
 */

import java.util.List;

public class NetAudioItem {

    /**
     * count : 4113
     * np : 1.475815253E9
     */

    private InfoBean info;
    /**
     * status : 4
     * comment : 59
     * top_comments : [{"voicetime":0,"status":0,"cmt_type":"text","precid":0,"content":"就凭帽子上那两个字 我就得赞死你","like_count":59,"u":{"header":["http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100","http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100"],"sex":"m","uid":"15535364","name":"一个神秘而诡异的地方"},"preuid":0,"passtime":"2016-10-07 03:51:14","voiceuri":"","id":65578409},{"voicetime":7,"status":0,"cmt_type":"audio","precid":0,"content":"","like_count":12,"u":{"header":["http://wimg.spriteapp.cn/profile/large/2016/04/04/5701fa2a9f2ff_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/04/04/5701fa2a9f2ff_mini.jpg"],"sex":"m","uid":"17836339","name":"大眼哥233"},"preuid":0,"passtime":"2016-10-07 10:57:07","audio":{"duration":7,"audio":["http://wvoice.spriteapp.cn/voice/2016/1007/bc06ff58-8c39-11e6-9742-d4ae5296039d.mp3"]},"voiceuri":"http://wvoice.spriteapp.cn/voice/2016/1007/bc06ff58-8c39-11e6-9742-d4ae5296039d.mp3","id":65593684},{"voicetime":10,"status":0,"cmt_type":"audio","precid":0,"content":"","like_count":6,"u":{"header":["http://qzapp.qlogo.cn/qzapp/100336987/2F84D55DB2090C417F5DB49355803748/100","http://qzapp.qlogo.cn/qzapp/100336987/2F84D55DB2090C417F5DB49355803748/100"],"sex":"m","uid":"9373666","name":"!!!K5G"},"preuid":0,"passtime":"2016-10-07 16:57:54","audio":{"duration":10,"audio":["http://wvoice.spriteapp.cn/voice/2016/1007/57f76392ce7a5.mp3"]},"voiceuri":"http://wvoice.spriteapp.cn/voice/2016/1007/57f76392ce7a5.mp3","id":65617865}]
     * tags : [{"id":1,"name":"搞笑"},{"id":62,"name":"内涵"},{"id":156,"name":"牛人"},{"id":164,"name":"游戏"},{"id":3176,"name":"冷知识"}]
     * bookmark : 124
     * text : 超厉害！中国顶级B-BOX震惊全世界的表演~
     * up : 998
     * share_url : http://a.f.budejie.com/share/21159389.html?wx.qq.com
     * down : 80
     * forward : 74
     * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/09/10/57d3c9cd7780b_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/09/10/57d3c9cd7780b_mini.jpg"],"is_v":true,"uid":"18560424","is_vip":false,"name":"ID名"}
     * passtime : 2016-10-07 16:50:03
     * video : {"playfcount":1255,"height":478,"width":852,"video":["http://wvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd.mp4","http://dvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd.mp4"],"download":["http://wvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpc.mp4","http://dvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpc.mp4"],"duration":247,"playcount":25455,"thumbnail":["http://wimg.spriteapp.cn/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg","http://dimg.spriteapp.cn/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg"],"thumbnail_small":["http://wimg.spriteapp.cn/crop/150x150/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg"]}
     * type : video
     * id : 21159389
     */

    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        private int count;
        private double np;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getNp() {
            return np;
        }

        public void setNp(double np) {
            this.np = np;
        }
    }

    public static class ListBean {
        private int status;
        private String comment;
        private String bookmark;
        private String text;
        private String up;
        private String share_url;
        private int down;
        private int forward;
        /**
         * header : ["http://wimg.spriteapp.cn/profile/large/2016/09/10/57d3c9cd7780b_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/09/10/57d3c9cd7780b_mini.jpg"]
         * is_v : true
         * uid : 18560424
         * is_vip : false
         * name : ID名
         */

        private UBean u;
        private String passtime;
        /**
         * playfcount : 1255
         * height : 478
         * width : 852
         * video : ["http://wvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd.mp4","http://dvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd.mp4"]
         * download : ["http://wvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpc.mp4","http://dvideo.spriteapp.cn/video/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpc.mp4"]
         * duration : 247
         * playcount : 25455
         * thumbnail : ["http://wimg.spriteapp.cn/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg","http://dimg.spriteapp.cn/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg"]
         * thumbnail_small : ["http://wimg.spriteapp.cn/crop/150x150/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg","http://dimg.spriteapp.cn/crop/150x150/picture/2016/1007/cf6fb698-8bee-11e6-abd2-d4ae5296039d_wpd_33.jpg"]
         */

        private VideoBean video;
        private ImageBean image;
        private GifBean gif;

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public GifBean getGif() {
            return gif;
        }

        public void setGif(GifBean gif) {
            this.gif = gif;
        }

        private String type;
        private String id;
        /**
         * voicetime : 0
         * status : 0
         * cmt_type : text
         * precid : 0
         * content : 就凭帽子上那两个字 我就得赞死你
         * like_count : 59
         * u : {"header":["http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100","http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100"],"sex":"m","uid":"15535364","name":"一个神秘而诡异的地方"}
         * preuid : 0
         * passtime : 2016-10-07 03:51:14
         * voiceuri :
         * id : 65578409
         */

        private List<TopCommentsBean> top_comments;
        /**
         * id : 1
         * name : 搞笑
         */

        private List<TagsBean> tags;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public UBean getU() {
            return u;
        }

        public void setU(UBean u) {
            this.u = u;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TopCommentsBean> getTop_comments() {
            return top_comments;
        }

        public void setTop_comments(List<TopCommentsBean> top_comments) {
            this.top_comments = top_comments;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class UBean {
            private boolean is_v;
            private String uid;
            private boolean is_vip;
            private String name;
            private List<String> header;

            public boolean isIs_v() {
                return is_v;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_vip() {
                return is_vip;
            }

            public void setIs_vip(boolean is_vip) {
                this.is_vip = is_vip;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getHeader() {
                return header;
            }

            public void setHeader(List<String> header) {
                this.header = header;
            }
        }

        public static class VideoBean {
            private int playfcount;
            private int height;
            private int width;
            private int duration;
            private int playcount;
            private List<String> video;
            private List<String> download;
            private List<String> thumbnail;
            private List<String> thumbnail_small;

            public int getPlayfcount() {
                return playfcount;
            }

            public void setPlayfcount(int playfcount) {
                this.playfcount = playfcount;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getPlaycount() {
                return playcount;
            }

            public void setPlaycount(int playcount) {
                this.playcount = playcount;
            }

            public List<String> getVideo() {
                return video;
            }

            public void setVideo(List<String> video) {
                this.video = video;
            }

            public List<String> getDownload() {
                return download;
            }

            public void setDownload(List<String> download) {
                this.download = download;
            }

            public List<String> getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(List<String> thumbnail) {
                this.thumbnail = thumbnail;
            }

            public List<String> getThumbnail_small() {
                return thumbnail_small;
            }

            public void setThumbnail_small(List<String> thumbnail_small) {
                this.thumbnail_small = thumbnail_small;
            }
        }

        public static class GifBean {

            /**
             * images : ["http://ww4.sinaimg.cn/large/c1e8ffd5jw1f8jynphtfbg207s0cqb29.gif","http://wimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f.gif","http://dimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f.gif"]
             * width : 280
             * gif_thumbnail : ["http://wimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_a_1.jpg"]
             * download_url : ["http://wimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_a_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f77620e849f_a_1.jpg"]
             * height : 458
             */

            private int width;
            private int height;
            private List<String> images;
            private List<String> gif_thumbnail;
            private List<String> download_url;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            public List<String> getGif_thumbnail() {
                return gif_thumbnail;
            }

            public void setGif_thumbnail(List<String> gif_thumbnail) {
                this.gif_thumbnail = gif_thumbnail;
            }

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }
        }
        public static class ImageBean {

            /**
             * medium : ["http://ww1.sinaimg.cn/bmiddle/c1e8ffd5jw1f8jn3hoiihj20go4cxgy3.jpg"]
             * big : ["http://ww1.sinaimg.cn/large/c1e8ffd5jw1f8jn3hoiihj20go4cxgy3.jpg","http://wimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48_1.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48_1.jpg"]
             * download_url : ["http://wimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48_d.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48_d.jpg","http://wimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48.jpg","http://dimg.spriteapp.cn/ugc/2016/10/07/57f7269ce2f48.jpg"]
             * height : 7061
             * width : 750
             * small : ["http://ww1.sinaimg.cn/mw240/c1e8ffd5jw1f8jn3hoiihj20go4cxgy3.jpg"]
             * thumbnail_small : ["http://wimg.spriteapp.cn/crop/150x150/ugc/2016/10/07/57f7269ce2f48.jpg","http://dimg.spriteapp.cn/crop/150x150/ugc/2016/10/07/57f7269ce2f48.jpg"]
             */

            private int height;
            private int width;
            private List<String> medium;
            private List<String> big;
            private List<String> download_url;
            private List<String> small;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getMedium() {
                return medium;
            }

            public void setMedium(List<String> medium) {
                this.medium = medium;
            }

            public List<String> getBig() {
                return big;
            }

            public void setBig(List<String> big) {
                this.big = big;
            }

            public List<String> getDownload_url() {
                return download_url;
            }

            public void setDownload_url(List<String> download_url) {
                this.download_url = download_url;
            }

            public List<String> getSmall() {
                return small;
            }

            public void setSmall(List<String> small) {
                this.small = small;
            }

        }


        public static class TopCommentsBean {
            private int voicetime;
            private int status;
            private String cmt_type;
            private int precid;
            private String content;
            private int like_count;
            /**
             * header : ["http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100","http://qzapp.qlogo.cn/qzapp/100336987/BFA030A72D6C2E8906E77EB0681392B8/100"]
             * sex : m
             * uid : 15535364
             * name : 一个神秘而诡异的地方
             */

            private UBean u;
            private int preuid;
            private String passtime;
            private String voiceuri;
            private int id;

            public int getVoicetime() {
                return voicetime;
            }

            public void setVoicetime(int voicetime) {
                this.voicetime = voicetime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCmt_type() {
                return cmt_type;
            }

            public void setCmt_type(String cmt_type) {
                this.cmt_type = cmt_type;
            }

            public int getPrecid() {
                return precid;
            }

            public void setPrecid(int precid) {
                this.precid = precid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public UBean getU() {
                return u;
            }

            public void setU(UBean u) {
                this.u = u;
            }

            public int getPreuid() {
                return preuid;
            }

            public void setPreuid(int preuid) {
                this.preuid = preuid;
            }

            public String getPasstime() {
                return passtime;
            }

            public void setPasstime(String passtime) {
                this.passtime = passtime;
            }

            public String getVoiceuri() {
                return voiceuri;
            }

            public void setVoiceuri(String voiceuri) {
                this.voiceuri = voiceuri;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class UBean {
                private String sex;
                private String uid;
                private String name;
                private List<String> header;

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getHeader() {
                    return header;
                }

                public void setHeader(List<String> header) {
                    this.header = header;
                }
            }
        }

        public static class TagsBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
