package com.chinasofti.guguanjia.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.fragments.myelecledger.HandleElecLedgerFragment;
import com.chinasofti.guguanjia.fragments.myelecledger.LaunchElecLedgerFragment;
import com.chinasofti.guguanjia.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子台账
 */
public class MyElecLedgerActivity extends IndicatorFragmentActivity {

    //选项卡列表
    private ArrayList<Fragment> mTabs = new ArrayList<Fragment>();
    private static Boolean isExit = false;

    public static final int LAUNCHELECLEDGER = 0;//发起工单
    public static final int HANDLERLECLEDGER = 1;//处理工单


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        tabs.add(new TabInfo(LAUNCHELECLEDGER, getString(R.string.LAUNCHELECLEDGER),
                LaunchElecLedgerFragment.class));
        tabs.add(new TabInfo(HANDLERLECLEDGER, getString(R.string.HANDLERLECLEDGER),
                HandleElecLedgerFragment.class));
        return LAUNCHELECLEDGER;
    }


    @Override
    protected String setTopTitle() {
        return "我的工单";
    }


}
