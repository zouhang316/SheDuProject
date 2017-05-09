package com.beenvip.shedu.fabaofang.adapter;

import android.content.Context;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by ZH on 2017/5/5.
 * 497239511@qq.com
 */

public class NewsAdapter extends BaseListViewAdapter {
    public NewsAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_news;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, Object o, int position) {
//        holder.setText(R.id.item_title,)
    }
}
