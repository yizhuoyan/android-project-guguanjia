package com.chinasofti.guguanjia.ui;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

import static com.chinasofti.guguanjia.R.id.service_find_head;

public class Service_center extends BaseActivity{
    private TitleView topTitleView;
    private RelativeLayout about_layout;

    private RelativeLayout jcgx;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_service_center);
    }

    @Override
    public void initLayoutView() {
        jcgx = (RelativeLayout)findViewById(R.id.jcgx);
        jcgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Service_center.this, "已经是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
        topTitleView=(TitleView)findViewById(service_find_head);
        topTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(Service_center.this);
            }
        });
        topTitleView.setTitle("服务中心");
        topTitleView.setRightBtnVisible(View.GONE);

        about_layout=(RelativeLayout) findViewById(R.id.about_layout);
        about_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.startActivity(Service_center.this,AboutMeActivity.class);
            }
        });

    }

    @Override
    public void loadData() {
        super.loadData();
    }
}
