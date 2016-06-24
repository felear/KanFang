package com.qf.kanfang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qf.kanfang.bean.FirstPageNews;
import com.qf.kanfang.utils.MyUtils;

import java.util.Formatter;
import java.util.List;

public class FirstNewsActivity extends AppCompatActivity {

    /**
     * 资讯详情
     */
    public static final String NEWS_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";
    private TextView titleView;
    private LinearLayout contentView;
    private TextView infoView;
    private Handler handler;
    private LinearLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fist_news);
        getSupportActionBar().hide();

        titleView = (TextView) findViewById(R.id.title);
        contentView = (LinearLayout) findViewById(R.id.content);
        infoView = (TextView) findViewById(R.id.news_info);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        final String url = "" + new Formatter().format(NEWS_DETAIL, id);

        params = new LinearLayout.LayoutParams(-2, -2);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    FirstPageNews data = (FirstPageNews) msg.obj;
                    titleView.setText(data.getTitle());
                    infoView.setText(data.getSource() + " " + data.getTime());
                    List<FirstPageNews.ContentBean> content = data.getContent();
                    String sb = null;
                    for (int i = 0; i < content.size(); i++) {
                        String value = content.get(i).getValue();
                        if (value.startsWith("http://")) {
                            ImageView imageView = new ImageView(FirstNewsActivity.this);
                            MyUtils.downImage(value,imageView);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            imageView.setLayoutParams(params);
                            contentView.addView(imageView);

                        } else {

                            sb = "\n       " + value;
                            TextView textView = new TextView(FirstNewsActivity.this);
                            textView.setText(sb);
                            textView.setTextSize(20);
                            textView.setLayoutParams(params);
                            contentView.addView(textView);

                        }
                    }
//                    contentView.setText(sb.toString());
                }
            }
        };

        MyUtils.load(url, new MyUtils.CallBack() {
            @Override
            public void onFinish(String json, Gson gson) {
                FirstPageNews data = gson.fromJson(json, FirstPageNews.class);
                Message message = Message.obtain();
                message.what = 1;
                message.obj = data;
                handler.sendMessage(message);
            }
        });
    }
}
