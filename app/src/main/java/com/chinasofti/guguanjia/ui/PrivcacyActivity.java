package com.chinasofti.guguanjia.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

import static com.chinasofti.guguanjia.R.id.aboutme_head;

public class PrivcacyActivity extends BaseActivity {

    private TitleView topTitleView;

    @Override
    public void initLayoutView() {


        topTitleView=(TitleView)findViewById(R.id.privacyAC);
        topTitleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(PrivcacyActivity.this);
            }
        });
        topTitleView.setTitle("隐私协议");
        topTitleView.setRightBtnVisible(View.GONE);
    }

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_privcacy);
    }
}
