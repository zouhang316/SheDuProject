package com.beenvip.shedu.fabaofang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.adapter.ListViewAdapter;
import com.beenvip.shedu.event.ShowFindBanzu;
import com.beenvip.shedu.fabaofang.activity.FabaoActivity;
import com.beenvip.shedu.fabaofang.activity.RecruitmentActivity;
import com.beenvip.shedu.fabaofang.activity.ReleaseDemandActivity;
import com.beenvip.shedu.fabaofang.adapter.GridViewAdapter;
import com.beenvip.shedu.holder.LocalImageHolderView;
import com.beenvip.shedu.view.MyGridView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FaBaoIndexFragment extends BaseFragment implements View.OnClickListener {
    private ConvenientBanner banner;
    private ArrayList<Integer> imageList;
    private RelativeLayout layout;
    private RadioButton fabao;
    private RadioButton findBanzu;
    private MyGridView mGridView;
    private GridViewAdapter adapter;
    private List<String> datas;
    private ListView listView;
    private RadioButton zhaoGong;
    private RadioButton releaseDemand;
    @Override
    public void initData(Bundle savedInstanceState) {
        datas=new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            datas.add("");
        }



    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        banner= (ConvenientBanner) view.findViewById(R.id.banner);
        layout= (RelativeLayout) view.findViewById(R.id.touming);
        fabao= (RadioButton) view.findViewById(R.id.fabao);
        findBanzu= (RadioButton) view.findViewById(R.id.findbanzu);
        listView= (ListView) view.findViewById(R.id.listview);
        mGridView = (MyGridView) view.findViewById(R.id.gridview);
        zhaoGong= (RadioButton) view.findViewById(R.id.zhaogong);
        releaseDemand= (RadioButton) view.findViewById(R.id.releasedemand);
        layout.getBackground().setAlpha(100);
        initListener();
        initBanner();

        adapter=new GridViewAdapter(getActivity(),datas);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==datas.size()){
                    EventBus.getDefault().post(new ShowFindBanzu());
                }

            }
        });
        ListViewAdapter adapter=new ListViewAdapter(getActivity(),datas);
        listView.setAdapter(adapter);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_fabaoindex;
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
    private void initListener(){
        fabao.setOnClickListener(this);
        findBanzu.setOnClickListener(this);
        releaseDemand.setOnClickListener(this);
        zhaoGong.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.fabao:
                startActivity(new Intent(getActivity(), FabaoActivity.class));
                break;
            case R.id.findbanzu:
                EventBus.getDefault().post(new ShowFindBanzu());
                break;
            case R.id.zhaogong:
                startActivity(new Intent(getActivity(), RecruitmentActivity.class));
                break;
            case R.id.releasedemand:
                startActivity(new Intent(getActivity(), ReleaseDemandActivity.class));
                break;
        }

    }
}
