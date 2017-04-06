package com.beenvip.shedu.contractor;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.adapter.ViewPagerAdapter;
import com.beenvip.shedu.contractor.mybanzu.BanzuInfoFragment;
import com.beenvip.shedu.contractor.mybanzu.MemberFragment;
import com.beenvip.shedu.contractor.mybanzu.ProjectFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MyBanzuFragment extends BaseFragment {
    private List<BaseFragment> fragmentList;
    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button joinBanzu;
    private Button createBanzu;
    private String tags[]={"成员","项目","详细信息"};
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        viewPager= (ViewPager) view.findViewById(R.id.mybanzu_vp);
        tabLayout= (TabLayout) view.findViewById(R.id.mytablayout);
        joinBanzu= (Button) view.findViewById(R.id.joinbanzu);
        createBanzu= (Button) view.findViewById(R.id.createbanzu);
        fragmentList=new ArrayList<>();
        fragmentList.add(new MemberFragment());
        fragmentList.add(new ProjectFragment());
        fragmentList.add(new BanzuInfoFragment());
        viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),fragmentList,tags);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        createBanzu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("WA");
            }
        });
        joinBanzu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("WA");
            }
        });



    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mybanzu;
    }
}
