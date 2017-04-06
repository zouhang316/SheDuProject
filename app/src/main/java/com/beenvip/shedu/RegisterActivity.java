package com.beenvip.shedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.home.FirstSelectIdentityActivity;
import com.beenvip.shedu.utils.CommUtils;
import com.beenvip.shedu.utils.TimeButton;

/**
 * Created by ZH on 2017/3/23.
 * 497239511@qq.com
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private TimeButton timeButton;
    private Button registerButton;
    private EditText edit_phone;
    private EditText edit_code;
    private EditText edit_pwd;
    private ImageView back;
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        timeButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        setFitsSystemWindows(false);
        timeButton=findView(R.id.getcode);
        registerButton=findView(R.id.regsiter);
        edit_phone=findView(R.id.edit_phone);
        edit_code=findView(R.id.edit_code);
        edit_pwd=findView(R.id.edit_pwd);
        back=findView(R.id.back);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.getcode){
            timeButton.startTimeCount();
        }else if (v.getId()==R.id.regsiter){
            startActivity(new Intent(RegisterActivity.this,FirstSelectIdentityActivity.class));
            finish();
            //Intent intent=new Intent(RegisterActivity.this,ContractorMainActivity.class);
            //registAction();
        }else if (v.getId()==R.id.back){
            finish();
        }
    }

    private void registAction(){
        String phone=edit_phone.getText().toString().trim();
        String code=edit_code.getText().toString().trim();
        String pwd=edit_pwd.getText().toString().trim();
        if (!CommUtils.isMobile(phone)){
            showMessageDialog("提示", "请输入正确的手机号码！");
            return;
        }
        if (code.isEmpty()){
            showMessageDialog("提示", "验证码不能为空！");
            return;
        }
        if (!CommUtils.isEnabalePwd(pwd)){
            showMessageDialog("提示", "请设置6-18位由英文和数字组成的密码！");
            return;
        }

    }
}
