package com.beenvip.shedu.fabaofang.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.fabaofang.activity.MoreActivity;
import com.beenvip.shedu.fabaofang.adapter.FindBanzuAdapter;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;
import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.publics.bean.FenleiBean;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.view.LoadMoreListView;
import com.bigkoo.pickerview.OptionsPickerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2017/4/18.
 * 497239511@qq.com
 */

public class FabaoFindBanZuFragment extends BaseFragment implements View.OnClickListener,LoadMoreListView.MyLoadingListener,SwipeRefreshLayout.OnRefreshListener{
    private TextView fenlei;
    private TextView diqu;
    private TextView nianxian;
    private TextView fujin;
    private LoadMoreListView listView;
    private List<String> dataList;
    private FindBanzuAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private OptionsPickerView pickerView;
    private List<String> dates;
    private FenleiBean fenleiBeanData;
    private static final int LOADMORE=1;
    private static final int REFRESH=2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case LOADMORE:
                    loadMore();
                    break;
                case REFRESH:
                    refreshi();
                    break;
            }
        }
    };
    @Override
    public void initData(Bundle savedInstanceState) {
        getSortData();
        getBanzuList();
        dataList=new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dataList.add("");
        }
        dates=new ArrayList<>();
        dates.add("全部");
        dates.add("一年以上");
        dates.add("二年以上");
        dates.add("三年以上");
        dates.add("四年以上");
        dates.add("五年以上");
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        fenlei= (TextView) view.findViewById(R.id.fenlei);
        diqu= (TextView) view.findViewById(R.id.diqu);
        nianxian= (TextView) view.findViewById(R.id.nianxian);
        listView= (LoadMoreListView) view.findViewById(R.id.listview);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,R.color.radiogroupTextColor,R.color.grenn,R.color.black);
        setListener();
    }
    private void setListener(){
        fenlei.setOnClickListener(this);
        diqu.setOnClickListener(this);
        nianxian.setOnClickListener(this);
        listView.setInterface(this);
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_findbanzu;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.fenlei:
                Intent intent=new Intent(getActivity(),MoreActivity.class);
                intent.putExtra("data",fenleiBeanData);
                intent.putExtra("title","班组分类");
                startActivityForResult(intent,0);
                break;
            case R.id.diqu:
                break;
            case R.id.nianxian:
                showPickView();
                break;
        }

    }
    private void loadMore(){
        if (dataList.size()>20){
            listView.noMore();
            return;
        }
        for (int i = 0; i <5 ; i++) {
            dataList.add("");
        }
        listView.loadComplate();
        adapter.notifyDataSetChanged();
    }
    private void refreshi(){
        LalaLog.i("refreshing");
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoad() {
        handler.sendEmptyMessageDelayed(LOADMORE,2000);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH,4000);
    }

    //获取头部班组分类数据
    public void getSortData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("type","2");
        httpHelper.asyncGetRequest(ApiContacts.INDEX_GETSORT, paramers, FenleiBean.class, new HttpCallBackListener<FenleiBean>() {
            @Override
            public void onSuccess(FenleiBean fenleiBean) {
                EventBus.getDefault().postSticky(fenleiBean);
                fenleiBeanData=fenleiBean;
            }
            @Override
            public void onFailed(FenleiBean fenleiBean) {

            }
        },false);
    }
    public void getBanzuList(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mid","6");
        httpHelper.asyncGetRequest(ApiContacts.LIST, paramers, BanzuListBean.class, new HttpCallBackListener<BanzuListBean>() {
            @Override
            public void onSuccess(BanzuListBean banzuListBean) {
                adapter=new FindBanzuAdapter(getActivity(),banzuListBean.getData());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailed(BanzuListBean banzuListBean) {
                if (banzuListBean.getErrorCode().equals("-1")){
                    showToast("当前网络不佳，请检查网络后重试");
                    return;
                }
            }
        },true);

    }
    private void showPickView(){
        pickerView=new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                showToast(dates.get(options1));

            }
        }).build();
        pickerView.setPicker(dates);
        pickerView.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode== Activity.RESULT_OK){
            fenlei.setText(data.getStringExtra("data"));
        }
    }
}
