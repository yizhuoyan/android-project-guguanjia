package com.chinasofti.guguanjia.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.adapter.TransferRecordsAdapter;
import com.chinasofti.guguanjia.application.GGJAplication;
import com.chinasofti.guguanjia.bean.WorkOrdersDetailBean;
import com.chinasofti.guguanjia.utils.DecodeUrl;
import com.chinasofti.guguanjia.utils.UrlConfig;
import com.chinasofti.guguanjia.views.TitleView;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Map;


public class WorkOrderDetailActivity extends BaseActivity {
    private TitleView titleView;
    private ImageView erweima;
    private TextView work_order_text;
    private ListView work_order_transform_listview;
    private TransferRecordsAdapter mAdapter;

    //扫描二维码结果 url
    private String requrstUrl;
    private Bitmap erweimaBitmap;
    private RelativeLayout work_order_layout;
    private long workOrderId=0;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    MapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位



    @Override
    public void loadContentView() {
        Intent intent = getIntent();
        String url="";
        if (intent != null){
            workOrderId=intent.getLongExtra("workOrderId",0);
            if(intent.getExtras()!=null) {
                url = intent.getExtras().getString("result");
                if (url != null && !url.equals(""))
                    requrstUrl = url;
            }
        }
        setContentView(R.layout.activity_work_order_detail);
    }

    @Override
    public void initLayoutView() {
        titleView=(TitleView) findViewById(R.id.workOrderTitleView);
        titleView.setTitle("工单详情");
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GGJAplication.finshActivity(WorkOrderDetailActivity.this);
            }
        });
        titleView.setRightBtnVisible(View.GONE);


        // 地图初始化
        mMapView = (MapView)findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 开启室内图
        //mBaiduMap.setIndoorEnable(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1500);

        mLocClient.setLocOption(option);
        mLocClient.start();




        erweima=(ImageView) findViewById(R.id.work_order_img);

        work_order_layout=(RelativeLayout) findViewById(R.id.work_order_layout);
        work_order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WorkOrderDetailActivity.this,ShowWorkOrderImgActivity.class);
                intent.putExtra("erweimaBitmap", erweimaBitmap);
                GGJAplication.startActivity(WorkOrderDetailActivity.this,intent);
            }
        });

        work_order_text=(TextView) findViewById(R.id.work_order_text);
        work_order_transform_listview=(ListView) findViewById(R.id.work_order_transform_listview);
    }

    public void createImgByUrl(){
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenWidth = dm.widthPixels;
            BitMatrix bitMatrix = new MultiFormatWriter().encode(requrstUrl, BarcodeFormat.QR_CODE, screenWidth*2/3,screenWidth*2/3, hints);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? Color.BLACK :Color.WHITE;
                }
            }
            erweimaBitmap=Bitmap.createBitmap(pixels,screenWidth*2/3,screenWidth*2/3, Bitmap.Config.RGB_565);
            erweima.setImageBitmap(erweimaBitmap);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlConfig.DERAIL_ORDERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WorkOrdersDetailBean workOrdersDetailBean=gson.fromJson(s,WorkOrdersDetailBean.class);
                        //设置工单编号
                        work_order_text.setText(workOrdersDetailBean.getData().getCode());
                        //创建二维码
                        requrstUrl=UrlConfig.DERAIL_ORDERS_URL+"?workOrderId="+workOrdersDetailBean.getData().getId();
                        createImgByUrl();

                        mAdapter=new TransferRecordsAdapter(WorkOrderDetailActivity.this,workOrdersDetailBean.getData().getTransferRecords());
                        work_order_transform_listview.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("this", volleyError.toString());
                Toast.makeText(WorkOrderDetailActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("userId",""+ GGJAplication.userID);
                if (workOrderId==0){
                    workOrderId= Long.parseLong(DecodeUrl.URLRequest(requrstUrl).get("workOrderId"));
                }

                map.put("workOrderId",""+ workOrderId);
                return map;

            }
        };
        GGJAplication.getRequestQueue().add(request);
    }



    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        private String lastFloor = null;

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mBaiduMap.clear();
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();

            mBaiduMap.setMyLocationData(locData);

            Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.mipmap.img_marker);
            BitmapDescriptor icon= BitmapDescriptorFactory.fromBitmap(bmp);

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions=new MarkerOptions().position(latLng).icon(icon);
            mBaiduMap.addOverlay(markerOptions);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }

        public void onConnectHotSpotMessage(String s, int i){

        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }


}
