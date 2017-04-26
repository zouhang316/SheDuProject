package com.beenvip.shedu.contractor.mybanzu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private MemberListAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                listView.setAdapter(adapter);
                refreshLayout.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }
        }
    };
    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.member_lv);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swperefreshi);
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,R.color.radiogroupTextColor,R.color.grenn,R.color.black);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
           return;
        }
   new Thread(new Runnable() {
       @Override
       public void run() {
           refreshLayout.setRefreshing(true);
           myContactses = GetContacts.getCon(getActivity());
           adapter=new MemberListAdapter(getActivity(),myContactses);
           Message message=new Message();
           message.what=1;
           handler.sendMessage(message);
       }
   }).start();



    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_member;
    }



}
