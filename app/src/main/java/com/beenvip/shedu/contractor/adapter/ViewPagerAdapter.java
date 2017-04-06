package com.beenvip.shedu.contractor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.beenvip.shedu.base.BaseFragment;

import java.util.List;

/**
 * Created by ZH on 2017/3/28.
 * 497239511@qq.com
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> flist;
    private String [] titles;
    public ViewPagerAdapter(FragmentManager fm,List<BaseFragment> flist,String [] titles) {
        super(fm);
        this.flist=flist;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
