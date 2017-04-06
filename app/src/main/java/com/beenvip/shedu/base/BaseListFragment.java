package com.beenvip.shedu.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.beenvip.shedu.CityBean;
import com.beenvip.shedu.R;
import com.beenvip.shedu.contractor.activity.ProjectDetailsActivity;
import com.beenvip.shedu.contractor.adapter.ListViewAdapter;
import com.beenvip.shedu.fabaofang.adapter.FindCbsAdapter;
import com.beenvip.shedu.supplier.adapter.DemandAdapter;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.view.LoadMoreListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/28.
 * 497239511@qq.com
 */

public class BaseListFragment extends BaseFragment implements LoadMoreListView.MyLoadingListener{
    private String tag;
    private LoadMoreListView listView;
    private int type;
    private List<CityBean> beanList;
    private ListViewAdapter adapter;
    private FindCbsAdapter cbsAdapter;
    private DemandAdapter adapter1;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        listView=new LoadMoreListView(getContext());
        listView= (LoadMoreListView) view.findViewById(R.id.baselistview);
        listView.setInterface(this);
        initData();
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_baselist;
    }

    public void setTag(String tag){
        this.tag=tag;
    }


    public void setType(int type){this.type=type;}

    private void initData(){
       beanList=new ArrayList<>();
        CityBean bean=new CityBean();
        for (int i = 0; i < 10; i++) {
            beanList.add(bean);
        }
        switch (type){
            case 0:
                adapter=new ListViewAdapter(getContext(),beanList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(getActivity(), ProjectDetailsActivity.class));
                    }
                });
                break;
            case 1:
                cbsAdapter=new FindCbsAdapter(getContext(),beanList);
                listView.setAdapter(cbsAdapter);
                break;
            case 2:
                adapter1=new DemandAdapter(getContext(),beanList);
                listView.setAdapter(adapter1);
                break;
            case 3:
                cbsAdapter=new FindCbsAdapter(getContext(),beanList);
                listView.setAdapter(cbsAdapter);
                break;
        }
    }

    @Override
    public void onLoad() {
        LalaLog.i("load","loading");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (beanList.size()>30){
                    showToast("没有更多数据了");
                    listView.noMore();
                    return;
                }
                getMore();
                listView.loadComplate();

                switch (type){
                    case 0:
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        cbsAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        adapter1.notifyDataSetChanged();
                        break;
                    case 3:
                        cbsAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }, 2000);
    }

    private void getMore(){
        for (int i = 0; i < 5; i++) {
            CityBean bean=new CityBean();
            beanList.add(bean);
        }
    }
}
