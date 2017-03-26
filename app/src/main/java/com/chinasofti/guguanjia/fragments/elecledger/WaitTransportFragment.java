package com.chinasofti.guguanjia.fragments.elecledger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.chinasofti.guguanjia.bean.WorkOrdersBean;
import com.chinasofti.guguanjia.bean.WorkOrdersListBean;
import com.chinasofti.guguanjia.ui.WorkOrderDetailActivity;
import com.chinasofti.guguanjia.utils.DateUtils;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hshuai on 2017/2/19.
 */

public class WaitTransportFragment extends Fragment{

    protected View waitTransportView;
    protected XListView xListView;
    protected Context mContext;
    private ElecLedgerListBean elecLedger=new ElecLedgerListBean();
    private ArrayList<WorkOrdersBean> elecLedgerList=new ArrayList<WorkOrdersBean>();

    private ElecLedgerListAdapter mAdapter;
    private int currentPage=1;
    private int pages;

    public WaitTransportFragment() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        waitTransportView=inflater.inflate(R.layout.wait_transport_fragment,null);
        xListView=(XListView) waitTransportView.findViewById(R.id.elec_xlistview);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                currentPage=1;
                elecLedgerList.clear();
                initData();
                onLoad();
            }

            @Override
            public void onLoadMore() {
                currentPage+=1;
                if(currentPage<=pages){
                    initData();
                }
                onLoad();
            }
        });
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), WorkOrderDetailActivity.class);
                intent.putExtra("workOrderId",elecLedgerList.get(i).getId());
                GGJAplication.startActivity(getActivity(), intent);
            }
        });

        initData();
        return waitTransportView;
    }

    private void initData(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.WAIT_TRANSPORT_ORDERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WorkOrdersListBean workOrdersListBean=gson.fromJson(s, WorkOrdersListBean.class);
                        elecLedgerList=workOrdersListBean.getData();
                        mAdapter=new ElecLedgerListAdapter(getActivity(),elecLedgerList);
                        xListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_SHORT).show();
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

