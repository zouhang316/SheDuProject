package com.beenvip.shedu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.beenvip.shedu.contractor.MessageFragment;
import com.beenvip.shedu.fabaofang.fragment.FaBaoIndexFragment;
import com.beenvip.shedu.fabaofang.fragment.FabaoFindBanZuFragment;
import com.beenvip.shedu.fabaofang.fragment.FabaoMineFragment;
import com.beenvip.shedu.fabaofang.fragment.MyProjectFragment;
import com.beenvip.shedu.fabaofang.view.FindeBanzuFragment_mvp;
import com.beenvip.shedu.utils.ExitAppliation;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ZH on 2017/3/29.
 * 497239511@qq.com
 */

public class FaBaoMianActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentTransaction ft;
    protected final int INDEX_FLAG = 0x100;
    protected final int FINDCBS_FLAG = 0x101;
    protected final int PROJECT_FLAG = 0x104;
    protected final int MESSAGE_FLAG = 0x102;
    protected final int MINE_FLAG = 0x103;

    private FaBaoIndexFragment indexFragment;
    private MessageFragment messageFragment;
    private FabaoFindBanZuFragment findBanZuFragment;
    private FabaoMineFragment fabaoMineFragment;
    private FindeBanzuFragment_mvp fragmentMvp;
    private MyProjectFragment projectFragment;

    private RadioButton rd_index;
    private RadioButton rd_message;
    private RadioButton rd_findcbf;
    private RadioButton rd_myproject;
    private RadioButton rd_mine;
    public static FaBaoMianActivity instance=null;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        setFullStyle();
        super.onCreate(savedInstanceState);
        instance=this;
        setContentView(R.layout.activity_mianfabao);
        rd_index= (RadioButton) findViewById(R.id.rb_index);
        rd_findcbf= (RadioButton) findViewById(R.id.rb_findcbx);
        rd_myproject = (RadioButton) findViewById(R.id.rb_myproject);
        rd_message= (RadioButton) findViewById(R.id.rb_message);
        rd_mine= (RadioButton) findViewById(R.id.rb_mine);
        initListener();
        isShowMineFragment();


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
                if (indexFragment == null) {
                    indexFragment = new FaBaoIndexFragment();
                    indexFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, indexFragment);
                } else {
                    ft.show(indexFragment);
                }
                break;
            case FINDCBS_FLAG:
                rd_findcbf.setChecked(true);
                if (fragmentMvp == null) {
                    fragmentMvp = new FindeBanzuFragment_mvp();
                    fragmentMvp.setArguments(bundle);
                    ft.add(R.id.fl_content, fragmentMvp);
                } else {
                    ft.show(fragmentMvp);
                }
                break;
            case PROJECT_FLAG:
                rd_myproject.setChecked(true);
                if (projectFragment == null) {
                    projectFragment = new MyProjectFragment();
                    projectFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, projectFragment);
                } else {
                    ft.show(projectFragment);
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
                if (fabaoMineFragment == null) {
                    fabaoMineFragment = new FabaoMineFragment();
                    fabaoMineFragment.setArguments(bundle);
                    ft.add(R.id.fl_content, fabaoMineFragment);
                } else {
                    ft.show(fabaoMineFragment);
                }
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (indexFragment != null) {
            ft.hide(indexFragment);
        }
        if (fragmentMvp != null) {
            ft.hide(fragmentMvp);
        }
        if (projectFragment != null) {
            ft.hide(projectFragment);
        }
        if (messageFragment != null) {
            ft.hide(messageFragment);
        }
        if (fabaoMineFragment != null) {
            ft.hide(fabaoMineFragment);
        }
    }

    private void initListener(){
        rd_index.setOnClickListener(this);
        rd_findcbf.setOnClickListener(this);
        rd_myproject.setOnClickListener(this);
        rd_message.setOnClickListener(this);
        rd_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Bundle bundle = new Bundle();
        if (R.id.rb_index == viewId) {
            showContentFragment(INDEX_FLAG, bundle);
        } else if (R.id.rb_findcbx == viewId) {
            showContentFragment(FINDCBS_FLAG, bundle);
        } else if (R.id.rb_message == viewId) {
            showContentFragment(MESSAGE_FLAG, bundle);
        } else if (R.id.rb_mine == viewId) {
            showContentFragment(MINE_FLAG, bundle);
        }else if (R.id.rb_myproject ==viewId){
            showContentFragment(PROJECT_FLAG, bundle);
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
    private void setFullStyle() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
