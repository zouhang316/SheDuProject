package com.beenvip.shedu.contractor.mybanzu;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.adapter.MemberListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MemberFragment extends BaseFragment {
    private ListView listView;
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        List<String> strings=new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            strings.add("");
        }
        listView= (ListView) view.findViewById(R.id.member_lv);
        MemberListAdapter adapter=new MemberListAdapter(getContext(),strings);
        listView.setAdapter(adapter);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_member;
    }
}
