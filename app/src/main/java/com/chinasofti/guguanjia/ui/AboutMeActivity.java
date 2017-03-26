package com.chinasofti.guguanjia.ui;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

import static com.chinasofti.guguanjia.R.id.aboutme_head;

public class AboutMeActivity extends BaseActivity {
    private TitleView topTitleView;
    private RelativeLayout about_layout;
    private TextView tiaokuan;
    private TextView ysxy;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_about_me);
    }

    @Override
    public void initLayoutView() {
        ysxy = (TextView)findViewById(R.id.ysxy);
        ysxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GGJAplication.startActivity(AboutMeActivity.this,PrivcacyActivity.class);
            }
        });
        tiaokuan = (TextView)findViewById(R.id.tiaokuan);
        tiaokuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GGJAplication.startActivity(AboutMeActivity.this,TermsOfServiceActivity.class);
            }
        });

        topTitleView=(TitleView)findViewById(aboutme_head);
        topTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(AboutMeActivity.this);
            }
        });
        topTitleView.setTitle("关于我们");
        topTitleView.setRightBtnVisible(View.GONE);


    }

    @Override
    public void loadData() {
        super.loadData();
    }
}
