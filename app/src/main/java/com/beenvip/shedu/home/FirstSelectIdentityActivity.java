package com.beenvip.shedu.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.beenvip.shedu.ContractorMainActivity;
import com.beenvip.shedu.FaBaoMianActivity;
import com.beenvip.shedu.R;
import com.beenvip.shedu.SupplierMainActivity;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.utils.PreferenceManager;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FirstSelectIdentityActivity extends BaseActivity {
    private Button comit;
    private RadioButton work;
    private RadioButton fabaofang;
    private RadioButton thirdparty;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choiceidentity);
        work=findView(R.id.btn_work);
        fabaofang=findView(R.id.btn_fabaofang);
        thirdparty=findView(R.id.btn_thirdparty);
        comit=findView(R.id.comit);
        comit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (work.isChecked()){
                    choiceWork();
                }else if (thirdparty.isChecked()){
                    choiceThirdpart();
                }else if (fabaofang.isChecked()){
                    choiceFabaofang();
                }
            }
        });
    }
    //选择我是工人
    private void choiceWork(){
        PreferenceManager.getInstance(FirstSelectIdentityActivity.this).putInt("type",0);
        Intent intent=new Intent(FirstSelectIdentityActivity.this, ContractorMainActivity.class);
        startActivity(intent);
        FirstSelectIdentityActivity.this.finish();
    }
    //选择我是发包方
    private void choiceFabaofang(){
        PreferenceManager.getInstance(FirstSelectIdentityActivity.this).putInt("type",1);
        Intent intent=new Intent(FirstSelectIdentityActivity.this, FaBaoMianActivity.class);
        startActivity(intent);
        FirstSelectIdentityActivity.this.finish();
    }
    //选择我是第三方(供应方)进入SupplierMain
    private void choiceThirdpart(){
        PreferenceManager.getInstance(FirstSelectIdentityActivity.this).putInt("type",2);
        Intent intent=new Intent(FirstSelectIdentityActivity.this, SupplierMainActivity.class);
        startActivity(intent);
        FirstSelectIdentityActivity.this.finish();
    }
}
