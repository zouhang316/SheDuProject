package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FindCbsAdapter extends BaseListViewAdapter {

    public FindCbsAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_findcbs;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, Object o, int position) {

    }
}
