package com.beenvip.shedu.publics;

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
 * Created by ZH on 2017/3/28.
 * 497239511@qq.com
 */

public class ChoiceIdentityActivity extends BaseActivity {
    private Button comit;
    private RadioButton work;
    private RadioButton fabaofang;
    private RadioButton thirdparty;
    private int type;
    @Override
    protected void initData() {
        type=PreferenceManager.getInstance(this).getInt("type",0);
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
    private void choiceWork(){
        if (type==0){
            ChoiceIdentityActivity.this.finish();
        } else {
            closeOldMian();
            PreferenceManager.getInstance(ChoiceIdentityActivity.this).putInt("type",0);
            Intent intent=new Intent(ChoiceIdentityActivity.this, ContractorMainActivity.class);
            intent.putExtra("last",4);
            startActivity(intent);
            ChoiceIdentityActivity.this.finish();
        }
    }
    private void choiceFabaofang(){
        if (type==1){
            ChoiceIdentityActivity.this.finish();
        }else {
            closeOldMian();
            PreferenceManager.getInstance(ChoiceIdentityActivity.this).putInt("type",1);
            Intent intent=new Intent(ChoiceIdentityActivity.this, FaBaoMianActivity.class);
            intent.putExtra("last",4);
            startActivity(intent);
            ChoiceIdentityActivity.this.finish();
        }
    }
    //选择我是第三方(供应方)进入SupplierMain
    private void choiceThirdpart(){
        if (type==2){
            ChoiceIdentityActivity.this.finish();
        }else {
            closeOldMian();
            PreferenceManager.getInstance(ChoiceIdentityActivity.this).putInt("type",2);
            Intent intent=new Intent(ChoiceIdentityActivity.this, SupplierMainActivity.class);
            intent.putExtra("last",4);
            startActivity(intent);
            ChoiceIdentityActivity.this.finish();
        }
    }
    //关闭上个身份MainActivity
    private void closeOldMian(){
        switch (type){
            case 0:
                ContractorMainActivity.instance.finish();
                break;
            case 1:
                FaBaoMianActivity.instance.finish();
                break;
            case 2:
                SupplierMainActivity.instance.finish();
                break;
        }
    }


}
