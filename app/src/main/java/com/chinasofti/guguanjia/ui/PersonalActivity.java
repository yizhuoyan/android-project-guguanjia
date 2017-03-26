package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.UserBean;
import com.chinasofti.guguanjia.utils.ImageDownloader;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;

public class PersonalActivity extends BaseActivity {
    private TitleView titleView;
    private RelativeLayout update_header_img,update_username,update_password;
    private UserBean userBean;
    private ImageView headerView;
    private TextView personal_telphone,personal_user_name,personal_user_company,personal_phone,personal_address;

    @Override
    public void loadContentView() {
        Intent intent = getIntent();
        if (intent!=null)
            userBean = (UserBean) intent.getSerializableExtra("userBean");

        setContentView(R.layout.activity_personal);
    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.personalTitleView);
        titleView.setTitle("个人信息");
        titleView.setRightBtnVisible(View.GONE);
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(PersonalActivity.this);
            }
        });
        titleView.setRightBtnText("切换账号");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.startActivity(PersonalActivity.this,LoginActivity.class);
            }
        });
        headerView=(ImageView) findViewById(R.id.headerView);
        update_header_img=(RelativeLayout) findViewById(R.id.update_header_img);
        update_header_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalActivity.this,UpdateHeaderImgActivity.class);
                intent.putExtra("userBean",userBean);
                GGJAplication.startActivity(PersonalActivity.this,intent);
            }
        });
        update_username=(RelativeLayout) findViewById(R.id.update_username);
        update_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.startActivity(PersonalActivity.this,UpdateUserNameActivity.class);
            }
        });
        update_password=(RelativeLayout) findViewById(R.id.update_password);
        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.startActivity(PersonalActivity.this,UpdateUserPwActivity.class);
            }
        });

        personal_telphone=(TextView) findViewById(R.id.personal_telphone);
        personal_user_name=(TextView) findViewById(R.id.personal_user_name);
        personal_user_company=(TextView) findViewById(R.id.personal_user_company);
        personal_phone=(TextView) findViewById(R.id.personal_phone);
        personal_address=(TextView) findViewById(R.id.personal_address);



    }

    @Override
    public void loadData() {

        //加载头像
        ImageDownloader imageDownloader = new ImageDownloader(PersonalActivity.this);
        imageDownloader.download(UrlConfig.BASEURL+"/static/uploads/heads/1470929079373.jpg", headerView, ImageView.ScaleType.FIT_CENTER);
        //加载姓名
        personal_telphone.setText(userBean.getData().getPhone());
        personal_user_name.setText(userBean.getData().getName());
        personal_user_company.setText(userBean.getData().getCompanyName());


    }
}
