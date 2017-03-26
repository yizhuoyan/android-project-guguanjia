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


/**
 * 首页activity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    //中间部分按钮
    private RelativeLayout my_worknums_btn, online_worknums_btn, push_worknums_btn;
    //底部按钮
    private RelativeLayout taizhang_btn, saomiao_btn, service_center_btn;

    private ImageView user_head_img;
    private TextView user_name, chan, yun, chu, user_company;

    private final static int SCANNIN_GREQUEST_CODE = 1;

    public UserBean userBean;

    @Override
    public void loadContentView() {
        //System.out.println(userBean.toString());
        Intent intent = getIntent();
        if (intent!=null)
        userBean = (UserBean) intent.getSerializableExtra("userBean");

        setContentView(R.layout.activity_main);
    }

    @Override
    public void initLayoutView() {
        //头像
        user_head_img = (ImageView) findViewById(R.id.user_head_img);
        user_head_img.setOnClickListener(this);
        //姓名
        user_name = (TextView) findViewById(R.id.user_name);
        //
        user_company = (TextView) findViewById(R.id.user_company);
        chan = (TextView)findViewById(R.id.chan);
        chu = (TextView)findViewById(R.id.chu);
        yun = (TextView)findViewById(R.id.yun);


        my_worknums_btn = (RelativeLayout) findViewById(R.id.my_worknums_btn);
        my_worknums_btn.setOnClickListener(this);
        online_worknums_btn = (RelativeLayout) findViewById(R.id.online_worknums_btn);
        online_worknums_btn.setOnClickListener(this);
        push_worknums_btn = (RelativeLayout) findViewById(R.id.push_worknums_btn);
        push_worknums_btn.setOnClickListener(this);

        taizhang_btn = (RelativeLayout) findViewById(R.id.taizhang_btn);
        taizhang_btn.setOnClickListener(this);
        saomiao_btn = (RelativeLayout) findViewById(R.id.saomiao_btn);
        saomiao_btn.setOnClickListener(this);
        service_center_btn = (RelativeLayout) findViewById(R.id.service_center_btn);
        service_center_btn.setOnClickListener(this);


    }

    @Override
    public void loadData() {
        //加载头像
        ImageDownloader imageDownloader = new ImageDownloader(MainActivity.this);
        imageDownloader.download(UrlConfig.BASEURL+"/static/uploads/heads/1470929079373.jpg", user_head_img, ImageView.ScaleType.FIT_CENTER);
        //加载姓名
        user_name.setText(userBean.getData().getName());
        user_company.setText(userBean.getData().getCompanyName());
        if (userBean.getData().getIsOutput()) {
            chan.setTextColor(getResources().getColor(R.color.greenColor));
        }
        if (userBean.getData().getIsTransport()) {
            yun.setTextColor(getResources().getColor(R.color.greenColor));
        }
        if (userBean.getData().getIsDispose()) {
            chu.setTextColor(getResources().getColor(R.color.greenColor));
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_head_img:
                Intent intent = new Intent(MainActivity.this,PersonalActivity.class);
                intent.putExtra("userBean",userBean);
                GGJAplication.startActivity(MainActivity.this,intent);
                break;
            case R.id.my_worknums_btn://我的工单
                GGJAplication.startActivity(MainActivity.this, MyElecLedgerActivity.class);
                break;
            case R.id.online_worknums_btn://在途工单
                GGJAplication.startActivity(MainActivity.this, OnRoadElecCledgerActivity.class);
                break;
            case R.id.push_worknums_btn://发起工单
                GGJAplication.startActivity(MainActivity.this, LaunchElecledgerActivity.class);
                break;
            case R.id.taizhang_btn://电子台账
                GGJAplication.startActivity(MainActivity.this, AllElecLedgerActivity.class);
                break;
            case R.id.saomiao_btn://扫描
                Intent mIntent = new Intent();
                mIntent.setClass(MainActivity.this, MipcaCaptureActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(mIntent, SCANNIN_GREQUEST_CODE);
                break;
            case R.id.service_center_btn://服务中心
                GGJAplication.startActivity(MainActivity.this, Service_center.class);
                break;
        }
    }
}
