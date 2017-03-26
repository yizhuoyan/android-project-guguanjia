package com.chinasofti.guguanjia.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;


/**
 * Created by hshuai on 2017/1/19.
 */

public class TitleView extends RelativeLayout{
    /**
     * 左边按钮
     */
    private RelativeLayout backBtn;
    /**
     * 标题
     */
    private TextView mtitle;
    /**
     * 右边按钮
     */
    private  TextView rightBtn;

    public TitleView(Context context, AttributeSet attrs){
        super(context, attrs);
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.titleview,this);
        //获取控件
        backBtn= (RelativeLayout) findViewById(R.id.backBtn);
        mtitle= (TextView) findViewById(R.id.title);
        rightBtn= (TextView) findViewById(R.id.rightBtn);

    }

    /**
     * 设置标题是否可见
     * @param flag
     */
    public void setTitleVisible(int flag){
        mtitle.setVisibility(flag);
    }
    // 设置标题的方法
    public void setTitle(String title) {
        mtitle.setText(title);
    }
    /**
     * 设置左边是否可见
     * @param flag
     */
    public void setLeftBtnVisible(int flag){
        backBtn.setVisibility(flag);
    }

    /**
     * 为左侧返回按钮添加自定义点击事件
     * @param listener
     */
    public void setLeftButtonListener(OnClickListener listener) {
        backBtn.setOnClickListener(listener);
    }
    /**
     * 设右边是否可见
     * @param flag
     */
    public void setRightBtnVisible(int flag){
        rightBtn.setVisibility(flag);
    }
    /**
     * 设置右边文字
     * @param rightBtnText
     */
    public void setRightBtnText(String rightBtnText){
        rightBtn.setText(rightBtnText);
    }
    // 为右边按钮添加自定义点击事件
    public void setRightButtonListener(OnClickListener listener) {
        rightBtn.setOnClickListener(listener);
    }



}









