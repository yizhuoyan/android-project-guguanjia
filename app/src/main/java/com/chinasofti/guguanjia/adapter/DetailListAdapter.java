package com.chinasofti.guguanjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chinasofti.guguanjia.R;
import com.chinasofti.guguanjia.bean.Detail;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/2/22.
 */

public class DetailListAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<Detail> detailsList;


    private DeleteDetailListCallBack deleteCallBack;

    public void setDeleteCallBack(DeleteDetailListCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }

    public interface DeleteDetailListCallBack{
        void deleteDetailList(int index);
    }
    public DetailListAdapter(Context context, ArrayList<Detail> detailsList, DeleteDetailListCallBack deleteCallBack){
        super();
        this.context=context;
        this.detailsList=detailsList;
        this.deleteCallBack=deleteCallBack;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return detailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return detailsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Detail detail=(Detail)getItem(i);
        ViewHolder viewHolder=null;
        if (view == null){
            viewHolder=new ViewHolder();
            view=mInflater.inflate(R.layout.details_list_item, null);
            viewHolder.detail_list_delete=(ImageView)view.findViewById(R.id.detail_list_delete);
            viewHolder.detail_list_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteCallBack.deleteDetailList(i);
                }
            });
            viewHolder.detail_list_num=(TextView)view.findViewById(R.id.detail_list_num);
            viewHolder.detail_list_baozhuang=(TextView)view.findViewById(R.id.detail_list_baozhuang);
            viewHolder.detail_list_weight=(TextView)view.findViewById(R.id.detail_list_weight);
            view.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.detail_list_num.setText(detail.getWasteCode());
        switch (detail.getPackaging()){
            case "0":
                viewHolder.detail_list_baozhuang.setText("固态");
                break;
            case "1":
                viewHolder.detail_list_baozhuang.setText("粉尘");
                break;
            case "2":
                viewHolder.detail_list_baozhuang.setText("液态");
        }

        viewHolder.detail_list_weight.setText(""+detail.getWeight());

        return view;
    }

    static class ViewHolder{
        ImageView detail_list_delete;
        TextView detail_list_num;
        TextView detail_list_baozhuang;
        TextView detail_list_weight;
    }
}
