package com.chinasofti.guguanjia.application;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.chinasofti.guguanjia.bean.UserBean;
import com.chinasofti.guguanjia.location.service.LocationService;

/**
 * Created by hshuai on 2017/2/19.
 */

public class GGJAplication  extends Application {
    private Context mContext;
    private final static String TAG = "GGJApplication";
    private static GGJAplication mInstance = null;
    public static RequestQueue mRequestQueue;
    
    public LocationService locationService;
    public Vibrator mVibrator;

    public static long userID=37;
    public static UserBean userBean;


    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mContext = this.getApplicationContext();
        mInstance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mRequestQueue= Volley.newRequestQueue(getApplicationContext());

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

    }

    public static GGJAplication getInstance() {
        if (null == mInstance) {
            mInstance = new GGJAplication();
        }
        return mInstance;
    }

    /**
     * 获得请求队列
     * @return
     */
    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    /**
     * 获取Preference设置
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
    /**
     * activity跳转
     * @param context
     * @param mclass
     */
    public static void startActivity(Context context, Class<?> mclass) {
        context.startActivity(new Intent(context, mclass));
    }
    /**
     * 关闭activity
     * @param activity
     */
    public static void finshActivity(Activity activity) {
        activity.finish();
    }
    /**
     * intent跳转
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
