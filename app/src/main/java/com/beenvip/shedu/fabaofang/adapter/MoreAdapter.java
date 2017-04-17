package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.user.bean.FenleiBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/17.
 * 497239511@qq.com
 */

public class MoreAdapter extends BaseAdapter {
    private Context context;
    private List<FenleiBean.DataBean> list;

    public MoreAdapter(Context context, List<FenleiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context,R.layout.item_more,null);
            holder.gridView= (GridView) convertView.findViewById(R.id.more_gridview);
            holder.title= (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getName());
        List<FenleiBean.DataBean.SubBean> subBeanList=list.get(position).getSub();
        MoreChildGridAdapter adapter=new MoreChildGridAdapter(context,subBeanList);
        holder.gridView.setAdapter(adapter);
        return convertView;
    }
    class ViewHolder{
        GridView gridView;
        TextView title;
    }
}
