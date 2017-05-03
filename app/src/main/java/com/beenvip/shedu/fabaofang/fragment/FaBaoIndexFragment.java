package com.beenvip.shedu.fabaofang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.adapter.ListViewAdapter;
import com.beenvip.shedu.fabaofang.activity.RecruitmentActivity;
import com.beenvip.shedu.fabaofang.activity.ReleaseDemandActivity;
import com.beenvip.shedu.fabaofang.activity.SelectProject;
import com.beenvip.shedu.fabaofang.adapter.FindBanzuAdapter;
import com.beenvip.shedu.fabaofang.adapter.GridViewAdapter;
import com.beenvip.shedu.fabaofang.bean.BanzuBean;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;
import com.beenvip.shedu.holder.LocalImageHolderView;
import com.beenvip.shedu.http.HttpListener;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.view.MyGridView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FaBaoIndexFragment extends BaseFragment implements View.OnClickListener {
    private ConvenientBanner banner;
    private ArrayList<Integer> imageList;
    private RadioButton fabao;
    private TextView emptyRecommend;
    private MyGridView mGridView;
    private GridViewAdapter adapter;
    private FindBanzuAdapter banzuAdapter;
    private List<String> datas;
    private ListView listView;
    private RadioButton zhaoGong;
    private RadioButton releaseDemand;
    private RadioButton zhaoBanzu;
    private RadioButton zhaoMuGongren;
    private List<BanzuBean.DataBean> beanList;

    @Override
    public void initData(Bundle savedInstanceState) {
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("type","1");
        datas=new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            datas.add("");
        }
        getRecommend();
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        banner= (ConvenientBanner) view.findViewById(R.id.banner);
        listView= (ListView) view.findViewById(R.id.listview);
        mGridView = (MyGridView) view.findViewById(R.id.gridview);
        emptyRecommend= (TextView) view.findViewById(R.id.empty_recommend);
        zhaoGong= (RadioButton) view.findViewById(R.id.zhaogong);
        releaseDemand= (RadioButton) view.findViewById(R.id.releasedemand);
        zhaoBanzu= (RadioButton) view.findViewById(R.id.zhaobanzu);
        zhaoMuGongren= (RadioButton) view.findViewById(R.id.zhaomugongren);
        listView.setFocusable(false);
        initListener();
        initBanner();

        getBanzuFenlei();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==beanList.size()){

                }

            }
        });
        ListViewAdapter adapter=new ListViewAdapter(getActivity(),datas);
        //listView.setAdapter(adapter);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_fabaoindex;
    }



    public void getBanzuFenlei(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("sort","2");
        httpHelper.asyncGetRequest(ApiContacts.FINDBANZU_BANZUFENLEI, paramers, BanzuBean.class, new HttpListener<BanzuBean>() {

            @Override
            public void onSuccess(BanzuBean banzuBean) {
                beanList=banzuBean.getData();
                adapter=new GridViewAdapter(getActivity(),beanList);
                mGridView.setAdapter(adapter);
            }

            @Override
            public void onFailed(BanzuBean banzuBean) {

            }
        },true);
    }

    public void getRecommend(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mid","6");
        httpHelper.asyncGetRequest(ApiContacts.LIST, paramers, BanzuListBean.class, new HttpListener<BanzuListBean>() {
            @Override
            public void onSuccess(BanzuListBean banzuListBean) {
                banzuAdapter=new FindBanzuAdapter(getActivity(),banzuListBean.getData());
                listView.setAdapter(banzuAdapter);
            }

            @Override
            public void onFailed(BanzuListBean banzuListBean) {
                LalaLog.d(banzuListBean.getErrorCode()+banzuListBean.getErrorInfo());//101 暂无推荐分类
                emptyRecommend.setVisibility(View.VISIBLE);
                if (banzuListBean.getErrorCode().equals("-1")){
                   showToast("当前网络不佳，请检查网络后重试");
                    return;
                }
                emptyRecommend.setText(banzuListBean.getErrorInfo());

            }
        },true);

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
        releaseDemand.setOnClickListener(this);
        zhaoGong.setOnClickListener(this);
        zhaoBanzu.setOnClickListener(this);
        zhaoMuGongren.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.zhaogong:
                startActivity(new Intent(getActivity(), RecruitmentActivity.class));
                break;
            case R.id.releasedemand:
                startActivity(new Intent(getActivity(), ReleaseDemandActivity.class));
                break;
            case R.id.zhaobanzu:
                startActivity(new Intent(getActivity(), SelectProject.class).putExtra("action",0));
                break;
            case R.id.zhaomugongren:
                startActivity(new Intent(getActivity(), SelectProject.class).putExtra("action",1));
                break;
        }
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
}
