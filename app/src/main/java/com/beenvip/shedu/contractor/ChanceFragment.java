package com.beenvip.shedu.contractor;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.base.BaseListFragment;
import com.beenvip.shedu.contractor.adapter.ViewPagerAdapter;
import com.beenvip.shedu.utils.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/21.
 * 497239511@qq.com
 */

public class ChanceFragment extends BaseFragment {
    private TextView title;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<BaseFragment> fragmentList;
    private String [] tags;
    private int type;
    @Override
    public void initData(Bundle savedInstanceState) {
        type= PreferenceManager.getInstance(getContext()).getInt("type",0);
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        title= (TextView) view.findViewById(R.id.chance_title);
        tabLayout= (TabLayout) view.findViewById(R.id.chance_tablayout);
        viewPager= (ViewPager) view.findViewById(R.id.chance_vp);
        setTabLayoutTitle(type);
        initAdapter();
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_chance;
    }
    private void initAdapter(){

        fragmentList=new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BaseListFragment fragment=new BaseListFragment();
            fragment.setType(type);
            fragment.setTag(tags[i]);
            fragmentList.add(fragment);
        }
        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager(),fragmentList,tags);
        viewPager.setAdapter(adapter);
    }
    private void setTabLayoutTitle(int indetity){
        switch (indetity){
            case 0:
                //工人 工作机会
                title.setText("找供应商");
                tags=new String[]{"工种分类","服务地区","时间排序"};

                break;
            case 1:
                //发包商 找承包商
                title.setText("找承包商");
                tags=new String[]{"承包方分类","服务地区","时间排序"};
                break;
            case 2:
                //第三方（供应商） 需求大厅
                title.setText("需求大厅");
                tags=new String[]{"需求分类","需求地区","时间排序"};
                break;
            default:
                title.setText("找供应商");
                tags=new String[]{"工种分类","服务地区","时间排序"};
                break;
        }
    }
}
