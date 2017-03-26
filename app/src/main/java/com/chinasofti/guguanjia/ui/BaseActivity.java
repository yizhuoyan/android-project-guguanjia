package com.chinasofti.guguanjia.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.chinasofti.guguanjia.utils.AppManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * BaseActivity,所有activity继承于该activity
 * 将每个activity添加到栈里，方便退出时清除栈信息
 */
public class BaseActivity extends Activity {
    //是否退出
    private static Boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        loadContentView();
        initLayoutView();
        loadData();
    }

    /**
     * 加载activity layout
     */
    public void loadContentView(){}
    /**
     * 初始化控件
     */
    public void initLayoutView(){}

    /**
     * 加载数据
     */
    public void loadData(){}


    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        loadData();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }
    public void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    AppManager.getAppManager().finishActivity();
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            AppManager.getAppManager().AppExit(this);

        }
    }
}
