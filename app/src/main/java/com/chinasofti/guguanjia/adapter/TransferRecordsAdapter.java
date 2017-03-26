package com.chinasofti.guguanjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.bean.TransferRecords;
import com.chinasofti.guguanjia.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/3/7.
 */

public class TransferRecordsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<TransferRecords> transferRecordsList;


    public TransferRecordsAdapter(Context context,ArrayList<TransferRecords> transferRecordsList){
        super();
        this.context=context;
        this.transferRecordsList=transferRecordsList;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return transferRecordsList.size();
    }

    @Override
    public Object getItem(int i) {
        return transferRecordsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        TransferRecords transferRecords=(TransferRecords)getItem(i);
       ViewHolder viewHolder=null;
        if (view == null){
            viewHolder=new ViewHolder();
            view=mInflater.inflate(R.layout.transport_list_item, null);
            viewHolder.transport_time=(TextView)view.findViewById(R.id.transport_time);
            viewHolder.transport_content=(TextView)view.findViewById(R.id.transport_content);
            viewHolder.transport_user=(TextView)view.findViewById(R.id.transport_user);
            viewHolder.transport_phone=(TextView)view.findViewById(R.id.transport_phone);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.transport_time.setText(DateUtils.dateTimeToString(transferRecords.getUpdateDate()));
        viewHolder.transport_content.setText(transferRecords.getContent());
        viewHolder.transport_user.setText(transferRecords.getName());
        viewHolder.transport_phone.setText(transferRecords.getPhone());
        return view;
    }

    static class ViewHolder{
        TextView transport_time;
        TextView transport_content;
        TextView transport_user;
        TextView transport_phone;
    }
}
