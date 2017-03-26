package com.chinasofti.guguanjia.ui;

import android.view.View;
import android.widget.EditText;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

public class UpdateUserPwActivity extends BaseActivity {
    private TitleView titleView;
    private EditText new_user_pw;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_update_user_pw);

    }
    @Override
    public void initLayoutView() {

        titleView=(TitleView) findViewById(R.id.updatUserPwTitleView);
        titleView.setTitle("修改密码");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateUserPwActivity.this);
            }
        });
        titleView.setRightBtnText("完成");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateUserPwActivity.this);
            }
        });

        new_user_pw=(EditText) findViewById(R.id.new_user_pw);
    }

    @Override
    public void loadData() {
        super.loadData();
    }


}
