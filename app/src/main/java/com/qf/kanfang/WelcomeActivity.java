package com.qf.kanfang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.qf.kanfang.bean.FirstPage;
import com.qf.kanfang.db.dbutils.HouseDBUtils;
import com.qf.kanfang.utils.MyUtils;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private Handler handler;

    public static final String FIRST_PAGE_LISTVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&pageflag=%d&act=newslist&channel=71&buttonmore=%d&cityid=4";
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                skip();
            }
        };

        down();

    }

    private synchronized void skip() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.skip:
                flag = true;
                skip();
                break;
        }
    }

    private void down() {
        MyUtils.load(FIRST_PAGE_LISTVIEW, new MyUtils.CallBack() {
            @Override
            public void onFinish(String json, Gson gson) {

                FirstPage firstPageData = gson.fromJson(json, FirstPage.class);
                List<FirstPage.DataBean> data = firstPageData.getData();
                String[] strings = new String[7];
                HouseDBUtils houseDBUtils = new HouseDBUtils(WelcomeActivity.this);

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

                }
                if (!flag) {
                    Message message = Message.obtain();
                    handler.sendMessage(message);
                }

            }
        });
    }


}
