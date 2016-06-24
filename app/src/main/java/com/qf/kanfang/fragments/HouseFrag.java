package com.qf.kanfang.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qf.kanfang.FirstNewsActivity;
import com.qf.kanfang.R;
import com.qf.kanfang.WebActivity;
import com.qf.kanfang.bean.FirstPage;
import com.qf.kanfang.bean.HouseTopPageView;
import com.qf.kanfang.db.dbutils.HouseDBUtils;
import com.qf.kanfang.utils.MyUtils;
import com.qf.kanfang.view.XListView;

import java.util.Formatter;
import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class HouseFrag extends Fragment {

    private XListView xList;

    /**
     * 首页WebView内容
     * id=4,表示深圳
     */
    public static final String FIRST_PAGE_WEBVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=homepage&channel=71&cityid=4";
    /**
     * 首页 ListView内容
     * <p/>
     * 1)进入时：reqnum=10,pageflag=0,buttonmore=0;
     * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
     * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1;
     */
    public static final String FIRST_PAGE_LISTVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&pageflag=%d&act=newslist&channel=71&buttonmore=%d&cityid=%d";
    /**
     * 资讯详情
     */
    public static final String NEWS_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";

    private View topView;
    private Handler handler;
    private View view;
    private HouseDBUtils houseDBUtils;
    private SimpleCursorAdapter adapter;
    private ViewPager viewPager;
    private TextView titleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_house, null);
        xList = (XListView) view.findViewById(R.id.xList);
        houseDBUtils = new HouseDBUtils(getActivity());

        initHandler();
        initAdapter();

        xList.setPullLoadEnable(true);
        // 下拉刷新，上拉加载更多监听
        xList.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                refreshAdapter(0,4);
            }

            @Override
            public void onLoadMore() {
                refreshAdapter(1,4);
            }
        });

        // 点击事件监听
        xList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView news_id = (TextView) view.findViewById(R.id.news_id);
                Intent intent = new Intent(getActivity(), FirstNewsActivity.class);
                intent.putExtra("id", news_id.getText());

                getActivity().startActivity(intent);

            }
        });

        // 设置头布局
        topView = inflater.inflate(R.layout.frag_house_top_view, null);
        initAds();
        xList.addHeaderView(topView);

        viewPager = (ViewPager) topView.findViewById(R.id.viewPager);
        titleView = (TextView) topView.findViewById(R.id.tv_title);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            viewPager.setOnContextClickListener(new View.OnContextClickListener() {
                @Override
                public boolean onContextClick(View v) {

                    CharSequence text = titleView.getText();

                    Cursor cursor = houseDBUtils.search(3, text + "", 0, false);
                    if (cursor.moveToNext()) {
                        String id = cursor.getString(1);
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra("id", id);

                        startActivity(intent);
                    }

                    return true;
                }
            });
        }


        return view;
    }

    private void refreshAdapter(int buttonmore,int cityId) {

        String format = "" + new Formatter().format(FIRST_PAGE_LISTVIEW, 21, 0, buttonmore, cityId);

        MyUtils.load(format, new MyUtils.CallBack() {
            @Override
            public void onFinish(String json, Gson gson) {

                FirstPage firstPageData = gson.fromJson(json, FirstPage.class);
                List<FirstPage.DataBean> data = firstPageData.getData();
                String[] strings = new String[7];
                int num = 0;
                for (int i = 0; i < data.size(); i++) {
                    FirstPage.DataBean dataBean = data.get(i);

                    if (houseDBUtils.contain(dataBean.getCommentid())) {
                        continue;
                    }

                    strings[0] = dataBean.getCommentid();
                    strings[1] = dataBean.getId();
                    strings[2] = dataBean.getType();
                    strings[3] = dataBean.getTitle();
                    strings[4] = dataBean.getSummary();
                    strings[5] = dataBean.getThumbnail();
                    strings[6] = dataBean.getGroupthumbnail();
                    houseDBUtils.add(strings);
                    num++;

                }
                Message message = Message.obtain();
                message.what = 2;
                message.arg1 = num;
                handler.sendMessage(message);

            }
        });
    }

    private void initHandler() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    MyUtils.setViewPager(getActivity(), topView, (List<HouseTopPageView.DataBean>) msg.obj);
                } else if (msg.what == 2) {
                    xList.stopRefresh();
                    xList.stopLoadMore();
                    if (msg.arg1 > 0) {
                        swop();
                    }
                    Toast.makeText(getActivity(), "刷新成功，更新了数据" + msg.arg1 + "条", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private int[] id = new int[]{R.id.iv_news,R.id.tv_title,R.id.tv_content,R.id.news_id};
    private String[] from = new String[]{"thumbnail","title","summary","id"};

    public void swop() {
        Cursor cursor = houseDBUtils.search();

        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
    }

    private void initAdapter() {

        Cursor cursor = houseDBUtils.search();

        adapter = new SimpleCursorAdapter(getActivity(), R.layout.item_first, cursor, from,
                id, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {

                if (view instanceof ImageView) {

                    String url = cursor.getString(columnIndex);
                    ImageView imageView = (ImageView) view;

                    MyUtils.downImage(url,imageView);

                    return true;
                }

                return false;
            }
        });

        xList.setAdapter(adapter);

    }

    private void initAds() {

        MyUtils.load(FIRST_PAGE_WEBVIEW, new MyUtils.CallBack() {
            @Override
            public void onFinish(String json, Gson gson) {
                HouseTopPageView topPageData = gson.fromJson(json, HouseTopPageView.class);
                List<HouseTopPageView.DataBean> data = topPageData.getData();
                Message message = Message.obtain();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);
            }
        });
    }
}
