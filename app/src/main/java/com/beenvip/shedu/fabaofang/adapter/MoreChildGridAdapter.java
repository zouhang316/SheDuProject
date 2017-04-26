package com.beenvip.shedu.fabaofang.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;
import com.beenvip.shedu.publics.bean.FenleiBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/17.
 * 497239511@qq.com
 */

public class MoreChildGridAdapter extends BaseListViewAdapter<FenleiBean.DataBean.SubBean> {
    private Activity activity;

    public MoreChildGridAdapter(Context context, List mLists,Activity activity) {
        super(context, mLists);
        this.activity=activity;
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_hotjob;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, final FenleiBean.DataBean.SubBean subBean, final int position) {
        holder.setText(R.id.job,subBean.getName());
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("data",subBean.getName());
                activity.setResult(Activity.RESULT_OK,intent);
                activity.finish();
            }
        });
    }
}
