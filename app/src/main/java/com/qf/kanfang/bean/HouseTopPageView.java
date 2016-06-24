package com.qf.kanfang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class HouseTopPageView {

    /**
     * retcode : 0
     * retmsg : 成功
     * data : [{"type":"1","picurl":"http://p.qpic.cn/estate/0/23faa39d5e519ac0ea7f016c900b85be.jpg/0","title":"深将投5360亿建交通项目","newsurl":"http://openapi.inews.qq.com/c/sz_house/20160616007987?refer=kanfangtuan&filter=1"},{"type":"1","picurl":"http://p.qpic.cn/estate/0/3cd8e35b14bc2d1c3fff93ae98537676.jpg/0","title":"深300万以下新房占比较低","newsurl":"http://openapi.inews.qq.com/c/sz_house/20160616009988?refer=kanfangtuan&filter=1"},{"type":"1","picurl":"http://p.qpic.cn/estate/0/ec98ed8fb449e5550db103ffcb4fb8b3.jpg/0","title":"地王后龙华新房最高涨17%","newsurl":"http://openapi.inews.qq.com/c/sz_house/20160615054514?refer=kanfangtuan&filter=1"}]
     * ehouse_timestamp : 0
     */

    private String retmsg;
    /**
     * type : 1
     * picurl : http://p.qpic.cn/estate/0/23faa39d5e519ac0ea7f016c900b85be.jpg/0
     * title : 深将投5360亿建交通项目
     * newsurl : http://openapi.inews.qq.com/c/sz_house/20160616007987?refer=kanfangtuan&filter=1
     */

    private List<DataBean> data;

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        private String picurl;
        private String title;
        private String newsurl;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNewsurl() {
            return newsurl;
        }

        public void setNewsurl(String newsurl) {
            this.newsurl = newsurl;
        }
    }
}
