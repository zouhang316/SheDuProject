package com.beenvip.shedu.base;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beenvip.shedu.R;
import com.beenvip.shedu.http.httpurlconnection.HttpHelper;
import com.beenvip.shedu.view.WaitDialog;

/**
 * Created by ZH on 2017/3/8.
 * 497239511@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity {
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private FrameLayout mContentLayout;
    private LinearLayout mLinearLayout;
    private WaitDialog mWaitDialog;
    public HttpHelper httpHelper;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpHelper=new HttpHelper(this);
        getDelegate().setContentView(R.layout.activity_base);
        mWaitDialog = new WaitDialog(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.back);
        mContentLayout = (FrameLayout) findViewById(R.id.content);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_base);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setBackBar(true);
        if (!isShowToolBar()) {
            getSupportActionBar().hide();
        }
        initData();
        onActivityCreate(savedInstanceState);
        initListener();
    }

    /***
     * 初始化数据
     */
    protected abstract void initData();

    /***
     * 初始化控件监听
     */
    protected abstract void initListener();

    /***
     * 是否显示ToolBar
     * @return
     */
    protected abstract boolean isShowToolBar();

    /***
     * 初始化控件View  FindViewById 需要在这里
     * @param savedInstanceState
     */
    protected abstract void onActivityCreate(Bundle savedInstanceState);

    /***
     * 替換FindViewById  減少每次強轉的問題
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findView(int viewId) {
        return (T) mContentLayout.findViewById(viewId);
    }

    public FrameLayout getContentLayout() {
        return mContentLayout;
    }

    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        } else {
            mTitle.setText("");
        }
    }

    @Override
    public void setTitle(int titleId) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        if (titleId > 0) {
            mTitle.setText(titleId);
        } else {
            mTitle.setText("");
        }
    }

    public void setTitleTextColor(int color) {
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.tv_title);
        mTitle.setTextColor(color);
    }

    public void setSubtitle(CharSequence title) {
        mToolbar.setSubtitle(title);
    }

    public void setSubtitle(int titleId) {
        mToolbar.setSubtitle(titleId);
    }

    public void setSubtitleTextColor(int color) {
        mToolbar.setSubtitleTextColor(color);
    }

    public void setBackBar(boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    public void setContentBackground(int color) {
        mContentLayout.setBackgroundResource(color);
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.addView(view, params);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mContentLayout.removeAllViews();
        getLayoutInflater().inflate(layoutResID, mContentLayout, true);
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, int message) {
        showMessageDialog(getText(title), getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(int title, CharSequence message) {
        showMessageDialog(getText(title), message);
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, int message) {
        showMessageDialog(title, getText(message));
    }

    /**
     * Show message dialog.
     *
     * @param title   title.
     * @param message message.
     */
    public void showMessageDialog(CharSequence title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /***
     * Show message dialog with custom DialogInterface.OnClickListener
     *
     * @param title
     * @param message
     * @param okListener
     */
    public void showMessageDialog(CharSequence title, CharSequence message, DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok, okListener);
        builder.show();
    }

    public void setFitsSystemWindows(boolean b) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mLinearLayout.setFitsSystemWindows(b);
        }

    }

    public void showLoading() {
        if (mWaitDialog.isShowing()) {
            dismissLoading();
        }
        mWaitDialog.setCanceledOnTouchOutside(false);
        mWaitDialog.show();
    }

    public void dismissLoading() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) mWaitDialog.dismiss();
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return onOptionsItemSelectedCompat(item);
    }

    protected boolean onOptionsItemSelectedCompat(MenuItem item) {
        return false;
    }

    public void showToastMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
