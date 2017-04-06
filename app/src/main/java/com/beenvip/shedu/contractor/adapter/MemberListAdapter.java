package com.beenvip.shedu.contractor.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MemberListAdapter extends BaseListViewAdapter {

    public MemberListAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_member;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, Object o, int position) {

    }
}
