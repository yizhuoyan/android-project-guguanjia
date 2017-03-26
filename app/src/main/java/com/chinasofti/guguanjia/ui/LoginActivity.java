package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录activity
 */
public class LoginActivity extends BaseActivity {
    private RelativeLayout login_layout;
    private TextView findpwd_text;

    //用户名
    private EditText user_edit;
    //密码
    private EditText pw_edit;

    /**
     * 重写父类中的loadContentView加载布局文件方法
     */
    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 重写父类中的initLayoutView加载控件方法
     */
    @Override
    public void initLayoutView() {
        login_layout = (RelativeLayout) findViewById(R.id.login_layout);
        user_edit = (EditText) findViewById(R.id.user_edit);
        pw_edit = (EditText) findViewById(R.id.pw_edit);
        login_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_edit.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw_edit.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                doLogin();
//                GGJAplication.startActivity(LoginActivity.this,MainActivity.class);
            }
        });

        findpwd_text = (TextView) findViewById(R.id.findpwd_text);
        findpwd_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.startActivity(LoginActivity.this, FindPassActivity.class);
            }
        });
    }

    public void doLogin() {
        /**
         * GET请求

         StringRequest request = new StringRequest(Request.Method.GET, UrlConfig.TEST_URL,
         new Response.Listener<String>() {
        @Override public void onResponse(String s) {
        Gson gson = new Gson();
        TestBean testBean = gson.fromJson(s, TestBean.class);

        Toast.makeText(LoginActivity.this, testBean.toString(), Toast.LENGTH_SHORT).show();
        }
        }, new Response.ErrorListener() {
        @Override public void onErrorResponse(VolleyError volleyError) {
        Log.d("this", volleyError.toString());
        Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
        }
        });
         GGJAplication.getRequestQueue().add(request);
         */

        /**
         * POST请求
         */
        StringRequest request1 = new StringRequest(Request.Method.POST, UrlConfig.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Gson gson = new Gson();
                        UserBean userBean = gson.fromJson(s, UserBean.class);

                        GGJAplication.userID = userBean.getData().getId();
                        GGJAplication.userBean=userBean;
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("userBean",userBean);
                        GGJAplication.startActivity(LoginActivity.this,intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("phone", user_edit.getText().toString().trim());
                map.put("password", pw_edit.getText().toString().trim());
                return map;

            }
        };
        GGJAplication.getRequestQueue().add(request1);

    }
}
