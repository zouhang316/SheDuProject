package com.beenvip.shedu.contractor.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by ZH on 2017/4/6.
 * 497239511@qq.com
 */

public class AddressSwitchAdapter extends BaseListViewAdapter<String> {
    public AddressSwitchAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_addressswich;
    }

    @Override
    public void convert(final BaseListViewViewHolder holder, String s, int position) {
        holder.setText(R.id.address,s);
    }


}
