package com.beenvip.shedu.fabaofang.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.base.BaseListFragment;
import com.beenvip.shedu.contractor.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FindgysFragment extends BaseFragment {
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<BaseFragment> fragmentList;
    private String [] tags={"供应商分类","服务地区","时间排序"};
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        title= (TextView) view.findViewById(R.id.chance_title);
        tabLayout= (TabLayout) view.findViewById(R.id.chance_tablayout);
        viewPager= (ViewPager) view.findViewById(R.id.chance_vp);
        tabLayout.setupWithViewPager(viewPager);
        title.setText("找供应商");
        initAdapter();

    }
    private void initAdapter(){

        fragmentList=new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BaseListFragment fragment=new BaseListFragment();
            fragment.setTag(tags[i]);
            fragment.setType(3);
            fragmentList.add(fragment);
        }
        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager(),fragmentList,tags);
        viewPager.setAdapter(adapter);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_chance;
    }
}
