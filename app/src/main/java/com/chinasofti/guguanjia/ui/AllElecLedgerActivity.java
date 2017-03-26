package com.chinasofti.guguanjia.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.fragments.elecledger.AllElecLedgerFragment;
import com.chinasofti.guguanjia.fragments.elecledger.CompletedFragment;
import com.chinasofti.guguanjia.fragments.elecledger.WaitTransportFragment;
import com.chinasofti.guguanjia.utils.AppManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子台账
 */
public class AllElecLedgerActivity extends IndicatorFragmentActivity {

    //选项卡列表
    private ArrayList<Fragment> mTabs = new ArrayList<Fragment>();
    private static Boolean isExit = false;

    public static final int ALLELECLEDGER = 0;//全部
    public static final int WAITTRANSPORT = 1;//等待中
//    public static final int TRANSPOTING = 2;//运输中
    public static final int COMPLETED = 3;//已完成


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);

    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        tabs.add(new TabInfo(ALLELECLEDGER, getString(R.string.AllElecLedgerFragment),
                AllElecLedgerFragment.class));
        tabs.add(new TabInfo(WAITTRANSPORT, getString(R.string.WaitTransportFragment),
                WaitTransportFragment.class));
//        tabs.add(new TabInfo(TRANSPOTING, getString(R.string.TranspotingFragment),
//                TranspotingFragment.class));
        tabs.add(new TabInfo(COMPLETED, getString(R.string.CompletedFragment),
                CompletedFragment.class));
        return ALLELECLEDGER;

    }

    @Override
    protected String setTopTitle() {
        return "电子台账";
    }

}
