package com.chinasofti.guguanjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.bean.WorkOrdersBean;
import com.chinasofti.guguanjia.utils.DateUtils;
import com.chinasofti.guguanjia.utils.ImageDownloader;
import com.chinasofti.guguanjia.utils.UrlConfig;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/2/20.
 */

public class ElecLedgerListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<WorkOrdersBean> elecLedgerList;
    private LayoutInflater mInflater;
    private ImageDownloader imageDownloader;

    public ElecLedgerListAdapter() {
        super();
    }
    public ElecLedgerListAdapter(Context context,ArrayList<WorkOrdersBean> elecLedgerList) {
        super();
        this.context = context;
        this.elecLedgerList = elecLedgerList;
        this.mInflater=LayoutInflater.from(context);
        imageDownloader=new ImageDownloader(context);
    }

    @Override
    public int getCount() {
        return elecLedgerList.size();
    }

    @Override
    public Object getItem(int i) {
        return elecLedgerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WorkOrdersBean workOrdersBean=(WorkOrdersBean)getItem(i);
        ViewHolder viewHolder=null;
        if (view == null){
            viewHolder=new ViewHolder();
            view=mInflater.inflate(R.layout.elec_ledger_list_item, null);
            viewHolder.elec_user_header=(ImageView)view.findViewById(R.id.elec_user_header);
            viewHolder.elec_user_name=(TextView)view.findViewById(R.id.elec_user_name);
            viewHolder.elec_work_num=(TextView)view.findViewById(R.id.elec_work_num);
            viewHolder.elec_work_num_date=(TextView)view.findViewById(R.id.elec_work_num_date);
            viewHolder.elec_work_num_state=(TextView)view.findViewById(R.id.elec_work_num_state);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }
        //viewHolder.elec_user_header.setImageBitmap();根据图片路径加载图片
       // Toast.makeText(context,UrlConfig.BASEURL+workOrdersBean.getCompanyLogo(),Toast.LENGTH_SHORT).show();
        String imgUrl="";
        if(workOrdersBean.getCompanyLogo() !=null && !workOrdersBean.getCompanyLogo().equals("")){
             imgUrl=workOrdersBean.getCompanyLogo().split(",")[0];
        }

        imageDownloader.download(UrlConfig.BASEURL+imgUrl,viewHolder.elec_user_header, ImageView.ScaleType.CENTER_INSIDE);
        viewHolder.elec_user_name.setText(workOrdersBean.getCompanyName());
        viewHolder.elec_work_num.setText(workOrdersBean.getCode());
        viewHolder.elec_work_num_date.setText(DateUtils.dateTime2String(workOrdersBean.getUpdateDate()));
        switch (workOrdersBean.getStatus()){
            case 0:
                viewHolder.elec_work_num_state.setText("待运输");
                break;
            case 1:
                viewHolder.elec_work_num_state.setText("运输中");
                break;
            case 2:
                viewHolder.elec_work_num_state.setText("已验收");
                break;

        }

        return view;
    }

    static class ViewHolder{
        ImageView elec_user_header;
        TextView elec_user_name;
        TextView elec_work_num;
        TextView elec_work_num_date;
        TextView elec_work_num_state;
    }
}
