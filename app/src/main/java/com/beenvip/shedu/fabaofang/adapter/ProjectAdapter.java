package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;
import com.beenvip.shedu.fabaofang.bean.ProjectBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/28.
 * 497239511@qq.com
 */

public class ProjectAdapter extends BaseListViewAdapter<ProjectBean.DataBean> {

    public ProjectAdapter(Context context, List<ProjectBean.DataBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_selectproject;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, ProjectBean.DataBean dataBean, int position) {
            holder.setText(R.id.item_title,dataBean.getPName());
            holder.setText(R.id.item_title2,dataBean.getPAddress());
    }
}
