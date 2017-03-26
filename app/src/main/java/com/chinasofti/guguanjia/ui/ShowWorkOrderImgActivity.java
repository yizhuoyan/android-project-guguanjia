package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

public class ShowWorkOrderImgActivity extends BaseActivity {
    private TitleView titleView;
    private ImageView work_order_erweima;
    private Bitmap erweimaBitmap;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_show_work_order_img);
        Intent intent = getIntent();
        if (intent != null)
            erweimaBitmap=intent.getParcelableExtra("erweimaBitmap");
    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.erweimaTitleView);
        titleView.setTitle("二维码扫描");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(ShowWorkOrderImgActivity.this);
            }
        });
        titleView.setRightBtnVisible(View.GONE);

        work_order_erweima=(ImageView) findViewById(R.id.work_order_erweima);
        if(erweimaBitmap!=null)
            work_order_erweima.setImageBitmap(erweimaBitmap);
    }
}
