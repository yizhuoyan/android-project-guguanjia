package com.chinasofti.guguanjia.ui;


import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.UserBean;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FindPassActivity extends BaseActivity {

    private TitleView titleView;
    private int t = 60;
    private Button hdyzm;
    private Handler mHandler = new Handler();
    private EditText phoneno;
    private EditText yzm;
    private EditText newpwd;


    @Override
    public void loadData() {
        super.loadData();
    }

    //加载布局
    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_find_pass);
    }

    //加载控件
    @Override
    public void initLayoutView() {
        phoneno = (EditText) findViewById(R.id.phoneno);
        yzm = (EditText) findViewById(R.id.yzm);
        newpwd = (EditText) findViewById(R.id.newpwd);
        hdyzm = (Button) findViewById(R.id.hdyzm);
        titleView = (TitleView) findViewById(R.id.find_head);
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GGJAplication.finshActivity(FindPassActivity.this);
            }
        });
        titleView.setTitle("找回密码");
        titleView.setRightBtnText("完成");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改密码
                StringRequest request1 = new StringRequest(Request.Method.POST, UrlConfig.FDPWD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Gson gson = new Gson();
//                                UserBean userBean = gson.fromJson(s, UserBean.class);
//                                GGJAplication.userID = userBean.getId();
                                GGJAplication.startActivity(FindPassActivity.this, LoginActivity.class);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("this", volleyError.toString());
                    }
                })

                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", phoneno.getText().toString().trim());
                        map.put("newPassword", newpwd.getText().toString().trim());
                        map.put("verifCode", yzm.getText().toString().trim());
                        return map;

                    }
                };
                System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111");
                GGJAplication.getRequestQueue().add(request1);
            }
        });

        hdyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送短息
                StringRequest request1 = new StringRequest(Request.Method.POST, UrlConfig.YZM,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                Gson gson = new Gson();
                                UserBean userBean = gson.fromJson(s, UserBean.class);
//                                GGJAplication.userID = userBean.getId();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("this", volleyError.toString());
                    }
                })

                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", phoneno.getText().toString().trim());
                        return map;

                    }
                };
                GGJAplication.getRequestQueue().add(request1);

                //启动计时线程
                new Thread(new MyCountDownTimer()).start();//开始执行
            }
        });
    }


    //计数器线程类
    class MyCountDownTimer implements Runnable {

        @Override
        public void run() {


            //倒计时开始，循环
            while (t > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        hdyzm.setClickable(false);
                        hdyzm.setText(t + "秒重新开始");
                    }
                });
                try {
                    Thread.sleep(1000); //强制线程休眠1秒，就是设置倒计时的间隔时间为1秒。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t--;
            }

            //倒计时结束，也就是循环结束
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    hdyzm.setClickable(true);
                    hdyzm.setText("获得验证码");
                }
            });
            t = 60; //最后再恢复倒计时时长
        }
    }
}
