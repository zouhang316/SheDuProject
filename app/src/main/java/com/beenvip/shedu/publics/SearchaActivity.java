package com.beenvip.shedu.publics;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.beenvip.shedu.CityBean;
import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.contractor.adapter.HotJobAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/31.
 * 497239511@qq.com
 */

public class SearchaActivity extends BaseActivity {
    private TextView mSearchgo;
    private EditText mSearchView;
    private GridView gridView;
    private GridView historyGridview;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mSearchgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSearchgo.getText().equals("搜索")) {
                    SearchaActivity.this.finish();
                    return;
                }
                showToastMsg("开始搜索");
            }
        });
        mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH&& !v.getText().toString().isEmpty()){
                    showToastMsg("Star");
                    return true;
                }
                return true;
            }
        });

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        mSearchgo=findView(R.id.search_go);
        mSearchView=findView(R.id.searchview);
        mSearchView.addTextChangedListener(textWatcher);
        gridView=findView(R.id.hotjob_gv);
        historyGridview=findView(R.id.history_gv);
        initMyData();


    }
    private TextWatcher textWatcher=new TextWatcher() {
        private CharSequence temp;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mSearchgo.setText("搜索");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() == 0) {
                mSearchgo.setText("取消");
            }
        }
    };

    public void initMyData(){
        List<CityBean> beanList=new ArrayList<>();
        CityBean bean=new CityBean();
        for (int i = 0; i < 18; i++) {
            beanList.add(bean);
        }
        HotJobAdapter adapter=new HotJobAdapter(this,beanList);
        gridView.setAdapter(adapter);
        historyGridview.setAdapter(adapter);
    }

}
