package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.user.bean.FenleiBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/7.
 * 497239511@qq.com
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<FenleiBean.DataBean> list;

    public GridViewAdapter(Context context, List<FenleiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            if (position==list.size()){
                convertView=View.inflate(context, R.layout.item_hotjoblast,null);
            }else {
                convertView=View.inflate(context, R.layout.item_hotjob,null);
            }
            vh.textView= (TextView) convertView.findViewById(R.id.job);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        if (position<list.size()){
            vh.textView.setText(list.get(position).getName());
        }

        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
