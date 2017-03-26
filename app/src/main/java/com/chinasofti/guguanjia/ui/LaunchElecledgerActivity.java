package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.adapter.DetailListAdapter;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.BaseBean;
import com.chinasofti.guguanjia.bean.Detail;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaunchElecledgerActivity extends BaseActivity {
    private TitleView titleView;
    private TextView detailedlist_num;
    private TextView add_company,add_name;
    private ListView launchElecListView;
    private ImageView add_luanch_ele_img;
    private DetailListAdapter mAdapter;
    private ArrayList<Detail> detailsList=new ArrayList<Detail>();
    private int num=0;
    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_launch_elecledger);
    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.launchTopTitle);
        titleView.setTitle("发起工单");
        titleView.setRightBtnText("发起");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(LaunchElecledgerActivity.this);
            }
        });

        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorkOrder();
            }
        });
        add_luanch_ele_img=(ImageView) findViewById(R.id.add_luanch_ele_img);
        add_luanch_ele_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailedlist_num.setText("共"+detailsList.size()+"条记录");
                Intent mIntent=new Intent(LaunchElecledgerActivity.this,DetailActivity.class);
                startActivityForResult(mIntent, 1);
                //GGJAplication.startActivity(LaunchElecledgerActivity.this,DetailActivity.class);
                //addOneDetailList();
            }
        });

        add_company=(TextView) findViewById(R.id.add_company);
        add_company.setText(GGJAplication.userBean.getData().getCompanyName());
        add_name=(TextView) findViewById(R.id.add_name);
        add_name.setText(GGJAplication.userBean.getData().getName());

        detailedlist_num=(TextView) findViewById(R.id.detailedlist_num);
        launchElecListView=(ListView) findViewById(R.id.launchElecListView);


    }

    @Override
    public void loadData() {

        mAdapter=new DetailListAdapter(LaunchElecledgerActivity.this, detailsList, new DetailListAdapter.DeleteDetailListCallBack() {
            @Override
            public void deleteDetailList(int index) {
                detailsList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });
        launchElecListView.setAdapter(mAdapter);
    }

    public void addWorkOrder(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.ADD_WORK_ORDERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        BaseBean baseBean=gson.fromJson(s, BaseBean.class);
                        if (baseBean.isSuccess()==true && baseBean.getStatus()==200) {
                            Toast.makeText(LaunchElecledgerActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            GGJAplication.startActivity(LaunchElecledgerActivity.this, MyElecLedgerActivity.class);
                            GGJAplication.finshActivity(LaunchElecledgerActivity.this);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(LaunchElecledgerActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Gson gson=new Gson();
                HashMap<String, String> map = new HashMap<>();
                map.put("userId",""+ GGJAplication.userID);
                map.put("details",gson.toJson(detailsList));
                return map;

            }
        };
        GGJAplication.getRequestQueue().add(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode ==2){
           Detail detail = (Detail) data.getSerializableExtra("detail");
            detailsList.add(detail);
            mAdapter.notifyDataSetChanged();
        }

    }
}
