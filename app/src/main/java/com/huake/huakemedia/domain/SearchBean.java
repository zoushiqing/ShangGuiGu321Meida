package com.huake.huakemedia.domain;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/6 19:44
 * @描述	      
 */

import java.util.List;

public class SearchBean {

    /**
     * flag : ok
     * pageNo : 1
     * pageSize : 20
     * wd : 你好
     * total : 302
     * items : [{"itemID":"ARTIYdU8dWK487Koe1Awiiem161003","itemTitle":"【谁不说俺家乡好】央视记者海采：奋斗换来好日子！","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIYdU8dWK487Koe1Awiiem161003&isfromapp=1","pubTime":"2016-10-03 09:49:13","keywords":"谁不说俺家乡好 央视 记者 海采 奋 好日子","category":"社会","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1383126846675770","datecheck":"2016-10-03","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/10/03/2016100309403376413.jpg"}},{"itemID":"ARTIdM90x4GcSXHZxs7sKGaC161003","itemTitle":"早啊！新闻来了〔2016.10.03〕","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIdM90x4GcSXHZxs7sKGaC161003&isfromapp=1","pubTime":"2016-10-03 06:53:04","keywords":"早啊！新闻来了〔2016.10.03〕","category":"观点","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939543757540","datecheck":"2016-10-03","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/10/03/2016100306391928477.png"}},{"itemID":"ARTIBdblTk4InxGt16KpuKqV161001","itemTitle":"我国首次启用智能机器人辅助海关查验","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIBdblTk4InxGt16KpuKqV161001&isfromapp=1","pubTime":"2016-10-01 14:38:30","keywords":"机器人","category":"社会","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1383126846675770","datecheck":"2016-10-01","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/10/01/2016100113464827817.jpg"}},{"itemID":"ARTIG1FlfghPStMUtFrDWUCb161001","itemTitle":"【谁不说俺家乡好】央视国庆海采：家乡最让我骄傲的是\u2026\u2026","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIG1FlfghPStMUtFrDWUCb161001&isfromapp=1","pubTime":"2016-10-01 10:49:56","keywords":"【央视国庆海采：谁不说俺家乡好】","category":"社会","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1383126846675770","datecheck":"2016-10-01","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/10/01/2016100110474031862.jpg"}},{"itemID":"ARTIgqXLmKGDilMN0uCF5yhi160926","itemTitle":"你好！火箭军","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIgqXLmKGDilMN0uCF5yhi160926&isfromapp=1","pubTime":"2016-09-26 23:29:59","keywords":"你好！火箭军","category":"不进时间链","guid":"","videoLength":"","source":"军报记者","brief":"","photoCount":"0","sub_column_id":"PAGE1401241938094224","datecheck":"2016-09-26","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/26/2016092623201961213.jpg"}},{"itemID":"ARTIfwRIsglpuz2b4ARaBYDG160926","itemTitle":"习近平：努力建设一支强大的现代化火箭军","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIfwRIsglpuz2b4ARaBYDG160926&isfromapp=1","pubTime":"2016-09-26 18:48:35","keywords":"习近平 现代化 火箭军","category":"时政","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1414660783241615","datecheck":"2016-09-26","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/26/2016092617555754514.jpg"}},{"itemID":"ARTIBW0NkUgpjQrNNrU6geN2160922","itemTitle":"独家调查还原徐玉玉案骗局：黑客出卖考生信息 骗子按剧本分工作案","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIBW0NkUgpjQrNNrU6geN2160922&isfromapp=1","pubTime":"2016-09-22 13:44:29","keywords":"徐玉玉案8名嫌犯归案 还原骗局细节","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-09-22","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/22/2016092213254259890.jpg"}},{"itemID":"ARTIjg7vdvW3J1fsYdLXvk6B160913","itemTitle":"【夜读】最亲近的人却也是别人，别再让他们转身流泪","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIjg7vdvW3J1fsYdLXvk6B160913&isfromapp=1","pubTime":"2016-09-13 22:41:19","keywords":"亲近 却也是别人，别再让他们转身流泪","category":"夜读","guid":"","videoLength":"","source":"央视新闻","brief":"","photoCount":"0","sub_column_id":"PAGEgZkqnyVTgChfECKoj1I6160222","datecheck":"2016-09-13","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/14/2016091401155774937.jpg"}},{"itemID":"ARTI2nuwhyAea1DjJIw7Apmr160913","itemTitle":"云南：西南最大规模大桥转体今将进行","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTI2nuwhyAea1DjJIw7Apmr160913&isfromapp=1","pubTime":"2016-09-13 11:59:12","keywords":"云南 西南 大桥转体","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-09-13","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/13/2016091311554051527.jpg"}},{"itemID":"ARTILLOQRFEtPgsMTGZ1W4G8160912","itemTitle":"【组图】各族穆斯林群众欢度古尔邦节","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTILLOQRFEtPgsMTGZ1W4G8160912&isfromapp=1","pubTime":"2016-09-12 17:34:54","keywords":"古尔邦节 穆斯林群众","category":"最新","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939846580285","datecheck":"2016-09-12","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/09/12/2016091217292828470.jpg"}},{"itemID":"ARTIr68kkGeu4vgR2HZqpdb0160828","itemTitle":"【意大利中部6.1级地震】再见了！勇敢的小姑娘朱莉娅","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIr68kkGeu4vgR2HZqpdb0160828&isfromapp=1","pubTime":"2016-08-28 14:46:54","keywords":"意大利地震 小朱莉娅 意大利 国葬 焦尔吉亚","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-08-28","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/08/28/2016082814271034845.jpg"}},{"itemID":"ARTItEb4Dbi6r0LiN8H6mD6R160820","itemTitle":"夜读：求仁得仁，慨当以慷","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTItEb4Dbi6r0LiN8H6mD6R160820&isfromapp=1","pubTime":"2016-08-20 23:37:24","keywords":"夜读 求仁得仁 慨当以慷","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-08-20","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/08/20/2016082023591656898.jpg"}},{"itemID":"ARTII0oQv33pl7E2AihD4oz8160814","itemTitle":"夜读：那段镌刻在心灵深处的记忆，从未褪去","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTII0oQv33pl7E2AihD4oz8160814&isfromapp=1","pubTime":"2016-08-14 23:40:50","keywords":"夜读 心灵 记忆","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-08-14","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/08/14/2016081423281469578.jpg"}},{"itemID":"ARTI1Y3ERGrDlJQY3Z0ItA2N160813","itemTitle":"聚焦俄罗斯\u201c国际军事比赛-2016\u201d 外国人眼中的中国军人","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTI1Y3ERGrDlJQY3Z0ItA2N160813&isfromapp=1","pubTime":"2016-08-13 18:33:28","keywords":"聚焦俄罗斯\u201c国际军事比赛-2016\u201d 外国人眼中的中国军人","category":"最新","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939846580285","datecheck":"2016-08-13","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/08/13/2016081317534270899.png"}},{"itemID":"ARTIlk0tcVzNkfKyQxK83jTP160813","itemTitle":"菲尔普斯答央视记者问：我确实很喜欢拔罐 它对我很有效","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIlk0tcVzNkfKyQxK83jTP160813&isfromapp=1","pubTime":"2016-08-13 15:39:41","keywords":"菲尔普斯告诉央视记者：为何自己喜欢拔火罐","category":"最新","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939846580285","datecheck":"2016-08-13","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/08/13/2016081315392484824.jpg"}},{"itemID":"ARTIaQ6ryHEdfKZpegmkMXWl160729","itemTitle":"两车被救援8公里花费12.87万元 天价拖车费如何产生？","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIaQ6ryHEdfKZpegmkMXWl160729&isfromapp=1","pubTime":"2016-07-29 09:19:07","keywords":"两车被救援8公里花费12.87万元 天价拖车费从何而来？","category":"社会","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1383126846675770","datecheck":"2016-07-29","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/07/29/2016072909094021971.jpg"}},{"itemID":"ARTIdNrHyMTPBO9DSYXnqvMV160704","itemTitle":"【夜读】你好，新疆","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIdNrHyMTPBO9DSYXnqvMV160704&isfromapp=1","pubTime":"2016-07-04 22:59:41","keywords":"夜读 你好 新疆","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-07-04","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/07/05/2016070500035274797.jpg"}},{"itemID":"ARTI3bBvRINQGS1fR70rtuE1160628","itemTitle":"快递又出新模式：顺路快递PK传统快递 哪个更靠谱？","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTI3bBvRINQGS1fR70rtuE1160628&isfromapp=1","pubTime":"2016-06-28 10:03:34","keywords":"快递 新模式 顺路快递 传统快递 靠谱","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-06-28","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/06/28/2016062809482844376.jpg"}},{"itemID":"ARTIV8Zy9LTvMB1p4CyesIJ0160622","itemTitle":"评论丨你好啊，两千多年的老朋友！","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIV8Zy9LTvMB1p4CyesIJ0160622&isfromapp=1","pubTime":"2016-06-22 21:48:20","keywords":"评论丨你好啊，两千多年的老朋友！","category":"时政","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1414660783241615","datecheck":"2016-06-22","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/06/22/2016062222560914053.jpg"}},{"itemID":"ARTIeytLJiyBD1YnQYyfQO6p160614","itemTitle":"【美国奥兰多枪击案】枪手父亲向受害者家属道歉","itemType":"article_flag","detailUrl":"http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIeytLJiyBD1YnQYyfQO6p160614&isfromapp=1","pubTime":"2016-06-14 06:44:43","keywords":"【美国奥兰多枪击案】枪手父亲向受害者家属道歉","category":"要闻","guid":"","videoLength":"","source":"央视新闻客户端","brief":"","photoCount":"0","sub_column_id":"PAGE1373939356250140","datecheck":"2016-06-14","itemImage":{"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/06/14/2016061406443181897.jpg"}}]
     */

