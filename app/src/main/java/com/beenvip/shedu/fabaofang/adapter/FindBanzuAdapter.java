package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/18.
 * 497239511@qq.com
 */

public class FindBanzuAdapter extends BaseListViewAdapter<BanzuListBean.DataBean> {


    public FindBanzuAdapter(Context context, List<BanzuListBean.DataBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_banzu;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, final BanzuListBean.DataBean dataBean, int position) {
            holder.setText(R.id.item_title,dataBean.getBName());
            holder.setText(R.id.item_title2,dataBean.getBNumer()+"人");
            holder.setText(R.id.item_title3,dataBean.getBTypename());
            holder.setText(R.id.city,dataBean.getBCityname());
    }


}
