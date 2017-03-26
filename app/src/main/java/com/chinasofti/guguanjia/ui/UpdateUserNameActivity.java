package com.chinasofti.guguanjia.ui;

import android.view.View;
import android.widget.EditText;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.views.TitleView;

public class UpdateUserNameActivity extends BaseActivity {
    private TitleView titleView;
    private EditText new_user_name;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_update_user_name);

    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.updatUserNameTitleView);
        titleView.setTitle("修改姓名");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateUserNameActivity.this);
            }
        });
        titleView.setRightBtnText("完成");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(UpdateUserNameActivity.this);
            }
        });

        new_user_name=(EditText) findViewById(R.id.new_user_name);
    }

    @Override
    public void loadData() {
        super.loadData();
    }


}