    private String flag;
    private String pageNo;
    private String pageSize;
    private String wd;
    private String total;
    /**
     * itemID : ARTIYdU8dWK487Koe1Awiiem161003
     * itemTitle : 【谁不说俺家乡好】央视记者海采：奋斗换来好日子！
     * itemType : article_flag
     * detailUrl : http://app.cntv.cn/special/news/detail/arti/index.html?id=ARTIYdU8dWK487Koe1Awiiem161003&isfromapp=1
     * pubTime : 2016-10-03 09:49:13
     * keywords : 谁不说俺家乡好 央视 记者 海采 奋 好日子
     * category : 社会
     * guid :
     * videoLength :
     * source : 央视新闻客户端
     * brief :
     * photoCount : 0
     * sub_column_id : PAGE1383126846675770
     * datecheck : 2016-10-03
     * itemImage : {"imgUrl1":"http://p1.img.cctvpic.com/photoworkspace/2016/10/03/2016100309403376413.jpg"}
     */

    private List<ItemsBean> items;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        private String itemID;
        private String itemTitle;
        private String itemType;
        private String detailUrl;
        private String pubTime;
        private String keywords;
        private String category;
        private String guid;
        private String videoLength;
        private String source;
        private String brief;
        private String photoCount;
        private String sub_column_id;
        private String datecheck;
        /**
         * imgUrl1 : http://p1.img.cctvpic.com/photoworkspace/2016/10/03/2016100309403376413.jpg
         */

