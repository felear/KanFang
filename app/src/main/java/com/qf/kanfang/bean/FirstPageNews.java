package com.qf.kanfang.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class FirstPageNews {

    /**
     * retcode : 0
     * retmsg : 成功
     * id : HSZ2016061801154503
     * title : 华润：万科重组预案并不算通过 将解释为何反对
     * source : 南方都市报
     * time : 2016-06-18 07:31:02
     * url : http://xw.qq.com/house/20160618011545/HSZ2016061801154503
     * surl : http://xw.qq.com/house/20160618011545/HSZ2016061801154503
     * content : [{"type":1,"value":"昨晚万科公告称，万科董事会通过增发股份引入深圳地铁重组预案。预案显示，万科此次发行股价拟为15 .8 8元/股，即万科A股停牌前6 0个交易日的93 .6%.\n\n 公告当中表示，6月17日下午，万科召开董事会审议发行股份购买资产的预案，11名董事中张利平董事认为自身存在潜在的关联与利益冲突，申请不对所有相关议案行使表决权，因此相关议案由无关联关系的10名董事进行表决。此次董事会最终以7票同意、3票反对、1票回避表决通过重组预案。据相关媒体透露，万科董事会中，来自华润的3名董事\u2014\u2014\u2014乔世波、魏斌、陈鹰，投下了反对票。\n\n 万科方面强调，经过无关联关系的10位董事投票，7位董事赞成，3名华润董事表示反对，最终董事会以超过2/3的票数通过此次预案。"},{"type":1,"value":"不过，针对万科召开董事会审议发行股份购买资产的预案一事，华润集团相关部门负责人昨晚在接受南都记者采访时，坚持认为该方案并没有通过，因为按照万科A公司章程相关规定，需要8张赞成票才行。\n\n 这名负责人还对\u201c方案获得通过\u201d的说法表示愤怒，称正在草拟律师函将发给万科方面。等万科发布正式公告后，还将解释华润集团派出的董事为什么会投反对票。\n\n 华润方面表示，按照《公司法》相关规定，股东会对公司增资作出决议，必须经代表2/3以上表决权的股东通过。此外，这一重组方案后续还需过两关：一是万科预计在8月召开第二次董事会审议正式的重组方案；二是最快于9月底召开临时股东大会，最终投票决定是否同意实施重组。"},{"type":1,"value":"万科投票方案是否在法律层面可以通过？昨晚接受南都记者的采访多名律师均表示，万科董事会投票方案存在争议。\n\n 法制盛邦合伙人律师陈亮表示，\u201c根据《公司法》第112条规定，董事会作出决议，必须经过全体董事的过半数通过。不过，这一规定适用于公司章程无特殊规定的情况，如果公司章程对董事会表决有特殊规定，则以公司章程为准。\u201d\n\n 这也就意味着，如果按照法律规定，11个股东只要达到6票赞成便可通过。但如果万科公司章程有特别规定。假设规定需要董事会2/3以上的董事表决同意，则需要8位董事通过才可。那华润所指，重组方案不算通过即为正确的说法。换言之，万科投票方案不能算作无效，但重组计划是否已经通过了还要依据公司章程具体规定。\n\n 同时，陈亮表示，董事会是把增资扩股议题分成两次表决，第一次表决管理层单方拟定的预案，如果预案通过，则根据董事会决议作出相应修改作为正式方案，进行第二次董事会表决，通过则进入股东会表决。\n\n 广东保典律师事务所窦雍岗律师表示，董事会表决还要注意关联企业，《公司法》当中规定，上市公司董事与董事会会议决议事项所涉及的企业有关联关系的，不得对该项决议行使表决权，也不得代理其他董事行使表决权。\n\n 此外，《公司法》第105条规定，\u201c本法和公司章程规定公司转让、受让重大资产或者对外提供担保等事项必须经股东大会作出决议的，董事会应当及时召集股东大会会议，由股东大会就上述事项进行表决。\u201d\n\n 换言之，万科决议是否已经通过尚存争议。"}]
     */

    private String id;
    private String title;
    private String source;
    private String time;
    private String url;
    private String surl;
    /**
     * type : 1
     * value : 昨晚万科公告称，万科董事会通过增发股份引入深圳地铁重组预案。预案显示，万科此次发行股价拟为15 .8 8元/股，即万科A股停牌前6 0个交易日的93 .6%.

     公告当中表示，6月17日下午，万科召开董事会审议发行股份购买资产的预案，11名董事中张利平董事认为自身存在潜在的关联与利益冲突，申请不对所有相关议案行使表决权，因此相关议案由无关联关系的10名董事进行表决。此次董事会最终以7票同意、3票反对、1票回避表决通过重组预案。据相关媒体透露，万科董事会中，来自华润的3名董事———乔世波、魏斌、陈鹰，投下了反对票。

     万科方面强调，经过无关联关系的10位董事投票，7位董事赞成，3名华润董事表示反对，最终董事会以超过2/3的票数通过此次预案。
     */

    private List<ContentBean> content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
