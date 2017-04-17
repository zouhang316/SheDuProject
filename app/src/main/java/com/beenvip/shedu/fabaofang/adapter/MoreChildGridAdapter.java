package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;
import com.beenvip.shedu.user.bean.FenleiBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/17.
 * 497239511@qq.com
 */

public class MoreChildGridAdapter extends BaseListViewAdapter<FenleiBean.DataBean.SubBean> {

    public MoreChildGridAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_hotjob;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, FenleiBean.DataBean.SubBean subBean, int position) {
        holder.setText(R.id.job,subBean.getName());
    }


}
