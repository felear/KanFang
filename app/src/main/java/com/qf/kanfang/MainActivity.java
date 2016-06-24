package com.qf.kanfang;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.qf.kanfang.fragments.HouseFrag;
import com.qf.kanfang.utils.FileName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FragmentTransaction transaction;
    public static File house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initImageLoad();
        loadHouseFrag();
    }

    private void initImageLoad() {

        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);
        ImageLoader.getInstance().setDefaultLoadingListener(new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                File file = new File(house, new FileName().hashKeyForDisk(imageUri));

                try {
                    loadedImage.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void loadHouseFrag() {
        Fragment houseFrag = new HouseFrag();

        transaction.replace(R.id.frame_layout,houseFrag);
        transaction.commit();

    }

    private void initView() {

        house = getExternalFilesDir("house");

        if (!house.exists()) {
            house.mkdirs();
        }

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        transaction = getSupportFragmentManager().beginTransaction();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

}
