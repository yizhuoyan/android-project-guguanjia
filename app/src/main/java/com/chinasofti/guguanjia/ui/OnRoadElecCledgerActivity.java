package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.adapter.ElecLedgerListAdapter;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.ElecLedgerListBean;
import com.chinasofti.guguanjia.bean.WorkOrdersListBean;
import com.chinasofti.guguanjia.bean.WorkOrdersBean;
import com.chinasofti.guguanjia.utils.DateUtils;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;
import com.chinasofti.guguanjia.views.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OnRoadElecCledgerActivity extends BaseActivity{
    private TitleView titleView;
    private XListView xListView;
    private ElecLedgerListBean elecLedger=new ElecLedgerListBean();
    private ArrayList<WorkOrdersBean> elecLedgerList=new ArrayList<WorkOrdersBean>();
    private ElecLedgerListAdapter mAdapter;
    private int currentPage=1;
    private int pages;
    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_on_road_elec_cledger);
    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.onRoadTopTitle);
        titleView.setTitle("在途工单");
        titleView.setRightBtnVisible(View.GONE);
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(OnRoadElecCledgerActivity.this);
            }
        });

        xListView=(XListView)findViewById(R.id.onRoadElecXlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                currentPage=1;
                elecLedgerList.clear();
                loadData();
                onLoad();
            }

            @Override
            public void onLoadMore() {
                currentPage+=1;
                if(currentPage<=pages){
                    loadData();
                }
                onLoad();
            }
        });
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(OnRoadElecCledgerActivity.this, WorkOrderDetailActivity.class);
                intent.putExtra("workOrderId",elecLedgerList.get(i).getId());
                GGJAplication.startActivity(OnRoadElecCledgerActivity.this, intent);
            }
        });
    }

    @Override
    public void loadData() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.ON_ROAD_WORK_ORDERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WorkOrdersListBean workOrdersListBean=gson.fromJson(s, WorkOrdersListBean.class);
                        elecLedgerList=workOrdersListBean.getData();
                        mAdapter=new ElecLedgerListAdapter(OnRoadElecCledgerActivity.this,elecLedgerList);
                        xListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(OnRoadElecCledgerActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("userId",""+ GGJAplication.userID);

                return map;

            }
        };
        GGJAplication.getRequestQueue().add(request);

    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime(DateUtils.getCurrDateStr());
    }
}
