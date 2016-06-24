package com.qf.kanfang.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qf.kanfang.MainActivity;
import com.qf.kanfang.R;
import com.qf.kanfang.WebActivity;
import com.qf.kanfang.adapter.MyAdapter;
import com.qf.kanfang.bean.HouseTopPageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MyUtils {

    private static ViewPager viewPager;
    private static LinearLayout dotLayout;
    private static TextView title;
    private static boolean flag;
    private static int viewPage;
    private static FileName fileName = new FileName();
    ;

    public interface CallBack {
        void onFinish(String json, Gson gson);
    }

    public static void load(String url, final CallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                if (callBack != null) {
                    callBack.onFinish(json, gson);
                }

            }
        });
    }

    // 设置头部滚动ViewPager
    public static void setViewPager(final Context context, View view, final List<HouseTopPageView.DataBean> data) {

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        dotLayout = (LinearLayout) view.findViewById(R.id.dot_layout);
        title = (TextView) view.findViewById(R.id.title);

        ArrayList<ImageView> imageViews = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        params.setMargins(0, 0, 10, 0);

        for (int i = 0; i < data.size(); i++) {

            HouseTopPageView.DataBean dataBean = data.get(i);
            // 创建小圆点控件
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(params);

            // 添加小圆点
            if (i == 0) {
                // 设置默认标题
                title.setText(dataBean.getTitle());
                imageView.setImageResource(R.mipmap.img_dot_white);
            } else
                imageView.setImageResource(R.mipmap.img_dot_gray);
            dotLayout.addView(imageView);

            // 设置背景图片
            ImageView imageView1 = new ImageView(context);
            imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String url = dataBean.getPicurl();
            downImage(url, imageView1);

            final String newsurl = dataBean.getNewsurl();
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", newsurl);
                    context.startActivity(intent);
                }

//                }
            });

            imageViews.add(imageView1);
        }

        MyAdapter myAdapter = new MyAdapter(imageViews);
        viewPager.setAdapter(myAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                // 初始化页面数
                viewPage = position;
                HouseTopPageView.DataBean dataBean = data.get(position);
                // 设置标题
                title.setText(dataBean.getTitle());
                for (int i = 0; i < data.size(); i++) {
                    // 创建小圆点控件
                    ImageView dotView = (ImageView) dotLayout.getChildAt(i);
                    // 设置小圆点
                    if (i == position) {
                        dotView.setImageResource(R.mipmap.img_dot_white);
                    } else
                        dotView.setImageResource(R.mipmap.img_dot_gray);
                }
            }
        });

        if (!flag) {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem((viewPage + 1) % 3);
                    handler.postDelayed(this, 5000);
                }
            }, 5000);
            flag = true;
        }
    }

    public static void downImage(String url, ImageView imageView) {
        File house = MainActivity.house;
        File file = new File(house, fileName.hashKeyForDisk(url));
        // 判断图片是否已存在
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else
            ImageLoader.getInstance().displayImage(url, imageView);
    }

}
