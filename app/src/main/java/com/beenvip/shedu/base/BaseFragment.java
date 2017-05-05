package com.beenvip.shedu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beenvip.shedu.http.httpurlconnection.HttpHelper;
import com.beenvip.shedu.view.WaitDialog;

/**
 * Created by ZH on 2017/3/8.
 * 497239511@qq.com
 */

public  abstract class BaseFragment extends Fragment {
    public HttpHelper httpHelper;
    private WaitDialog mWaitDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpHelper=new HttpHelper(getActivity());
        mWaitDialog = new WaitDialog(getActivity());
        initData(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(getFragmentLayoutId(), container, false);
        initView(convertView,savedInstanceState);
        return convertView;
    }

    public void showToast(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }
    /***
     * Initialization data, the subclass must implements this method
     * 初始化数据，子类必须实现此方法
     *
     * @param savedInstanceState
     */
    public abstract void initData(Bundle savedInstanceState);

    /***
     * Initialization view, the subclass must implements this method
     * 初始化控件，子类必须实现此方法
     *
     * @param view
     */
    public abstract void initView(View view,Bundle savedInstanceState);

    /***
     * Back Layout, subclass must implements this method
     * 返回子类布局，子类需要实现此方法
     *
     * @return
     */
    public abstract int getFragmentLayoutId();
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


}
