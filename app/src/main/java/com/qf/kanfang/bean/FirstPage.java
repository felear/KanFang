package com.qf.kanfang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class FirstPage {

    /**
     * id : HSZ2016061900550703
     * type : 0
     * title : 1-5月全国平均房价涨13% 有房企调高全年销售目标
     * summary : 数据显示，1-5月39家典型房企累计销售10101.61亿元，同比上升72%。2016年虽未过半，千亿房企已有三家：万科、恒大、碧桂园。
     * thumbnail : http://inews.gtimg.com/newsapp_ls/0/360321862_640330/0
     * groupthumbnail : http://inews.gtimg.com/newsapp_ls/0/360321862_150120/0
     * commentcount : 0
     * imagecount : 0
     * commentid : 1437711333
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String type;
        private String title;
        private String summary;
        private String thumbnail;
        private String groupthumbnail;
        private int commentcount;
        private int imagecount;
        private String commentid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getGroupthumbnail() {
            return groupthumbnail;
        }

        public void setGroupthumbnail(String groupthumbnail) {
            this.groupthumbnail = groupthumbnail;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public int getImagecount() {
            return imagecount;
        }

        public void setImagecount(int imagecount) {
            this.imagecount = imagecount;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }
    }
}
