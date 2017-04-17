package com.beenvip.shedu.contractor.mybanzu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ListView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.adapter.MemberListAdapter;
import com.beenvip.shedu.utils.GetContacts;
import com.beenvip.shedu.utils.MyContacts;

import java.util.ArrayList;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MemberFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<MyContacts> myContactses;
    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.member_lv);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
           return;
        }
        myContactses= GetContacts.getAllContacts(getActivity());
        MemberListAdapter adapter=new MemberListAdapter(getContext(),myContactses);
        listView.setAdapter(adapter);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_member;
    }
}
