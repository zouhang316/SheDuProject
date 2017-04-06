package com.beenvip.shedu.supplier.index;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.beenvip.shedu.CityBean;
import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.holder.LocalImageHolderView;
import com.beenvip.shedu.supplier.adapter.DemandAdapter;
import com.beenvip.shedu.view.MyListView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class SupplierIndexFragment extends BaseFragment {
    private ConvenientBanner banner;
    private ArrayList<Integer> imageList;
    private RelativeLayout layout;
    private MyListView myListView;
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        banner= (ConvenientBanner) view.findViewById(R.id.banner);
        layout= (RelativeLayout) view.findViewById(R.id.touming);
        myListView= (MyListView) view.findViewById(R.id.listview);
        myListView.setFocusable(false);
        layout.getBackground().setAlpha(100);
        initBanner();
        initData();
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_supplierindex;
    }

    private void initBanner(){
        imageList=new ArrayList<>();
        imageList.add(R.mipmap.test);
        imageList.add(R.mipmap.test1);
        imageList.add(R.mipmap.test2);
        banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },imageList);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }
    private void initData(){
        List<CityBean> beanList=new ArrayList<>();
        CityBean bean=new CityBean();
        for (int i = 0; i < 10; i++) {
            beanList.add(bean);
        }
        DemandAdapter adapter=new DemandAdapter(getContext(),beanList);
        myListView.setAdapter(adapter);
    }
}