        private ItemImageBean itemImage;

        public String getItemID() {
            return itemID;
        }

        public void setItemID(String itemID) {
            this.itemID = itemID;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(String videoLength) {
            this.videoLength = videoLength;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getPhotoCount() {
            return photoCount;
        }

        public void setPhotoCount(String photoCount) {
            this.photoCount = photoCount;
        }

        public String getSub_column_id() {
            return sub_column_id;
        }

        public void setSub_column_id(String sub_column_id) {
            this.sub_column_id = sub_column_id;
        }

        public String getDatecheck() {
            return datecheck;
        }

        public void setDatecheck(String datecheck) {
            this.datecheck = datecheck;
        }

        public ItemImageBean getItemImage() {
            return itemImage;
        }

        public void setItemImage(ItemImageBean itemImage) {
            this.itemImage = itemImage;
        }

        @Override
        public String toString() {
            return "ItemsBean{" +
                    "itemID='" + itemID + '\'' +
                    ", itemTitle='" + itemTitle + '\'' +
                    ", itemType='" + itemType + '\'' +
                    ", detailUrl='" + detailUrl + '\'' +
                    ", pubTime='" + pubTime + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", category='" + category + '\'' +
                    ", guid='" + guid + '\'' +
                    ", videoLength='" + videoLength + '\'' +
                    ", source='" + source + '\'' +
                    ", brief='" + brief + '\'' +
                    ", photoCount='" + photoCount + '\'' +
                    ", sub_column_id='" + sub_column_id + '\'' +
                    ", datecheck='" + datecheck + '\'' +
                    ", itemImage=" + itemImage +
                    '}';
        }

        public static class ItemImageBean {
            private String imgUrl1;

            public String getImgUrl1() {
                return imgUrl1;
            }

            public void setImgUrl1(String imgUrl1) {
                this.imgUrl1 = imgUrl1;
            }
        }
    }
}
