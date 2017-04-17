package com.beenvip.shedu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.beenvip.shedu.contractor.ChanceFragment;
import com.beenvip.shedu.contractor.ContractorIndexFragment;
import com.beenvip.shedu.contractor.ContractorMineFragment;
import com.beenvip.shedu.contractor.MessageFragment;
import com.beenvip.shedu.contractor.MyBanzuFragment;
import com.beenvip.shedu.event.ShowChance;
import com.beenvip.shedu.httputils.PermissionMgr;
import com.beenvip.shedu.utils.ExitAppliation;
import com.beenvip.shedu.utils.LalaLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

public class ContractorMainActivity extends AppCompatActivity implements View.OnClickListener{
    protected final int INDEX_FLAG = 0x100;
    protected final int CHANCE_FLAG = 0x101;
    protected final int MESSAGE_FLAG = 0x102;
    protected final int MINE_FLAG = 0x103;
    protected final int MYBANZU_FLAG=0x104;

    private ContractorIndexFragment contractorIndexFragment;
    private ChanceFragment chanceFragment;
    private MessageFragment messageFragment;
    private ContractorMineFragment contractorMineFragment;
    private MyBanzuFragment banzuFragment;
    private FragmentTransaction ft;

    private RadioButton rd_index;
    private RadioButton rd_chance;
    private RadioButton rd_message;
    private RadioButton rd_mine;
    private RadioButton rd_mybanzu;
    public static  ContractorMainActivity instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        instance=this;
        rd_index= (RadioButton) findViewById(R.id.rb_index);
        rd_chance= (RadioButton) findViewById(R.id.rb_chance);
        rd_message= (RadioButton) findViewById(R.id.rb_message);
        rd_mine= (RadioButton) findViewById(R.id.rb_mine);
        rd_mybanzu= (RadioButton) findViewById(R.id.rb_mybanzu);
        initListener();
        isShowMineFragment();
        getReadContactsPermission();

    }
    private void showContentFragment(int index, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);

        switch (index) {
            case INDEX_FLAG:
                rd_index.setChecked(true);
                if (contractorIndexFragment == null) {
                    contractorIndexFragment = new ContractorIndexFragment();
                    contractorIndexFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, contractorIndexFragment);
                } else {
                    ft.show(contractorIndexFragment);
                }
                break;
            case CHANCE_FLAG:
                rd_chance.setChecked(true);
                if (chanceFragment == null) {
                    chanceFragment = new ChanceFragment();
                    chanceFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, chanceFragment);
                } else {
                    ft.show(chanceFragment);
                }
                break;
            case MESSAGE_FLAG:
                rd_message.setChecked(true);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    messageFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, messageFragment);
                } else {
                    ft.show(messageFragment);
                }
                break;
            case MINE_FLAG:
                rd_mine.setChecked(true);
                if (contractorMineFragment == null) {
                    contractorMineFragment = new ContractorMineFragment();
                    contractorMineFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, contractorMineFragment);
                } else {
                    ft.show(contractorMineFragment);
                }
                break;
            case MYBANZU_FLAG:
                rd_mybanzu.setChecked(true);
                if (banzuFragment == null) {
                    banzuFragment = new MyBanzuFragment();
                    banzuFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, banzuFragment);
                } else {
                    ft.show(banzuFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (contractorIndexFragment != null) {
            ft.hide(contractorIndexFragment);
        }
        if (chanceFragment != null) {
            ft.hide(chanceFragment);
        }
        if (messageFragment != null) {
            ft.hide(messageFragment);
        }
        if (contractorMineFragment != null) {
            ft.hide(contractorMineFragment);
        }
        if (banzuFragment != null) {
            ft.hide(banzuFragment);
        }
    }
    private void initListener(){
        rd_index.setOnClickListener(this);
        rd_chance.setOnClickListener(this);
        rd_message.setOnClickListener(this);
        rd_mine.setOnClickListener(this);
        rd_mybanzu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Bundle bundle = new Bundle();
        if (R.id.rb_index == viewId) {
            showContentFragment(INDEX_FLAG, bundle);
        } else if (R.id.rb_chance == viewId) {
            showContentFragment(CHANCE_FLAG, bundle);
        } else if (R.id.rb_message == viewId) {
            showContentFragment(MESSAGE_FLAG, bundle);
        } else if (R.id.rb_mine == viewId) {
            showContentFragment(MINE_FLAG, bundle);
        }else if (R.id.rb_mybanzu==viewId){
            showContentFragment(MYBANZU_FLAG, bundle);
        }
    }
    private void isShowMineFragment(){
        //切换身份是否停留在“我的” 页面
        if (getIntent().getIntExtra("last",1)==4){
            showContentFragment(MINE_FLAG,null);
        }else {
            showContentFragment(INDEX_FLAG,null);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ExitAppliation.getInstance().exit();

        }
    }
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void showChance(ShowChance chance){
        showContentFragment(CHANCE_FLAG,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    private void getReadContactsPermission(){
        PermissionMgr.grantPermission(this,new String[]{
                Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS
        });

    }
}
