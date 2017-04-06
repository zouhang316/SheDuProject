package com.beenvip.shedu.contractor.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by ZH on 2017/3/31.
 * 497239511@qq.com
 */

public class HotJobAdapter extends BaseListViewAdapter {

    public HotJobAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_hotjob;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, Object o, int position) {

    }
}
