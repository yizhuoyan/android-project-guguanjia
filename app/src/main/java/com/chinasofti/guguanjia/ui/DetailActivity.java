package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.Detail;
import com.chinasofti.guguanjia.bean.WasteBean;
import com.chinasofti.guguanjia.bean.WasteListBean;
import com.chinasofti.guguanjia.utils.DateUtils;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.SpinnerView.AbstractSpinerAdapter;
import com.chinasofti.guguanjia.views.SpinnerView.CustemSpinerAdapter;
import com.chinasofti.guguanjia.views.SpinnerView.SpinerPopWindow;
import com.chinasofti.guguanjia.views.TitleView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DetailActivity extends BaseActivity implements View.OnClickListener, AbstractSpinerAdapter.IOnItemSelectListener{
    private View mRootView;
    private TitleView titleView;
    private EditText wasteType,waste,morphological,packaging;
    private ImageButton wasteType_dropdown,waste_dropdown,morphological_dropdown,packaging_dropdown;
    private EditText weight,component,plateNumber;
    private List<WasteBean> wasteTypeList = new ArrayList<WasteBean>();
    private List<WasteBean> wasteList = new ArrayList<WasteBean>();
    private List<WasteBean> morphologicalList = new ArrayList<WasteBean>();
    private List<WasteBean> packagingList = new ArrayList<WasteBean>();
    private int mSpinerIndex=0;//第几个下拉列表

    private Detail detail=new Detail();
    /** 废物类型ID,大类ID. */
    private Long wasteTypeId;
    /**
     * 小类编号
     */
    private String wasteCode;


    /** 废物ID,小类ID */
    private Long wasteId;

    private AbstractSpinerAdapter mAdapter;
    private SpinerPopWindow mSpinerPopWindow;

    @Override
    public void loadContentView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    public void initLayoutView() {
        mRootView = findViewById(R.id.activity_detail);

        titleView=(TitleView) findViewById(R.id.detailTitleView);
        titleView.setTitle("废物清单");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(DetailActivity.this);
            }
        });
        titleView.setRightBtnText("完成");
        titleView.setRightButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !weight.getText().toString().trim().equals(""))
                    detail.setWeight(Float.parseFloat(weight.getText().toString().trim()));
                detail.setComponent(component.getText().toString().trim());
                detail.setCreateDate(DateUtils.getCurrDate());
                detail.setCreateBy(GGJAplication.userBean.getData().getId()+","+GGJAplication.userBean.getData().getUsername());
                detail.setPlateNumber(plateNumber.getText().toString().trim());
                Intent mIntent = new Intent();
                mIntent.putExtra("detail", detail);
                setResult(2,mIntent);
                DetailActivity.this.finish();
            }
        });

        wasteType = (EditText) findViewById(R.id.wasteType);
        wasteType_dropdown = (ImageButton) findViewById(R.id.wasteType_dropdown);
        wasteType_dropdown.setOnClickListener(this);

        waste = (EditText) findViewById(R.id.waste);
        waste_dropdown = (ImageButton) findViewById(R.id.waste_dropdown);
        waste_dropdown.setOnClickListener(this);

        morphological = (EditText) findViewById(R.id.morphological);
        morphological_dropdown = (ImageButton) findViewById(R.id.morphological_dropdown);
        morphological_dropdown.setOnClickListener(this);

        packaging = (EditText) findViewById(R.id.packaging);
        packaging_dropdown = (ImageButton) findViewById(R.id.packaging_dropdown);
        packaging_dropdown.setOnClickListener(this);


        weight=(EditText) findViewById(R.id.weight);
        component=(EditText) findViewById(R.id.component);
        plateNumber=(EditText) findViewById(R.id.plateNumber);

        //加载物体形态
        String[] morphologicals = getResources().getStringArray(R.array.morphological);
        for(int i = 0; i < morphologicals.length; i++){
            WasteBean object = new WasteBean();
            object.setId(i);
            object.setName(morphologicals[i]);
            morphologicalList.add(object);
        }
        //加载包装类型
        String[] packagings = getResources().getStringArray(R.array.packaging);
        for(int i = 0; i < packagings.length; i++){
            WasteBean object = new WasteBean();
            object.setId(i);
            object.setName(packagings[i]);
            packagingList.add(object);
        }
    }

    public void loadWasteTypeData(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.WASTE_TYPE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WasteListBean wasteListBean=gson.fromJson(s,WasteListBean.class);
                        wasteTypeList=wasteListBean.getData();

                        createSpinerPopWindow(wasteTypeList);
                        showSpinWindow(wasteType);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(DetailActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
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

    public void loadWasteData(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.WASTE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WasteListBean wasteListBean=gson.fromJson(s,WasteListBean.class);
                        wasteList=wasteListBean.getData();

                        createSpinerPopWindow(wasteList);
                        showSpinWindow(waste);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(DetailActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("userId",""+ GGJAplication.userID);
                map.put("parentId",""+ wasteTypeId);
                return map;

            }
        };
        GGJAplication.getRequestQueue().add(request);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.wasteType_dropdown:
                mSpinerIndex=1;
                if(wasteTypeList.size()==0) {
                    loadWasteTypeData();
                }else {
                    createSpinerPopWindow(wasteTypeList);
                    showSpinWindow(wasteType);
                }
                wasteId=null;
                waste.setText("");
                break;
            case R.id.waste_dropdown:
                if(mSpinerIndex==0 || wasteTypeId==null){
                    Toast.makeText(DetailActivity.this,"请选择废物大类",Toast.LENGTH_SHORT).show();
                    break;
                }

                mSpinerIndex=2;

                loadWasteData();

                break;
            case R.id.morphological_dropdown:

                mSpinerIndex=3;
                createSpinerPopWindow(morphologicalList);
                showSpinWindow(morphological);
                break;
            case R.id.packaging_dropdown:
                mSpinerIndex=4;
                createSpinerPopWindow(packagingList);
                showSpinWindow(packaging);
                break;
        }

    }

    public void createSpinerPopWindow(List<WasteBean> mlist){
        mAdapter = new CustemSpinerAdapter(this);
        mAdapter.refreshData(mlist, 0);

        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);
    }


    private void showSpinWindow(EditText mEditText){
        mSpinerPopWindow.setWidth(mEditText.getWidth());
        mSpinerPopWindow.showAsDropDown(mEditText);
    }

    @Override
    public void onItemClick(int pos) {
        switch (mSpinerIndex){
            case 1:
                setEditVlaue(wasteType,wasteTypeList,pos);
                break;
            case 2:
                setEditVlaue(waste,wasteList,pos);
                break;
            case 3:
                setEditVlaue(morphological,morphologicalList,pos);
                break;
            case 4:
                setEditVlaue(packaging,packagingList,pos);
                break;

        }

    }
    private void setEditVlaue(EditText mEditView,List<WasteBean> mList,int pos){
        if (pos >= 0 && pos <= mList.size()){
            WasteBean object = mList.get(pos);
            if (mSpinerIndex==1) {
                wasteTypeId = object.getId();
                detail.setWasteTypeId(wasteTypeId);
            }else if (mSpinerIndex==2){
                wasteId=object.getId();
                wasteCode=object.getCode();
                detail.setWasteCode(wasteCode);
            }else if(mSpinerIndex==3)
                detail.setMorphological(""+object.getId());
            else
                detail.setPackaging(""+object.getId());
            mEditView.setText(object.getName());
        }
    }
}
